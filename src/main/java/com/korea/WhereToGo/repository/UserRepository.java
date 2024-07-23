package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    UserEntity findByNickname(String nickname);
    @Query("SELECT u FROM user u WHERE u.role != 'ROLE_ADMIN'")
    List<UserEntity> findAllNonAdminUsers();
    List<UserEntity> findTop5ByOrderByTemperatureDesc();

}
