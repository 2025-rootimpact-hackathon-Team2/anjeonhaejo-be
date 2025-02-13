package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelBResponse(

        LocalDate localDate,
        double averageDecibel,
        String zone
) {

    public static ReadDayAvgDecibelBResponse of(LocalDate localDate, double averageDecibel, String zone) {
        return new ReadDayAvgDecibelBResponse(
                localDate,
                averageDecibel,
                zone
        );
    }
}
