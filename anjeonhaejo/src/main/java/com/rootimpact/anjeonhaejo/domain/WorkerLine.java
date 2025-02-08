package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Long temperature;

    private Long oxygenSaturation;

    private Long co2Level;

    private Long flammableGasLevel;

    private Long noiseLevel;

    private Long vibrationLevel;

    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Report> reports = new ArrayList<>();

    @Builder
    public WorkerLine(String zoneName, String workState, Long temperature, Long oxygenSaturation, Long co2Level, Long flammableGasLevel, Long noiseLevel, Long vibrationLevel) {
        this.zoneName = zoneName;
        this.workState = workState;
        this.temperature = temperature;
        this.oxygenSaturation = oxygenSaturation;
        this.co2Level = co2Level;
        this.flammableGasLevel = flammableGasLevel;
        this.noiseLevel = noiseLevel;
        this.vibrationLevel = vibrationLevel;
    }
}
