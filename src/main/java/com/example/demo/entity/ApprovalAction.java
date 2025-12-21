package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "approval_action")
public class ApprovalAction {
package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApprovalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestId;
    private Long approverId;
    private Integer levelNumber;
    private String action;
    private String comments;
    private LocalDateTime actionDate;

    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestid;
    private Long approverld;
    private Integer levelNumber;

    private String action;
    private String comments;

    @Column(nullable = false)
    private LocalDateTime actionDate = LocalDateTime.now();

    // getters & setters
}






