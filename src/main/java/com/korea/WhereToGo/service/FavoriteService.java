package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.response.festival.GetAllFavoriteListResponseDto;
import com.korea.WhereToGo.dto.response.festival.PutFavoriteResponseDto;
import org.springframework.http.ResponseEntity;

public interface FavoriteService {
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(String contentId, String nickname);
    ResponseEntity<? super GetAllFavoriteListResponseDto> getAllFavoriteList(String nickname);
}
