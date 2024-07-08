package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.rate.PostRateRequestDto;
import com.korea.WhereToGo.dto.response.rate.GeAverageRateResponseDto;
import com.korea.WhereToGo.dto.response.rate.PostRateResponseDto;
import com.korea.WhereToGo.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @PostMapping("/postRate")
    public ResponseEntity<? super PostRateResponseDto> saveRate(
            @RequestBody PostRateRequestDto dto
    ) {
        ResponseEntity<? super PostRateResponseDto> response = rateService.postRate(dto);
        return response;
    }

    @GetMapping("/getAverageRate")
    public ResponseEntity<? super GeAverageRateResponseDto> getRateAverage(
            @RequestParam List<String> contentId
    ) {
        ResponseEntity<? super GeAverageRateResponseDto> response = rateService.getRateAverage(contentId);
        return response;
    }
}
