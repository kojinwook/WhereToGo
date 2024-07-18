package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.meeting.board.PatchMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.GetMeetingBoardListResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.GetMeetingBoardResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.PatchMeetingBoardResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.PostMeetingBoardResponseDto;
import com.korea.WhereToGo.entity.ImageEntity;
import com.korea.WhereToGo.entity.MeetingBoardEntity;
import com.korea.WhereToGo.entity.MeetingEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.ImageRepository;
import com.korea.WhereToGo.repository.MeetingBoardRepository;
import com.korea.WhereToGo.repository.MeetingRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.MeetingBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingBoardServiceImplement implements MeetingBoardService {

    private final MeetingRepository meetingRepository;
    private final MeetingBoardRepository meetingBoardRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(PostMeetingBoardRequestDto dto, Long meetingId, String userId) {
        try {
            MeetingEntity meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return PostMeetingBoardResponseDto.notExistMeeting();

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PostMeetingBoardResponseDto.notExistUser();
            UserDto userDto = new UserDto(userEntity);

            MeetingBoardEntity meetingBoardEntity = new MeetingBoardEntity(dto);
            meetingBoardEntity.setMeeting(meetingEntity);
            meetingBoardEntity.setUserDto(userDto);

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

    @Override
    public ResponseEntity<? super PatchMeetingBoardResponseDto> patchMeetingBoard(PatchMeetingBoardRequestDto dto, Long boardId, String userId) {
        try {
            MeetingBoardEntity meetingBoardEntity = meetingBoardRepository.findByMeetingBoardId(boardId);
            if (meetingBoardEntity == null) return PatchMeetingBoardResponseDto.notExistMeetingBoard();

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PatchMeetingBoardResponseDto.notExistUser();

            if (!meetingBoardEntity.getUser().getUserId().equals(userId)) return PatchMeetingBoardResponseDto.noPermission();

            meetingBoardEntity.patchMeetingBoard(dto);

            meetingBoardRepository.save(meetingBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchMeetingBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetMeetingBoardListResponseDto> getMeetingBoardList(Long meetingId) {
        try {
            List<MeetingBoardEntity> meetingBoardList = meetingBoardRepository.findByMeeting_MeetingId(meetingId);

            List<MeetingBoardEntity> meetingBoardEntityList = new ArrayList<>();
            for (MeetingBoardEntity meetingBoardEntity : meetingBoardList) {
                UserEntity userEntity = meetingBoardEntity.getUser();
                if (userEntity != null) {
                    UserDto userDto = new UserDto(userEntity);
                    meetingBoardEntity.setUserDto(userDto);
                }
                meetingBoardEntityList.add(meetingBoardEntity);
            }

            return GetMeetingBoardListResponseDto.success(meetingBoardEntityList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetMeetingBoardResponseDto> getMeetingBoard(Long boardId) {
        MeetingBoardEntity meetingBoardEntity = new MeetingBoardEntity();
        try {
            meetingBoardEntity = meetingBoardRepository.findByMeetingBoardId(boardId);
            if (meetingBoardEntity == null) return GetMeetingBoardResponseDto.notExistMeetingBoard();

            UserEntity userEntity = meetingBoardEntity.getUser();
            if (userEntity != null) {
                UserDto userDto = new UserDto(userEntity);
                meetingBoardEntity.setUserDto(userDto);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetMeetingBoardResponseDto.success(meetingBoardEntity);
    }
}
