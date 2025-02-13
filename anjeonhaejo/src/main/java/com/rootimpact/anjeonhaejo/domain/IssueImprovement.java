package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue_improvement")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueImprovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueImprovementId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audio_analysis_id", unique = true)
    private AudioAnalysis audioAnalysis;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", unique = true)
    private Report report;
}
