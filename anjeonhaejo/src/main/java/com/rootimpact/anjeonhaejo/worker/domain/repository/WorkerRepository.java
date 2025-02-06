package com.rootimpact.anjeonhaejo.worker.domain.repository;

import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {


}
