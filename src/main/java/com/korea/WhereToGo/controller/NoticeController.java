package com.korea.WhereToGo.controller;


import com.korea.WhereToGo.dto.request.notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.notice.PostNoticeRequestDto;
import com.korea.WhereToGo.dto.response.notice.*;
import com.korea.WhereToGo.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping("")
    public ResponseEntity<? super PostNoticeResponseDto> postNotice(
            @RequestBody @Valid PostNoticeRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostNoticeResponseDto> response = noticeService.postNotice(requestBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetAllNoticeResponseDto> getAllNotice() {
        ResponseEntity<? super GetAllNoticeResponseDto> response = noticeService.getAllNotice();
        return response;
    }

    @GetMapping("/detail/{noticeId}")
    public ResponseEntity<? super GetNoticeResponseDto> getNotice(
            @PathVariable("noticeId") Long noticeId
    ) {
        ResponseEntity<? super GetNoticeResponseDto> response = noticeService.getNotice(noticeId);
        return response;
    }

    @PatchMapping("/update/{noticeId}")
    public ResponseEntity<? super PatchNoticeResponseDto> patchNotice(
            @RequestBody @Valid PatchNoticeRequestDto requestBody,
            @PathVariable("noticeId") Long noticeId
    ) {
        ResponseEntity<? super PatchNoticeResponseDto> response = noticeService.patchNotice(requestBody, noticeId);
        return response;
    }
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<? super DeleteNoticeResponseDto> deleteNotice(
            @PathVariable("noticeId") Long noticeId,
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super DeleteNoticeResponseDto> response = noticeService.deleteNotice(noticeId);
        return response;
    }
    @GetMapping("/search")
    public ResponseEntity<? super GetSearchNoticeListResponseDto> findNoticesByKeyword(
            @RequestParam String keyword
    ){
        ResponseEntity<? super GetSearchNoticeListResponseDto> response = noticeService.searchNoticeList(keyword);
        return response;
    }

}
