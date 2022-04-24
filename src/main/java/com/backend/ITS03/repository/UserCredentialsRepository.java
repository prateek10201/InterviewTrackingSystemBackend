package com.backend.ITS03.repository;

import com.backend.ITS03.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {

    void deleteUserById(Long id);

    Optional<UserCredentials> findUserById(Long id);

    Optional<UserCredentials> findUserByUserId(String userId);
}
