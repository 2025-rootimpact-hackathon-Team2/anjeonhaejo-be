package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class WorkerLine extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "workline_id")
    private Long id;

    private String zoneName;

    private String workState;

    private int threshold = 0;


    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Machine> machines = new ArrayList<>();

    @Builder
    public WorkerLine(String zoneName, String workState, int threshold) {
        this.zoneName = zoneName;
        this.workState = workState;
        if (threshold > 0) {
            this.threshold = threshold;  // threshold 값이 0보다 크면 해당 값으로 설정
        }
    }

    // threshold 값을 수정할 수 있는 setter 메서드 추가
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    // threshold 값을 1 증가시키는 메서드
    public void incrementThreshold() {
        this.threshold++;
    }



}
