package com.rootimpact.anjeonhaejo.responseDTO.noise;

import java.time.LocalDate;

public record ReadDayAvgDecibelResponse(

        LocalDate localDate,
        double averageDecibel
) {

    public static ReadDayAvgDecibelResponse of(LocalDate localDate, double averageDecibel) {
        return new ReadDayAvgDecibelResponse(
                localDate,
                averageDecibel
        );
    }
}
