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
public class GetTop5FestivalListResponseDto extends ResponseDto {

    private List<FestivalEntity> festivalList;

    public GetTop5FestivalListResponseDto(List<FestivalEntity> festivalList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.festivalList = festivalList;
    }

    public static ResponseEntity<? super GetTop5FestivalListResponseDto> success(List<FestivalEntity> festivalList) {
        GetTop5FestivalListResponseDto responseBody = new GetTop5FestivalListResponseDto(festivalList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
