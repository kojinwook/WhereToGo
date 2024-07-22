package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.festival.PatchFestivalRequestDto;
import com.korea.WhereToGo.dto.response.festival.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface FestivalService {
    ResponseEntity<? super PostFestivalListResponseDto> saveFestivalList(String eventStartDate);
    ResponseEntity<? super GetFestivalListResponseDto> getFestivalList();
    ResponseEntity<? super GetSearchFestivalListResponseDto> searchFestivalList(String areaCode);
    ResponseEntity<? super PatchFestivalResponseDto> patchFestival(PatchFestivalRequestDto dto, String contentId, String userId);
    ResponseEntity<? super GetFestivalResponseDto> getFestival(String contentId);
    ResponseEntity<? super GetTop5FestivalListResponseDto> getTop5FestivalList();
}
