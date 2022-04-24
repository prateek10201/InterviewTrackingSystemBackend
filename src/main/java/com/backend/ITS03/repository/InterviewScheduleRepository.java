package com.backend.ITS03.repository;

import com.backend.ITS03.entity.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Long> {
    void deleteInterviewById(Long id);

    Optional<InterviewSchedule> findUserByUserId(String userId);
}
