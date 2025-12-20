package com.flowforge.auth.repository;

import com.flowforge.auth.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, String> {

    Optional<UserCredentials> findByEmail(String email);

    // ✅ ADD THIS LINE
    boolean existsByEmail(String email);
}
