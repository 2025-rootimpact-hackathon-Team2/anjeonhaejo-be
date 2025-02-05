package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "audio_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AudioAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String soundClass;

    private String transcription;

    @ElementCollection
    private List<String> detectedKeywords; // List로 변환된 값을 저장
}
