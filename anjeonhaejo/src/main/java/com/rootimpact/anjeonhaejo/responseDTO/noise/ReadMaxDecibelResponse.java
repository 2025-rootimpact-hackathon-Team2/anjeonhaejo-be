package com.rootimpact.anjeonhaejo.responseDTO.noise;

import com.rootimpact.anjeonhaejo.domain.Noise;

public record ReadMaxDecibelResponse(

        double maxDecibel
) {
    public static ReadMaxDecibelResponse from(Noise noise) {
        return new ReadMaxDecibelResponse(
                noise.getMaxDecibel()
        );
    }
}
