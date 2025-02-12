package com.rootimpact.anjeonhaejo.responseDTO.noise;

import com.rootimpact.anjeonhaejo.domain.Noise;

public record GetMinDecibelResponse(

        double minDecibel
) {
    public static GetMinDecibelResponse from(Noise noise) {
        return new GetMinDecibelResponse(
                noise.getMinDecibel()
        );
    }
}
