package com.example.skyeng.repository;

import com.example.skyeng.entity.PostOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostOfficeRepository extends JpaRepository<PostOfficeEntity, Long> {
}
