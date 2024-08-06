package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchReplyReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostReplyToReplyRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.*;
import com.korea.WhereToGo.entity.MeetingBoardReplyEntity;
import com.korea.WhereToGo.entity.MeetingReplyToReplyEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.MeetingBoardReplyRepository;
import com.korea.WhereToGo.repository.MeetingReplyToReplyRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.MeetingBoardReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingBoardReplyServiceImplement implements MeetingBoardReplyService {

    private final MeetingBoardReplyRepository meetingBoardReplyRepository;
    private final UserRepository userRepository;
    private final MeetingReplyToReplyRepository meetingReplyToReplyRepository;

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

    @Override
    public ResponseEntity<? super DeleteBoardReplyResponseDto> deleteBoardReply(Long replyId, String userId) {
        MeetingBoardReplyEntity reply = new MeetingBoardReplyEntity();
        try {
            reply = meetingBoardReplyRepository.findByReplyId(replyId);
            if(reply == null) return DeleteBoardReplyResponseDto.notExistBoardReply();

            UserEntity user = userRepository.findByUserId(userId);

            if (!reply.getUser().getUserId().equals(userId) || !user.getRole().equals("ROLE_ADMIN")) return DeleteBoardReplyResponseDto.noPermission();

            meetingBoardReplyRepository.delete(reply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardReplyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteReplyReplyResponseDto> deleteReplyReply(Long replyReplyId, String userId) {
        MeetingReplyToReplyEntity reply = new MeetingReplyToReplyEntity();
        try {
            reply = meetingReplyToReplyRepository.findByReplyReplyId(replyReplyId);
            if(reply == null) return DeleteReplyReplyResponseDto.notExistedReply();

            if (!reply.getUser().getUserId().equals(userId)) return DeleteReplyReplyResponseDto.noPermission();

            meetingReplyToReplyRepository.delete(reply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteReplyReplyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardReplyResponseDto> patchBoardReply(PatchBoardReplyRequestDto dto, String userId) {
        MeetingBoardReplyEntity reply = new MeetingBoardReplyEntity();
        try {
            reply = meetingBoardReplyRepository.findByReplyId(dto.getReplyId());
            if(reply == null) return PatchBoardReplyResponseDto.notExistReply();

            if (!reply.getUser().getUserId().equals(userId)) return PatchBoardReplyResponseDto.noPermission();

            reply.patchBoardReply(dto);
            meetingBoardReplyRepository.save(reply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardReplyResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchReplyReplyResponseDto> patchReplyReply(PatchReplyReplyRequestDto dto, String userId) {
        MeetingReplyToReplyEntity reply = new MeetingReplyToReplyEntity();
        try {
            reply = meetingReplyToReplyRepository.findByReplyReplyId(dto.getReplyReplyId());
            if(reply == null) return PatchReplyReplyResponseDto.notExistReply();

            if (!reply.getUser().getUserId().equals(userId)) return PatchReplyReplyResponseDto.noPermission();

            reply.patchReplyReply(dto);
            meetingReplyToReplyRepository.save(reply);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchReplyReplyResponseDto.success();
    }
}
