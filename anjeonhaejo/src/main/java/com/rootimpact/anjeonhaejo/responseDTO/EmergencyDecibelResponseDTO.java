package com.rootimpact.anjeonhaejo.responseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Builder
public class EmergencyDecibelResponseDTO {

    @Schema(description = "시간", example = "2024-02-06 00:00:01")
    private LocalDateTime timestamp;

    @Schema(description = "구역 이름", example = "A구역")
    private Long workerZone;

    @Schema(description = "데시벨", example = "105db")
    private double decibel;

    @Schema(description = "소리 클래스", example = "Explosion")
    private String soundClass;

    @Schema(description = "설명", example = "Powering up fire")
    private String transcription;

    public EmergencyDecibelResponseDTO(LocalDateTime timestamp, Long workerZone, double decibel, String soundClass, String transcription) {
        this.timestamp = timestamp;
        this.workerZone = workerZone;
        this.decibel = decibel;
        this.soundClass = soundClass;
        this.transcription = transcription;
    }
}
