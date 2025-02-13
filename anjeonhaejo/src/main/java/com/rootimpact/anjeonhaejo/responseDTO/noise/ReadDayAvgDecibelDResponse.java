package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelDResponse(

        LocalDate localDate,
        double averageDecibel,
        String zone
) {

    public static ReadDayAvgDecibelDResponse of(LocalDate localDate, double averageDecibel, String zone) {
        return new ReadDayAvgDecibelDResponse(
                localDate,
                averageDecibel,
                zone
        );
    }
}
