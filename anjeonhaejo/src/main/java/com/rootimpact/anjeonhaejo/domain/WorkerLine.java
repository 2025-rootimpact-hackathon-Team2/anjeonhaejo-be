package com.rootimpact.anjeonhaejo.domain;

import jakarta.persistence.*;
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
public class WorkerLine {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "workLine_id")
    private Long id;

    private String zoneName;

    private String workeState;

    private Long temperature;

    private Long oxygenSaturation;

    private Long co2Level;

    private Long flammableGasLevel;

    private Long noiseLevel;

    private Long vibrationLevel;

    @OneToMany(mappedBy = "workerLine", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<User> userCount = new ArrayList<>();

}
