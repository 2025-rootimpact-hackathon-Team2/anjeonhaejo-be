package com.rootimpact.anjeonhaejo.responseDTO;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class ShowStateResponseDTO {

    private String machineName;

    private String zoneName;

    private int threshold;

    public ShowStateResponseDTO(String machineName, String zoneName, int threshold) {
        this.machineName = machineName;
        this.zoneName = zoneName;
        this.threshold = threshold;
    }
}
