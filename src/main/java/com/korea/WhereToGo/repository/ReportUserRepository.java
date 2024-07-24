package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ReportUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportUserRepository extends JpaRepository<ReportUserEntity, Long> {
}
