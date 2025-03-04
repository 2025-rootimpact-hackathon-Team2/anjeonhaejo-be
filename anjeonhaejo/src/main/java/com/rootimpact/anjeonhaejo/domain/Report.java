package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    private String content;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workline_id")
    private WorkerLine workerLine;

    @Column(name = "status")
    private String status;

    // Report와 Tag 사이의 ManyToMany 관계 설정
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ReportTagMap> reportTags = new ArrayList<>();

    @Builder
    public Report(String content, String title, User user, WorkerLine workerLine, String status) {
        this.content = content;
        this.title = title;
        this.user = user;
        this.workerLine = workerLine;
        this.status = status;
    }

    // 태그 추가 메서드
    public void addTag(Tag tag) {
        ReportTagMap reportTagMap = new ReportTagMap(this, tag);
        this.reportTags.add(reportTagMap);
        tag.getReportTags().add(reportTagMap);
    }

    // 태그 제거 메서드
    public void removeTag(Tag tag) {
        reportTags.removeIf(reportTagMap -> reportTagMap.getTag().equals(tag));
        tag.getReportTags().removeIf(reportTagMap -> reportTagMap.getReport().equals(this));
    }
}
