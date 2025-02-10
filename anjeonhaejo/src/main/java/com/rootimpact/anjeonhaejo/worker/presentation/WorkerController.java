package com.rootimpact.anjeonhaejo.worker.presentation;

import com.rootimpact.anjeonhaejo.worker.application.WorkerService;
import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.request.CreateWorkerRequest;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.response.ReadAllWorkersResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping("/workers")
    public ResponseEntity<Void> createWorker(@RequestBody CreateWorkerRequest createWorkerRequest) {
        log.info("method: createWorker, requested time: {}", LocalDateTime.now());
        Long workerId = workerService.registerWorker(createWorkerRequest);
        return ResponseEntity.created(URI.create("/api/v1/workers" + workerId)).build();
    }

    @GetMapping("/workers")
    public ResponseEntity<List<ReadAllWorkersResponse>> readAllWorkers() {
        log.info("method: readAllWorkers, requested time: {}", LocalDateTime.now());
        List<ReadAllWorkersResponse> readAllWorkersResponse = workerService.showAllWorkers();
        return ResponseEntity.ok(readAllWorkersResponse);
    }
}
