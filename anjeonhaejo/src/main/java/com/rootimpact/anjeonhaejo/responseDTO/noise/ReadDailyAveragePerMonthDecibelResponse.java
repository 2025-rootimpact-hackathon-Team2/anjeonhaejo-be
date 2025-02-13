package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.util.List;

public record ReadDailyAveragePerMonthDecibelResponse(

        List<ReadDayAvgDecibelAResponse> dailyAverageDecibelAs,
        List<ReadDayAvgDecibelBResponse> dailyAverageDecibelBs,
        List<ReadDayAvgDecibelCResponse> dailyAverageDecibelCs,
        List<ReadDayAvgDecibelDResponse> dailyAverageDecibelDs
) {

    public static ReadDailyAveragePerMonthDecibelResponse of(
            List<ReadDayAvgDecibelAResponse> dailyAverageDecibelAs,
            List<ReadDayAvgDecibelBResponse> dailyAverageDecibelBs,
            List<ReadDayAvgDecibelCResponse> dailyAverageDecibelCs,
            List<ReadDayAvgDecibelDResponse> dailyAverageDecibelDs
    ) {
        return new ReadDailyAveragePerMonthDecibelResponse(
                dailyAverageDecibelAs,
                dailyAverageDecibelBs,
                dailyAverageDecibelCs,
                dailyAverageDecibelDs
        );
    }
}
