package com.rootimpact.anjeonhaejo.worker.application;

import com.rootimpact.anjeonhaejo.worker.domain.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;


}
