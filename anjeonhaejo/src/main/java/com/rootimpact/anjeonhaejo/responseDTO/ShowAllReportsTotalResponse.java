package com.rootimpact.anjeonhaejo.responseDTO;

import com.rootimpact.anjeonhaejo.domain.Report;

import java.time.LocalDateTime;

public record ShowAllReportsTotalResponse(

        LocalDateTime createdAt,
        String workingZone,
        String tag,
        String significant,
        String status,
        String workerName

) {
    public static ShowAllReportsTotalResponse from(Report report) {
        return new ShowAllReportsTotalResponse(
                report.getCreateTime(),
                report.getWorkerLine().getZoneName(),
                report.getTitle(),
                report.getContent(),
                report.getStatus(),
                report.getUser().getUsername()
        );
    }
}
