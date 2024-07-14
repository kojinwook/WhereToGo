package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.response.festival.GetAllFavoriteListResponseDto;
import com.korea.WhereToGo.dto.response.festival.PutFavoriteResponseDto;
import com.korea.WhereToGo.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PutMapping("/putFavorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
            @RequestParam String contentId, @RequestParam String nickname
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = favoriteService.putFavorite(contentId, nickname);
        return response;
    }

    @GetMapping("/getAllFavoriteList")
    public ResponseEntity<? super GetAllFavoriteListResponseDto> getAllFavoriteList(
            @RequestParam String nickname
    ) {
        ResponseEntity<? super GetAllFavoriteListResponseDto> response = favoriteService.getAllFavoriteList(nickname);
        return response;
    }
}
