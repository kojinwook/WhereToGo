package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetFestivalResponseDto extends ResponseDto {

private FestivalEntity festival;

    public GetFestivalResponseDto(FestivalEntity festival) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.festival = festival;
    }

    public static ResponseEntity<GetFestivalResponseDto> success(FestivalEntity festival){
        GetFestivalResponseDto responseBody = new GetFestivalResponseDto(festival);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistFestival(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
