package com.korea.WhereToGo.dto.response.rate;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.OptionalDouble;

@Getter
public class GetRateAverageResponseDto extends ResponseDto {
    private OptionalDouble average;

    private GetRateAverageResponseDto(OptionalDouble average) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.average = average;
    }

    public static ResponseEntity<? super GetRateAverageResponseDto> success(OptionalDouble average) {
        GetRateAverageResponseDto responseBody = new GetRateAverageResponseDto(average);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistFestival() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
