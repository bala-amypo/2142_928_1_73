package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workflow_step_configs")
@Data
public class WorkflowStepConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long templateId;
    
    @Column(nullable = false)
    private Integer levelNumber;
    
    @Column(nullable = false)
    private String approverRole;
    
    @Column(nullable = false)
    private Boolean isFinalStep = false;
    
    private String instructions;
}