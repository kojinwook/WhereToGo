package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.user.ChangePasswordRequestDto;
import com.korea.WhereToGo.dto.request.user.PatchNicknameRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.user.*;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.EmailService;
import com.korea.WhereToGo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplement.class);

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String userId){
        UserEntity userEntity = null;
        try{
            userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return GetUserResponseDto.notExistUser();
        }catch (Exception exception) {
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
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return ChangePasswordResponseDto.notExistUser();

            String currentPassword = dto.getCurrentPassword();
            if (!passwordEncoder.matches(currentPassword, userEntity.getPassword()))
                return ChangePasswordResponseDto.wrongPassword();

            String newPassword = dto.getNewPassword();
            String hashedPassword = passwordEncoder.encode(newPassword);
            userEntity.setPassword(hashedPassword);
            userRepository.save(userEntity);

            log.info("User {} changed password successfully.", userId);
        }catch (Exception exception) {
            log.error("Error occurred while changing password for user {}.", userId, exception);
            return ResponseDto.databaseError();
        }
        return ChangePasswordResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String userId){
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) PatchNicknameResponseDto.notExistUser();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            userEntity.setNickname(nickname);

            userRepository.save(userEntity);
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PasswordRecoveryResponseDto> passwordRecovery(String email) {
        try{
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return PasswordRecoveryResponseDto.notExistUser();

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
