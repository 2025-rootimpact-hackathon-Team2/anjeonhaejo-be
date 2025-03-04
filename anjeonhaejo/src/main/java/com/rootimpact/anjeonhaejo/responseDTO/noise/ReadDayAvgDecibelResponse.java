package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelResponse(

        LocalDate localDate,
        double averageDecibel,
        String zone
) {

    public static ReadDayAvgDecibelResponse of(LocalDate localDate, double averageDecibel, String zone) {
        return new ReadDayAvgDecibelResponse(
                localDate,
                averageDecibel,
                zone
        );
    }
}
