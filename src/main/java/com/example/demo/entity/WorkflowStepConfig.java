package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_step_config")
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateld;   // MUST be Long (SRS constraint)
    private Integer levelNumber;
    private String approverRole;
    private Boolean isFinalStep;
    private String instructions;

    public WorkflowStepConfig() {
    }

    public Long getId() {
        return id;
    }

    public Long getTemplateld() {
        return templateld;
    }

    public void setTemplateld(Long templateld) {
        this.templateld = templateld;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getApproverRole() {
        return approverRole;
    }

    public void setApproverRole(String approverRole) {
        this.approverRole = approverRole;
    }

    public Boolean getIsFinalStep() {
        return isFinalStep;
    }

    public void setIsFinalStep(Boolean isFinalStep) {
        this.isFinalStep = isFinalStep;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
