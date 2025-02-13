package com.rootimpact.anjeonhaejo.responseDTO;

import java.util.List;

public record ShowAllReportsWithTotalPageResponse(

        int totalPage,
        List<ShowAllReportsTotalResponse> reports
) {

    public static ShowAllReportsWithTotalPageResponse of(int totalPage, List<ShowAllReportsTotalResponse> reports) {
        return new ShowAllReportsWithTotalPageResponse(
                totalPage,
                reports
        );
    }
}
