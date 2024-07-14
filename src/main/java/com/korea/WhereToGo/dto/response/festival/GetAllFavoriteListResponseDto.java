package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.FavoriteFestivalDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllFavoriteListResponseDto extends ResponseDto {

    private List<FavoriteFestivalDto> favoriteList;

    public GetAllFavoriteListResponseDto(List<FavoriteFestivalDto> favoriteList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoriteList = favoriteList;
    }

    public static ResponseEntity<? super GetAllFavoriteListResponseDto> success(List<FavoriteFestivalDto> favoriteList) {
        GetAllFavoriteListResponseDto responseBody = new GetAllFavoriteListResponseDto(favoriteList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistFavorite() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FAVORITE, ResponseMessage.NOT_EXISTED_FAVORITE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
