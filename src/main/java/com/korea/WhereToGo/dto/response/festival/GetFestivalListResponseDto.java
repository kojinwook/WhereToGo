package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetFestivalListResponseDto extends ResponseDto {
    List<FestivalEntity> festivalList;

    public GetFestivalListResponseDto(List<FestivalEntity> festivalList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.festivalList = festivalList;
    }

    public static ResponseEntity<? super GetFestivalListResponseDto> success(List<FestivalEntity> festivalList){
        GetFestivalListResponseDto responseBody = new GetFestivalListResponseDto(festivalList);
        return ResponseEntity.ok(responseBody);
    }
}
