package com.rootimpact.anjeonhaejo.domain;

import com.rootimpact.anjeonhaejo.domain.enumration.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public User(String nickname, String userState, String password, String email, RoleType role, String taskManager) {
        this.username = nickname;
        this.userState = userState;
        this.password = password;
        this.email = email;
        this.role = role;
        this.taskManager = taskManager;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "worker_line_id")
    private WorkerLine workerLine;



}
