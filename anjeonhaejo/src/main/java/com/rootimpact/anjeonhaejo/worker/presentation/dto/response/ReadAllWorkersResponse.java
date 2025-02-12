package com.rootimpact.anjeonhaejo.worker.presentation.dto.response;

import com.rootimpact.anjeonhaejo.worker.domain.entity.Worker;
import io.swagger.v3.oas.annotations.media.Schema;

public record ReadAllWorkersResponse(

        @Schema(description = "작업자 id", example = "001")
        Long workerId,

        @Schema(description = "작업자 이름", example = "전규리")
        String name,

        @Schema(description = "부서 이름", example = "기계 설비")
        String department,

        @Schema(description = "상태", example = "정상")
        String significant,

        @Schema(description = "특이사항", example = "없음")
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
