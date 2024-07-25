package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.user.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.user.*;
import com.korea.WhereToGo.entity.*;
import com.korea.WhereToGo.repository.*;
import com.korea.WhereToGo.service.EmailService;
import com.korea.WhereToGo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final FavoriteRepository favoriteRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingBoardRepository meetingBoardRepository;
    private final MeetingBoardReplyRepository meetingBoardReplyRepository;
    private final MeetingUsersRepository meetingUsersRepository;
    private final QuestionRepository questionRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ReportUserRepository reportUserRepository;
    private final ImageRepository imageRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplement.class);

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userId) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return GetUserResponseDto.notExistUser();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String userId) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super ChangePasswordResponseDto> changePassword(ChangePasswordRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return ChangePasswordResponseDto.notExistUser();

            String currentPassword = dto.getCurrentPassword();
            if (!passwordEncoder.matches(currentPassword, userEntity.getPassword()))
                return ChangePasswordResponseDto.wrongPassword();

            String newPassword = dto.getNewPassword();
            String hashedPassword = passwordEncoder.encode(newPassword);
            userEntity.setPassword(hashedPassword);
            userRepository.save(userEntity);

            log.info("User {} changed password successfully.", userId);
        } catch (Exception exception) {
            log.error("Error occurred while changing password for user {}.", userId, exception);
            return ResponseDto.databaseError();
        }
        return ChangePasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super WithdrawalUserResponseDto> withdrawalUser(WithdrawalUserRequestDto dto) {
        String userId = dto.getUserId();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return WithdrawalUserResponseDto.notExistedUser();

            String nickname = userEntity.getNickname();

            if (!userEntity.getUserId().equals(userId)) return WithdrawalUserResponseDto.notPermission();

            String password = dto.getPassword();
            if (!passwordEncoder.matches(password, userEntity.getPassword()))
                return WithdrawalUserResponseDto.wrongPassword();

            List<FavoriteEntity> favoriteEntities = userEntity.getLikes();
            favoriteRepository.deleteAll(favoriteEntities);

            List<MeetingBoardReplyEntity> meetingBoardReplyEntities = userEntity.getMeetingBoardReply();
            meetingBoardReplyRepository.deleteAll(meetingBoardReplyEntities);

            Optional<MeetingUsersEntity> meetingUsersEntity = meetingUsersRepository.findByUserNickname(nickname);
            meetingUsersEntity.ifPresent(meetingUsersRepository::delete);

            List<MeetingEntity> meetingEntities = meetingRepository.findByCreatorNickname(nickname);
            List<Long> meetingIds = meetingEntities.stream().map(MeetingEntity::getMeetingId).toList();
            List<MeetingBoardEntity> meetingBoardEntities = meetingBoardRepository.findByMeeting_MeetingIdIn(meetingIds);
            meetingBoardRepository.deleteAll(meetingBoardEntities);
            meetingRepository.deleteAll(meetingEntities);

            List<QuestionEntity> questionEntities = questionRepository.findByNickname(nickname);
            questionRepository.deleteAll(questionEntities);

            List<ChatMessageEntity> chatMessageEntities = chatMessageRepository.findBySender(nickname);
            chatMessageRepository.deleteAll(chatMessageEntities);

            List<ChatRoomEntity> chatRoomEntities = chatRoomRepository.findByNicknameOrCreatorNickname(nickname, nickname);
            chatRoomRepository.deleteAll(chatRoomEntities);

            userRepository.delete(userEntity);

            log.info("User {} has been deleted.", userId);
        } catch (Exception exception) {
            log.error("Error occurred while deleting user {}.", userId, exception);
            return ResponseDto.databaseError();
        }
        return WithdrawalUserResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) PatchNicknameResponseDto.notExistUser();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();
            userEntity.setNickname(nickname);

            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PasswordRecoveryResponseDto> passwordRecovery(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PasswordRecoveryResponseDto.notExistUser();

            String temporaryPassword = generateTemporaryPassword();
            userEntity.setPassword(passwordEncoder.encode(temporaryPassword));

            userRepository.save(userEntity);

            String emailText = "임시 비밀번호는: " + temporaryPassword + " 입니다.\n" + "로그인 후 비밀번호를 변경해주세요.";

            emailService.sendEmail(email, "임시 비밀번호", emailText);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PasswordRecoveryResponseDto.success();
    }

    @Override
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return FindUserIdResponseDto.notExistUser();

            String userId = userEntity.getUserId();

            String emailText = "회원님의 아이디는: " + userId + " 입니다.";

            emailService.sendEmail(email, "아이디", emailText);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return FindUserIdResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(String userId) {
        List<UserDto> userList = new ArrayList<>();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null || userEntity.getRole().equals("ROLE_USER"))
                return GetUserListResponseDto.noPermission();

//            List<UserEntity> users = userRepository.findAllNonAdminUsers();
            List<UserEntity> users = userRepository.findAll();
            if (users.isEmpty()) return GetUserListResponseDto.notExistUser();

            for (UserEntity user : users) {
                UserDto userDto = new UserDto(user);
                userList.add(userDto);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetUserListResponseDto.success(userList);
    }

    @Override
    public ResponseEntity<? super DeleteUserResponseDto> deleteUser(String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return DeleteUserResponseDto.notExistUser();

            String nickname = userEntity.getNickname();

            List<FavoriteEntity> favoriteEntities = userEntity.getLikes();
            favoriteRepository.deleteAll(favoriteEntities);

            List<MeetingBoardReplyEntity> meetingBoardReplyEntities = userEntity.getMeetingBoardReply();
            meetingBoardReplyRepository.deleteAll(meetingBoardReplyEntities);

            Optional<MeetingUsersEntity> meetingUsersEntity = meetingUsersRepository.findByUserNickname(nickname);
            meetingUsersEntity.ifPresent(meetingUsersRepository::delete);

            List<MeetingEntity> meetingEntities = meetingRepository.findByCreatorNickname(nickname);
            List<Long> meetingIds = meetingEntities.stream().map(MeetingEntity::getMeetingId).toList();
            List<MeetingBoardEntity> meetingBoardEntities = meetingBoardRepository.findByMeeting_MeetingIdIn(meetingIds);
            meetingBoardRepository.deleteAll(meetingBoardEntities);
            meetingRepository.deleteAll(meetingEntities);

            List<QuestionEntity> questionEntities = questionRepository.findByNickname(nickname);
            questionRepository.deleteAll(questionEntities);

            List<ChatMessageEntity> chatMessageEntities = chatMessageRepository.findBySender(nickname);
            chatMessageRepository.deleteAll(chatMessageEntities);

            List<ChatRoomEntity> chatRoomEntities = chatRoomRepository.findByNicknameOrCreatorNickname(nickname, nickname);
            chatRoomRepository.deleteAll(chatRoomEntities);

            userRepository.delete(userEntity);

            log.info("User {} has been deleted.", userId);
        } catch (Exception exception) {
            log.error("Error occurred while deleting user {}.", userId, exception);
            return ResponseDto.databaseError();
        }
        return DeleteUserResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostReportUserResponseDto> reportUser(PostReportUserRequestDto dto, String userId) {
        ReportUserEntity reportUserEntity = new ReportUserEntity();
        String reportUserNickname = dto.getReportUserNickname();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            UserEntity reportUser = userRepository.findByNickname(reportUserNickname);
            if (userEntity == null || reportUser == null) return PostReportUserResponseDto.notExistUser();

            reportUserEntity = new ReportUserEntity(dto, userEntity.getNickname());
            reportUserRepository.save(reportUserEntity);

            reportUser.setReportCount(userEntity.getReportCount() + 1);
//            userEntity.decreaseTemperature(0.5);
            userRepository.save(reportUser);

            List<String> imageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : imageList) {
                ImageEntity imageEntity = new ImageEntity(image, reportUserEntity, userId);
                imageEntity.setReportUser(reportUserEntity);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostReportUserResponseDto.success();
    }

    @Override
    public ResponseEntity<? super BlockUserResponseDto> blockUser(BlockUserRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null || userEntity.getRole().equals("ROLE_USER"))
                return BlockUserResponseDto.noPermission();

            String targetUserId = dto.getUserId();
            int blockDays = dto.getBlockDay();
            UserEntity targetUserEntity = userRepository.findByUserId(targetUserId);

            targetUserEntity.setBlocked(true);
            targetUserEntity.setBlockReleaseDate(targetUserEntity.getBlockReleaseDate().plusDays(blockDays));
            userRepository.save(targetUserEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return BlockUserResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetTop5TemperatureUserResponseDto> getTop5User() {
        List<UserDto> userList = new ArrayList<>();
        try {
            List<UserEntity> users = userRepository.findTop5ByOrderByTemperatureDesc();

            for (UserEntity user : users) {
                UserDto userDto = new UserDto(user);
                userList.add(userDto);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop5TemperatureUserResponseDto.success(userList);
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String userId) {

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PatchProfileImageResponseDto.notExistUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchProfileImageResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchUserResponseDto> patchUser(PatchUserRequestDto dto, String userId) {

        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PatchUserResponseDto.notExistUser();

            userEntity.patchUser(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchUserResponseDto.success();
    }

    @Override
    public ResponseEntity<? super VerifyPasswordResponseDto> verifyPassword(VerifyPasswordRequestDto dto, String userId) {
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return VerifyPasswordResponseDto.notExistUser();

            String password = dto.getPassword();
            if (!passwordEncoder.matches(password, userEntity.getPassword()))
                return VerifyPasswordResponseDto.wrongPassword();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return VerifyPasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetReportListResponseDto> getReportList(String nickname, String userId) {
        List<ReportUserEntity> reportList = new ArrayList<>();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null || userEntity.getRole().equals("ROLE_USER"))
                return GetReportListResponseDto.noPermission();

            reportList = reportUserRepository.findByReportUserNickname(nickname);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReportListResponseDto.success(reportList);
    }

    private String generateTemporaryPassword() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            passwordBuilder.append(randomChar);
        }
        return passwordBuilder.toString();
    }
}
