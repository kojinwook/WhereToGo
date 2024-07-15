package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.FavoriteFestivalDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.festival.GetAllFavoriteListResponseDto;
import com.korea.WhereToGo.dto.response.festival.PutFavoriteResponseDto;
import com.korea.WhereToGo.entity.FavoriteEntity;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.FavoriteRepository;
import com.korea.WhereToGo.repository.FestivalRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImplement implements FavoriteService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final FestivalRepository festivalRepository;

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(String contentId, String nickname) {
        try {
            boolean existedUser = userRepository.existsByNickname(nickname);
            if (!existedUser) return PutFavoriteResponseDto.notExistUser();

            FestivalEntity festivalEntity = festivalRepository.findByContentId(contentId);
            if (festivalEntity == null) return PutFavoriteResponseDto.notExistFestival();

            UserEntity userEntity = userRepository.findByNickname(nickname);

            FavoriteEntity favoriteEntity = favoriteRepository.findByUserAndFestival(userEntity, festivalEntity);
            if (favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(userEntity, festivalEntity);
                favoriteRepository.save(favoriteEntity);
                festivalEntity.increaseFavoriteCount();
            } else {
                favoriteRepository.delete(favoriteEntity);
                festivalEntity.decreaseFavoriteCount();
            }
            festivalRepository.save(festivalEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetAllFavoriteListResponseDto> getAllFavoriteList(String nickname) {
        List<FavoriteEntity> favoriteList;
        List<FavoriteFestivalDto> favoriteFestivalDtos;
        try {
            boolean existedUser = userRepository.existsByNickname(nickname);
            if (!existedUser) return GetAllFavoriteListResponseDto.notExistUser();

            favoriteList = favoriteRepository.findAllByUserNickname(nickname);
//            if (favoriteList.isEmpty()) return GetAllFavoriteListResponseDto.notExistFavorite();

            favoriteFestivalDtos = favoriteList.stream()
                    .map(favoriteEntity -> new FavoriteFestivalDto(favoriteEntity.getFestival()))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllFavoriteListResponseDto.success(favoriteFestivalDtos);
    }

}
