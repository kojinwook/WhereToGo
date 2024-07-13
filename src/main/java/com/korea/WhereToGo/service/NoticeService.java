package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.notice.PostNoticeRequestDto;
import com.korea.WhereToGo.dto.response.notice.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface NoticeService {
    ResponseEntity<? super GetNoticeResponseDto> getNotice(Long NoticeId);
    ResponseEntity<? super PostNoticeResponseDto> postNotice(PostNoticeRequestDto dto);
    ResponseEntity<? super PatchNoticeResponseDto> patchNotice(PatchNoticeRequestDto dto, Long NoticeId);
    ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(Long NoticeId);
    ResponseEntity<? super GetAllNoticeResponseDto> getAllNotice();

}
