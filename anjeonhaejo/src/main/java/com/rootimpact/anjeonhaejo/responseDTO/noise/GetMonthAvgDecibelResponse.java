package com.rootimpact.anjeonhaejo.responseDTO.noise;

import com.rootimpact.anjeonhaejo.domain.Noise;

public record GetMonthAvgDecibelResponse(

        double averageDecibel
) {
    public static GetMonthAvgDecibelResponse from(Noise noise) {
        return new GetMonthAvgDecibelResponse(
                noise.getAverageDecibel()
        );
    }
}
