package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.Notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.Notice.PostNoticeRequestDto;
import com.korea.WhereToGo.dto.response.Notice.*;
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
