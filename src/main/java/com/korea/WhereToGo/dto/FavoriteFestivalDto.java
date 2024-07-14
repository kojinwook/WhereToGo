package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.FestivalEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteFestivalDto {

    private Long id;
    private String contentId;
    private String title;

    public FavoriteFestivalDto(FestivalEntity festival) {
        this.id = festival.getId();
        this.contentId = festival.getContentId();
        this.title = festival.getTitle();
    }
}
