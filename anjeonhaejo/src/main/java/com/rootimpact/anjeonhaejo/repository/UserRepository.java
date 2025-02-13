package com.rootimpact.anjeonhaejo.repository;


import com.rootimpact.anjeonhaejo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserByEmail(String email);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByPassword(String password);
}
