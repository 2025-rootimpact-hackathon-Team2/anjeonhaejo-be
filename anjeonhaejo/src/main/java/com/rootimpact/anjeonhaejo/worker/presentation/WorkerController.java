package com.rootimpact.anjeonhaejo.worker.presentation;

import com.rootimpact.anjeonhaejo.worker.application.WorkerService;
import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.response.ReadAllWorkersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/workers")
    public ResponseEntity<List<ReadAllWorkersResponse>> readAllWorkers() {
        log.info("method: readAllWorkers, requested time: {}", LocalDateTime.now());
        List<ReadAllWorkersResponse> readAllWorkersResponse = workerService.showAllWorkers();
        return ResponseEntity.ok(readAllWorkersResponse);
    }
}
