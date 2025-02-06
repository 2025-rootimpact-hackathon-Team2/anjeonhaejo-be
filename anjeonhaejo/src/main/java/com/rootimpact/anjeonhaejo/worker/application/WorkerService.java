package com.rootimpact.anjeonhaejo.worker.application;

import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import com.rootimpact.anjeonhaejo.worker.domain.repository.WorkerRepository;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.response.ReadAllWorkersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final WorkerRepository workerRepository;

    @Transactional
    public List<ReadAllWorkersResponse> showAllWorkers() {
        return workerRepository.findAll()
                .stream()
                .map(ReadAllWorkersResponse::from)
                .toList();
    }
}
