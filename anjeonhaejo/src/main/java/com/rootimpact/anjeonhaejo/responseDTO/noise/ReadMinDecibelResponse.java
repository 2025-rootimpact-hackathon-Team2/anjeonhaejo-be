package com.rootimpact.anjeonhaejo.responseDTO.noise;

import com.rootimpact.anjeonhaejo.domain.Noise;

public record ReadMinDecibelResponse(

        double minDecibel
) {
    public static ReadMinDecibelResponse from(Noise noise) {
        return new ReadMinDecibelResponse(
                noise.getMinDecibel()
        );
    }
}
