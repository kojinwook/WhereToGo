package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.response.festival.GetFestivalListResponseDto;
import com.korea.WhereToGo.dto.response.festival.PostFestivalListResponseDto;
import com.korea.WhereToGo.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @PostMapping("/saveFestivalList")
    public ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(@RequestParam String eventStartDate) {
        ResponseEntity<? super PostFestivalListResponseDto> response = festivalService.saveFestivalList(eventStartDate);
        return response;
    }

    @GetMapping("/getFestivalList")
    public ResponseEntity<? super GetFestivalListResponseDto> getFestivalList() {
        ResponseEntity<? super GetFestivalListResponseDto> response = festivalService.getFestivalList();
        return response;
    }
}
