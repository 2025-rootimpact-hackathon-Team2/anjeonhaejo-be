package com.rootimpact.anjeonhaejo.repository;

import com.rootimpact.anjeonhaejo.domain.Noise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface NoiseRepository extends JpaRepository<Noise, Long> {

    Optional<Noise> findByCreatedAt(LocalDate localDate);
}
