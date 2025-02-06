package com.rootimpact.anjeonhaejo.worker.presentation.dto.response;

import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;

public record ReadAllWorkersResponse(

        Long workerId,

        String name,

        String department,

        String significant,

        String condition
) {

    public static ReadAllWorkersResponse from(Worker worker) {
        return new ReadAllWorkersResponse(
                worker.getWorkerId(),
                worker.getName(),
                worker.getDepartment(),
                worker.getSignificant(),
                worker.getCondition()
        );
    }
}
