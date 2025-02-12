package com.rootimpact.anjeonhaejo.worker.presentation;

import com.rootimpact.anjeonhaejo.worker.application.WorkerService;
import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.request.CreateWorkerRequest;
import com.rootimpact.anjeonhaejo.worker.presentation.dto.response.ReadAllWorkersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "작업자 생성", description = "작업자를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "작업자 생성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @PostMapping("/workers")
    public ResponseEntity<Void> createWorker(@RequestBody CreateWorkerRequest createWorkerRequest) {
        log.info("method: createWorker, requested time: {}", LocalDateTime.now());
        Long workerId = workerService.registerWorker(createWorkerRequest);
        return ResponseEntity.created(URI.create("/api/v1/workers" + workerId)).build();
    }

    @Operation(summary = "작업자 조회", description = "전체 작업자를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "작업자 조회 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    @GetMapping("/workers")
    public ResponseEntity<List<ReadAllWorkersResponse>> readAllWorkers() {
        log.info("method: readAllWorkers, requested time: {}", LocalDateTime.now());
        List<ReadAllWorkersResponse> readAllWorkersResponse = workerService.showAllWorkers();
        return ResponseEntity.ok(readAllWorkersResponse);
    }
}
