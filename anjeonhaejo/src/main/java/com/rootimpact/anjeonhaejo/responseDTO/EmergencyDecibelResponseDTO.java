package com.rootimpact.anjeonhaejo.responseDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class EmergencyDecibelResponseDTO {

    private LocalDateTime timestamp;

    private Long workerZone;

    private double decibel;

    private String soundClass;

    private String transcription;

    public EmergencyDecibelResponseDTO(LocalDateTime timestamp, Long workerZone, double decibel, String soundClass, String transcription) {
        this.timestamp = timestamp;
        this.workerZone = workerZone;
        this.decibel = decibel;
        this.soundClass = soundClass;
        this.transcription = transcription;
    }
}
