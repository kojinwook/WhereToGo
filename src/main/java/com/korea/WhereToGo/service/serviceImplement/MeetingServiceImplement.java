package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.meeting.PatchMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostJoinMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.meeting.*;
import com.korea.WhereToGo.entity.*;
import com.korea.WhereToGo.repository.*;
import com.korea.WhereToGo.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImplement implements MeetingService {
    private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final ImageRepository imageRepository;
    private final MeetingRequestRepository meetingRequestRepository;
    private final MeetingUsersRepository meetingUsersRepository;
    private final MeetingBoardRepository meetingBoardRepository;

    @Override
    public ResponseEntity<? super GetMeetingResponseDto> getMeeting(Long meetingId) {
        MeetingEntity meetingEntity = null;
        try {
            meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return GetMeetingResponseDto.notExistMeeting();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMeetingResponseDto.success(meetingEntity);
    }

    @Override
    public ResponseEntity<? super PostMeetingResponseDto> postMeeting(PostMeetingRequestDto dto, String userId) {
        String nickname = dto.getNickname();
        try {
            boolean userEntity = userRepository.existsByNickname(nickname);
            if(!userEntity) return PostMeetingResponseDto.notExistUser();

            MeetingEntity meetingEntity = new MeetingEntity(dto);
            meetingRepository.save(meetingEntity);

            List<String> meetingImageUrl = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : meetingImageUrl) {
                ImageEntity imageEntity = new ImageEntity(image, meetingEntity, userId);
                imageEntity.setMeeting(meetingEntity);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostMeetingResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetAllMeetingResponseDto> getAllMeeting() {
        List<MeetingEntity> meetingList = new ArrayList<>();
        try {
            meetingList = meetingRepository.findAll();
            if (meetingList.isEmpty()) return GetAllMeetingResponseDto.notExistMeeting();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllMeetingResponseDto.success(meetingList);
    }

    @Override
    public ResponseEntity<? super PostJoinMeetingResponseDto> postJoinMeeting(PostJoinMeetingRequestDto dto) {
        try {
            Long meetingId = dto.getMeetingId();
            String nickname = dto.getNickname();

            MeetingEntity meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return PostJoinMeetingResponseDto.notExistMeeting();

            UserEntity userEntity = userRepository.findByNickname(nickname);
            if (userEntity == null) return PostJoinMeetingResponseDto.notExistUser();

            boolean alreadyRequested = meetingRequestRepository.existsByMeetingAndUser(meetingEntity, userEntity);
            if (alreadyRequested) {
                return PostJoinMeetingResponseDto.alreadyRequested();
            }

            MeetingRequestEntity meetingRequestEntity = new MeetingRequestEntity();
            meetingRequestEntity.setMeeting(meetingEntity);
            meetingRequestEntity.setUser(userEntity);
            meetingRequestEntity.setRequestDate(LocalDateTime.now());
            meetingRequestEntity.setStatus("PENDING");

            meetingRequestRepository.save(meetingRequestEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostJoinMeetingResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostResponseToJoinResponseDto> respondToJoinRequest(Long requestId, boolean isAccepted) {
        try {
            MeetingRequestEntity meetingRequest = meetingRequestRepository.findById(requestId).orElse(null);
            if (meetingRequest == null) return PostResponseToJoinResponseDto.notExistRequest();


            if (isAccepted) {
                meetingRequest.setStatus("ACCEPTED");

                MeetingUsersEntity meetingUserEntity = new MeetingUsersEntity();
                meetingUserEntity.setMeeting(meetingRequest.getMeeting());
                meetingUserEntity.setUser(meetingRequest.getUser());
                meetingUserEntity.setUserNickname(meetingRequest.getUser().getNickname());
                meetingUserEntity.setJoinDate(LocalDateTime.now());

                meetingUsersRepository.save(meetingUserEntity);
            } else {
                meetingRequest.setStatus("REJECTED");
            }

            meetingRequestRepository.save(meetingRequest);
            meetingRequestRepository.delete(meetingRequest);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostResponseToJoinResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetMeetingRequestsResponseDto> getMeetingRequests(Long meetingId) {
        List<MeetingRequestEntity> requests = new ArrayList<>();
        try {

            requests = meetingRequestRepository.findByMeeting_MeetingId(meetingId);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMeetingRequestsResponseDto.success(requests);
    }

    @Override
    public ResponseEntity<? super PatchMeetingResponseDto> patchMeeting(PatchMeetingRequestDto dto, Long meetingId, String userId) {
        try {
            MeetingEntity meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return PatchMeetingResponseDto.notExistMeeting();

            UserEntity user = userRepository.findByUserId(userId);
            if (!user.getUserId().equals(userId)) return PatchMeetingResponseDto.noPermission();

            meetingEntity.patchMeeting(dto);
            meetingRepository.save(meetingEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchMeetingResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(PostMeetingBoardRequestDto dto, Long meetingId, String userId) {
        try {
            MeetingEntity meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return PostMeetingBoardResponseDto.notExistMeeting();

            MeetingBoardEntity meetingBoardEntity = new MeetingBoardEntity(dto);
            meetingBoardEntity.setMeeting(meetingEntity);

            meetingBoardRepository.save(meetingBoardEntity);

            List<String> imageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : imageList) {
                ImageEntity imageEntity = new ImageEntity(image, meetingBoardEntity, userId);
                imageEntity.setMeetingBoard(meetingBoardEntity);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostMeetingBoardResponseDto.success();
    }
}
