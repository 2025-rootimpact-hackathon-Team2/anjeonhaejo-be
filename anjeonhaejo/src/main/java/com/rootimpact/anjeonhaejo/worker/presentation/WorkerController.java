package com.rootimpact.anjeonhaejo.worker.presentation;

import com.rootimpact.anjeonhaejo.worker.application.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;
}
