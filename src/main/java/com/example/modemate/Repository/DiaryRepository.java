package com.example.modemate.Repository;

import com.example.modemate.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllById (@Param("id") Long id);
}