package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUserId(String userId);
    boolean existByEmail(String email);
    boolean existByNickname(String nickname);
    UserEntity findByUserId(String userId);
    UserEntity findByEmail(String email);

}
