package com.korea.WhereToGo.entity;

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
    private Integer id;

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
    @Column(name = "member_id")
    private Integer memberId;
}
