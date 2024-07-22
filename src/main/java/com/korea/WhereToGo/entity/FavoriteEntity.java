package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favorite")
@Table(name = "favorite")
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "content_id", referencedColumnName = "content_id", nullable = false)
    private FestivalEntity festival;

    public FavoriteEntity(UserEntity user, FestivalEntity festival) {
        this.user = user;
        this.festival = festival;
    }

}
