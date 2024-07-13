package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchFestivalListResponseDto extends ResponseDto {

    private List<FestivalEntity> festivalList;

    public GetSearchFestivalListResponseDto(List<FestivalEntity> festivalList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.festivalList = festivalList;
    }

    public static ResponseEntity<? super GetSearchFestivalListResponseDto> success(List<FestivalEntity> festivalList){
        GetSearchFestivalListResponseDto responseBody = new GetSearchFestivalListResponseDto(festivalList);
        return ResponseEntity.ok(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistFestival(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseBody);
    }
}
