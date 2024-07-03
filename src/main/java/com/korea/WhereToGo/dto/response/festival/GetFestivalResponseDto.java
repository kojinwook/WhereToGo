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
    private String title;
    private String startDate;
    private String endDate;
    private String address1;
    private String firstImage;
    private String tel;
    private String mapX;
    private String mapY;
    private String modifyDate;
    private String areaCode;
    private String sigunguCode;
    private String contentId;
    private String contentTypeId;
    private String homepage;

    public GetFestivalResponseDto(FestivalEntity entity) {
        this.title = entity.getTitle();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.address1 = entity.getAddress1();
        this.firstImage = entity.getFirstImage();
        this.tel = entity.getTel();
        this.mapX = entity.getMapX();
        this.mapY = entity.getMapY();
        this.modifyDate = entity.getModifyDate();
        this.areaCode = entity.getAreaCode();
        this.sigunguCode = entity.getSigunguCode();
        this.contentId = entity.getContentId();
        this.contentTypeId = entity.getContentTypeId();
        this.homepage = entity.getHomepage();
    }

    public static ResponseEntity<GetFestivalResponseDto> success(FestivalEntity festivalEntity){
        GetFestivalResponseDto responseBody = new GetFestivalResponseDto(festivalEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistFestival(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
