package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelCResponse(

        LocalDate localDate,
        double averageDecibel,
        String zone
) {

    public static ReadDayAvgDecibelCResponse of(LocalDate localDate, double averageDecibel, String zone) {
        return new ReadDayAvgDecibelCResponse(
                localDate,
                averageDecibel,
                zone
        );
    }
}
