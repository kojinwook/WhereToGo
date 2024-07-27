package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.ChatRoomDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.ChatRoomEntity;
import com.korea.WhereToGo.repository.ChatMessageRepository;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetChatRoomResponseDto extends ResponseDto {

    List<ChatRoomDto> chatRoomList;

    public GetChatRoomResponseDto(List<ChatRoomEntity> chatRoomEntities, ChatMessageRepository chatMessageRepository) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoomList = chatRoomEntities.stream()
                .map(chatRoom -> {
                    ChatMessageEntity lastMessage = chatMessageRepository.findLastMessageByRoomId(chatRoom.getRoomId());
                    return new ChatRoomDto(chatRoom, lastMessage);
                })
                .collect(Collectors.toList());
    }

    public static ResponseEntity<? super GetChatRoomResponseDto> success(List<ChatRoomEntity> chatRoomEntities, ChatMessageRepository chatMessageRepository) {
        GetChatRoomResponseDto response = new GetChatRoomResponseDto(chatRoomEntities, chatMessageRepository);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }
}
