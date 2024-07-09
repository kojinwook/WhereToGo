package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.CertificationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CertificationRepository extends JpaRepository<CertificationEntity, String> {
    CertificationEntity findByUserId(String userId);

    @Transactional
    void deleteByUserId(String userId);
}
