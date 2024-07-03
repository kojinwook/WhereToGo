package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.response.festival.GetFestivalListResponseDto;
import com.korea.WhereToGo.dto.response.festival.GetSearchFestivalListResponseDto;
import com.korea.WhereToGo.dto.response.festival.PostFestivalListResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FestivalService {
    ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(String eventStartDate);
    ResponseEntity<? super GetFestivalListResponseDto> getFestivalList();
    ResponseEntity<? super GetSearchFestivalListResponseDto> searchFestivalList(String areaCode);
}
