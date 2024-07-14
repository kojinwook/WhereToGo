package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.FavoriteEntity;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long>{
    FavoriteEntity findByUserAndFestival(UserEntity user, FestivalEntity festival);
    List<FavoriteEntity> findAllByUserNickname(String nickname);
}
