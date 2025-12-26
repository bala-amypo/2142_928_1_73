package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "workflow_step_configs")
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
    
    // Constructors
    public WorkflowStepConfig() {}
    
    public WorkflowStepConfig(Long templateId, Integer levelNumber, String approverRole, Boolean isFinalStep, String instructions) {
        this.templateId = templateId;
        this.levelNumber = levelNumber;
        this.approverRole = approverRole;
        this.isFinalStep = isFinalStep;
        this.instructions = instructions;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowStepConfig that = (WorkflowStepConfig) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(templateId, that.templateId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, templateId);
    }
    
    @Override
    public String toString() {
        return "WorkflowStepConfig{" +
               "id=" + id +
               ", templateId=" + templateId +
               ", levelNumber=" + levelNumber +
               ", approverRole='" + approverRole + '\'' +
               ", isFinalStep=" + isFinalStep +
               ", instructions='" + instructions + '\'' +
               '}';
    }
}