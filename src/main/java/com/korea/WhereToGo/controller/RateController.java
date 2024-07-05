package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.rate.PostRateRequestDto;
import com.korea.WhereToGo.dto.response.rate.GetRateAverageResponseDto;
import com.korea.WhereToGo.dto.response.rate.PostRateResponseDto;
import com.korea.WhereToGo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/saveRate")
    public ResponseEntity<? super PostRateResponseDto> saveRate(
            @RequestBody PostRateRequestDto dto
    ) {
        ResponseEntity<? super PostRateResponseDto> response = rateService.postRate(dto);
        return response;
    }

    @GetMapping("/getRateAverage")
    public ResponseEntity<? super GetRateAverageResponseDto> getRateAverage(
            @RequestParam String contentId
    ) {
        ResponseEntity<? super GetRateAverageResponseDto> response = rateService.getRateAverage(contentId);
        return response;
    }
}
