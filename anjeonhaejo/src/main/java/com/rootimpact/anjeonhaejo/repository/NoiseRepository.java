package com.rootimpact.anjeonhaejo.repository;

import com.rootimpact.anjeonhaejo.domain.Noise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NoiseRepository extends JpaRepository<Noise, Long> {

    Optional<Noise> findByCreatedAt(LocalDate localDate);

    List<Noise> findAllByCreatedAt(LocalDate localDate);

    @Query("SELECT n FROM Noise n WHERE YEAR(n.createdAt) = :year AND MONTH(n.createdAt) = :month")
    List<Noise> findByYearAndMonth(@Param("year") int year, @Param("month") int month);
}
