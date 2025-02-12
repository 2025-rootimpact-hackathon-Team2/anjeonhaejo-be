package com.rootimpact.anjeonhaejo.responseDTO.noise;

import com.rootimpact.anjeonhaejo.domain.Noise;

public record GetMaxDecibelResponse(

        double maxDecibel
) {
    public static GetMaxDecibelResponse from(Noise noise) {
        return new GetMaxDecibelResponse(
                noise.getMaxDecibel()
        );
    }
}
