package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.festival.PatchFestivalRequestDto;
import com.korea.WhereToGo.dto.response.festival.*;
import com.korea.WhereToGo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @PostMapping("/saveFestivalList")
    public ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(
            @RequestParam String eventStartDate
    ) {
        ResponseEntity<? super PostFestivalListResponseDto> response = festivalService.saveFestivalList(eventStartDate);
        return response;
    }

    @GetMapping("/getFestivalList")
    public ResponseEntity<? super GetFestivalListResponseDto> getFestivalList() {
        ResponseEntity<? super GetFestivalListResponseDto> response = festivalService.getFestivalList();
        return response;
    }

    @GetMapping("/searchFestivalList")
    public ResponseEntity<? super GetSearchFestivalListResponseDto> searchFestivalList(
            @RequestParam String areaCode
    ) {
        ResponseEntity<? super GetSearchFestivalListResponseDto> response = festivalService.searchFestivalList(areaCode);
        return response;
    }

    @PatchMapping("/patchFestival")
    public ResponseEntity<? super PatchFestivalResponseDto> patchFestival(
            @RequestBody PatchFestivalRequestDto dto, @RequestParam String contentId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchFestivalResponseDto> response = festivalService.patchFestival(dto, contentId, userId);
        return response;
    }

    @GetMapping("/getFestival")
    public ResponseEntity<? super GetFestivalResponseDto> getFestival(
            @RequestParam String contentId
    ) {
        ResponseEntity<? super GetFestivalResponseDto> response = festivalService.getFestival(contentId);
        return response;
    }

    @GetMapping("/getTop5FestivalList")
    public ResponseEntity<? super GetTop5FestivalListResponseDto> getTop5FestivalList(
    ) {
        ResponseEntity<? super GetTop5FestivalListResponseDto> response = festivalService.getTop5FestivalList();
        return response;
    }
}
