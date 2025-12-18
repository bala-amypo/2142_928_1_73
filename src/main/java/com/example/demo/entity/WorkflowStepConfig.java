package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_step_config")
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long templateld;

    private Integer levelNumber;
    private String approverRole;
    private Boolean isFinalStep;
    private String instructions;

    // getters & setters
}