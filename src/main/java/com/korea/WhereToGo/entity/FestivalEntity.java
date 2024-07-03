package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.festival.PatchFestivalRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "festival")
@Table(name = "festival")
public class FestivalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "eventStartDate")
    private String startDate;
    @Column(name = "eventEndDate")
    private String endDate;
    @Column(name = "address1")
    private String address1;
    @Column(name = "first_image")
    private String firstImage;
    @Column(name = "tel")
    private String tel;
    @Column(name = "mapX")
    private String mapX;
    @Column(name = "mapY")
    private String mapY;
    @Column(name = "modifyDate")
    private String modifyDate;
    @Column(name = "areaCode")
    private String areaCode;
    @Column(name = "sigunguCode")
    private String sigunguCode;
    @Column(name = "content_id")
    private String contentId;
    @Column(name = "content_type_id")
    private String contentTypeId;
    @Column(name = "homepage")
    private String homepage;

    public void patchFestival(PatchFestivalRequestDto dto){
        this.title = dto.getTitle();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.address1 = dto.getAddress1();
        this.firstImage = dto.getFirstImage();
        this.tel = dto.getTel();
        this.mapX = dto.getMapX();
        this.mapY = dto.getMapY();
        this.modifyDate = dto.getModifyDate();
        this.areaCode = dto.getAreaCode();
        this.sigunguCode = dto.getSigunguCode();
        this.contentId = dto.getContentId();
        this.contentTypeId = dto.getContentTypeId();
        this.homepage = dto.getHomepage();
    }
}
