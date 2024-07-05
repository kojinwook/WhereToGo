package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.rate.PostRateRequestDto;
import com.korea.WhereToGo.dto.response.rate.GetRateAverageResponseDto;
import com.korea.WhereToGo.dto.response.rate.PostRateResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface RateService {
    ResponseEntity<? super PostRateResponseDto> postRate(PostRateRequestDto dto);
    ResponseEntity<? super GetRateAverageResponseDto> getRateAverage(String contentId);

}
