package com.korea.WhereToGo.dto.response.rate;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
public class GeAverageRateResponseDto extends ResponseDto {

    private Map<String, Double> average;

    private GeAverageRateResponseDto(Map<String, Double> average) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.average = average;
    }

    public static ResponseEntity<? super GeAverageRateResponseDto> success(Map<String, Double> average) {
        GeAverageRateResponseDto responseBody = new GeAverageRateResponseDto(average);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistFestival() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
