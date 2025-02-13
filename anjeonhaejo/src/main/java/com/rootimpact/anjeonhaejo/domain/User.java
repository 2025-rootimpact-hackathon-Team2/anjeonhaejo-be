package com.rootimpact.anjeonhaejo.domain;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String userState;

    private String password;

    private String email;

    private RoleType role;

    private String taskManager;

    private String factory;

    private String department;


    public User(String username, String userState, String password, String email, RoleType role, String taskManager, WorkerLine workerLine) {
        this.username = username;
        this.userState = userState;
        this.password = password;
        this.email = email;
        this.role = role;
        this.taskManager = taskManager;
        this.workerLine = workerLine;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "worker_line_id")
    private WorkerLine workerLine;

    public void setWorkerLine(WorkerLine workerLine) {
        this.workerLine = workerLine;
    }


    @Builder
    private User(
            final String username,
            final RoleType role,
            final String factory,
            final String department
    ) {
        this.username = username;
        this.role = role;
        this.factory = factory;
        this.department = department;
    }

    public void modifyUser(
            final String username,
            final RoleType role,
            final String factory,
            final String department
    ) {
        this.username = username;
        this.role = role;
        this.factory = factory;
        this.department = department;
    }
}
