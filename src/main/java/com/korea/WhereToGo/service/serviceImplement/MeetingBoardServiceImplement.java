package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.meeting.board.PatchMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostReplyToReplyRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.*;
import com.korea.WhereToGo.dto.response.meeting.board.reply.GetBoardReplyListResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.PostBoardReplyResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.PostReplyToReplyResponseDto;
import com.korea.WhereToGo.entity.*;
import com.korea.WhereToGo.repository.*;
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
    private final MeetingBoardReplyRepository meetingBoardReplyRepository;
    private final MeetingReplyToReplyRepository meetingReplyToReplyRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(PostMeetingBoardRequestDto dto, Long meetingId, String userId) {
        try {
            MeetingEntity meetingEntity = meetingRepository.findByMeetingId(meetingId);
            if (meetingEntity == null) return PostMeetingBoardResponseDto.notExistMeeting();

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PostMeetingBoardResponseDto.notExistUser();
//            UserDto userDto = new UserDto(userEntity);

            MeetingBoardEntity meetingBoardEntity = new MeetingBoardEntity(dto);
            meetingBoardEntity.setMeeting(meetingEntity);
            meetingBoardEntity.setUser(userEntity);

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

            if (!meetingBoardEntity.getUser().getUserId().equals(userId))
                return PatchMeetingBoardResponseDto.noPermission();

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

    @Override
    public ResponseEntity<? super DeleteMeetingBoardResponseDto> deleteMeetingBoard(Long boardId, String userId) {
        try {
            MeetingBoardEntity meetingBoardEntity = meetingBoardRepository.findByMeetingBoardId(boardId);
            if (meetingBoardEntity == null) return DeleteMeetingBoardResponseDto.notExistMeetingBoard();

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return DeleteMeetingBoardResponseDto.notExistUser();

            if (!meetingBoardEntity.getUser().getUserId().equals(userId))
                return DeleteMeetingBoardResponseDto.noPermission();

            meetingBoardRepository.delete(meetingBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteMeetingBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PostBoardReplyResponseDto> postBoardReply(PostBoardReplyRequestDto dto, String userId) {
        try {
            MeetingBoardReplyEntity comment = new MeetingBoardReplyEntity(dto);
            UserEntity user = userRepository.findByUserId(userId);
            comment.setUser(user);

            meetingBoardReplyRepository.save(comment);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardReplyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetBoardReplyListResponseDto> getBoardReplyList(Long boardId) {
        List<MeetingBoardReplyEntity> replyList = new ArrayList<>();
        try {
            replyList = meetingBoardReplyRepository.findByMeetingBoardId(boardId);

            for (MeetingBoardReplyEntity meetingBoardReplyEntity : replyList) {
                UserEntity userEntity1 = meetingBoardReplyEntity.getUser();
                if (userEntity1 != null) {
                    UserDto userDto = new UserDto(userEntity1);
                    meetingBoardReplyEntity.setUserDto(userDto);
                }
                for (MeetingReplyToReplyEntity replyReply : meetingBoardReplyEntity.getReplies()) {
                    UserEntity userEntity2 = replyReply.getUser();
                    if (userEntity1 != null) {
                        UserDto userDto = new UserDto(userEntity2);
                        meetingBoardReplyEntity.setUserDto(userDto);
                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardReplyListResponseDto.success(replyList);
    }

    private void setUserDtoToReplyReply(MeetingBoardReplyEntity meetingBoardReplyEntity) {
        for(MeetingReplyToReplyEntity reply : meetingBoardReplyEntity.getReplies()) {
            UserDto userDto = new UserDto(reply.getUser());
            reply.setUserDto(userDto);
        }
        setUserDtoToReplyReply(meetingBoardReplyEntity);
    }

    @Override
    public ResponseEntity<? super PostReplyToReplyResponseDto> postReplyToReply(PostReplyToReplyRequestDto dto, String userId) {
        try {
            MeetingBoardReplyEntity parentComment = meetingBoardReplyRepository.findById(dto.getParentCommentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent comment ID"));

            MeetingReplyToReplyEntity reply = new MeetingReplyToReplyEntity(dto, parentComment);
            UserEntity user = userRepository.findByUserId(userId);
            reply.setUser(user);

            meetingReplyToReplyRepository.save(reply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostReplyToReplyResponseDto.success();
    }
}
