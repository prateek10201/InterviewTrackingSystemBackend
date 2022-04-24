package com.backend.ITS03.repository;

import com.backend.ITS03.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    void deleteUserById(Long id);

    Optional<List<UserProfile>> findUserByDesignation(String designation);

    Optional<List<UserProfile>> findUserByPrimarySkillSet(String primarySkillSet);

    Optional<List<UserProfile>> findUserBySecondarySkillSet(String secondarySkillSet);

    Optional<UserProfile> findUserByUserId(String userId);
}
