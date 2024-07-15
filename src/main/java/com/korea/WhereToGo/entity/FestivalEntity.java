package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.festival.PatchFestivalRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "content_id", unique = true)
    private String contentId;
    @Column(name = "content_type_id")
    private String contentTypeId;
    @Column(name = "homepage")
    private String homepage;
    @Column(name = "favorite_count", nullable = false)
    private int favoriteCount = 0;
    @ElementCollection
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteEntity> likes = new ArrayList<>();

    public void patchFestival(PatchFestivalRequestDto dto){
        this.title = dto.getTitle();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.address1 = dto.getAddress1();
        this.firstImage = dto.getFirstImage();
        this.tel = dto.getTel();
        this.contentId = dto.getContentId();
        this.homepage = dto.getHomepage();
        this.tags = dto.getTags();
    }

    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }

    public void decreaseFavoriteCount() {
        if (this.favoriteCount > 0) {
            this.favoriteCount--;
        }
    }
}
