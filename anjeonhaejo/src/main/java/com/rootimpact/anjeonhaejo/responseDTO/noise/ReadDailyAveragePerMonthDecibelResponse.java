package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.util.List;

public record ReadDailyAveragePerMonthDecibelResponse(

        List<ReadDayAvgDecibelResponse> dailyAverageDecibels
) {

    public static ReadDailyAveragePerMonthDecibelResponse from(List<ReadDayAvgDecibelResponse> dailyAverageDecibels) {
        return new ReadDailyAveragePerMonthDecibelResponse(
                dailyAverageDecibels
        );
    }
}
