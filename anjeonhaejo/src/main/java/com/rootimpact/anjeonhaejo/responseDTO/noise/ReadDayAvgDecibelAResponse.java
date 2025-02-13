package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelAResponse(

        LocalDate localDate,
        double averageDecibel,
        String zone
) {

    public static ReadDayAvgDecibelAResponse of(LocalDate localDate, double averageDecibel, String zone) {
        return new ReadDayAvgDecibelAResponse(
                localDate,
                averageDecibel,
                zone
        );
    }
}
