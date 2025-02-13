package com.rootimpact.anjeonhaejo.repository;

import com.rootimpact.anjeonhaejo.domain.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Page<Report> findAllByOrderByCreateTimeDesc(Pageable pageable);
}
