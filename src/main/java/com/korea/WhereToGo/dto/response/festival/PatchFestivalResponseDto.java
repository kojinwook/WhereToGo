package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchFestivalResponseDto extends ResponseDto {

    private PatchFestivalResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchFestivalResponseDto> success() {
        PatchFestivalResponseDto responseBody = new PatchFestivalResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistFestival() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
