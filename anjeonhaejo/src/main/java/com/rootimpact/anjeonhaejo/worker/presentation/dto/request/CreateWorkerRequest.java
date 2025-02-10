package com.rootimpact.anjeonhaejo.worker.presentation.dto.request;

public record CreateWorkerRequest(

        String name,
        String department,
        String significant,
        String condition
) {
}
