package com.example.skyeng.repository;

import com.example.skyeng.entity.MailingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends JpaRepository<MailingEntity, Long> {
}
