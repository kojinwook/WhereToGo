package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);
    UserEntity findByNickname(String nickname);

}
