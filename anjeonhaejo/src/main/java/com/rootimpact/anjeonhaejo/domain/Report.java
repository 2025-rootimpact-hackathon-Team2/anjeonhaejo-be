package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    private Set<ReportTagMap> tagMap = new HashSet<>();

    @Builder
    public Report(String content, User user, WorkerLine workerLine) {
        this.content = content;
        this.user = user;
        this.workerLine = workerLine;
    }

    // 태그 추가 메서드
    public void addTag(Tag tag) {
        ReportTagMap reportTagMap = new ReportTagMap(this, tag);
        this.tagMap.add(reportTagMap);
        tag.getReportMap().add(reportTagMap);
    }

    // 태그 제거 메서드
    public void removeTag(Tag tag) {
        tagMap.removeIf(reportTagMap -> reportTagMap.getTag().equals(tag));
        tag.getReportMap().removeIf(reportTagMap -> reportTagMap.getReport().equals(this));
    }
}
