package com.rootimpact.anjeonhaejo.repository;


import com.rootimpact.anjeonhaejo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserByEmail(String email);

    Optional<User> findById(Long id);
}
