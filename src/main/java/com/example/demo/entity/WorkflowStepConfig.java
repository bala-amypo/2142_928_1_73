package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateId;   // IMPORTANT: no relation
    private Integer levelNumber;
    private String approverRole;
    private Boolean isFinalStep;
    private String instructions;

    public Long getId() { return id; }
    public Long getTemplateId() { return templateId; }
    public Integer getLevelNumber() { return levelNumber; }
    public String getApproverRole() { return approverRole; }
    public Boolean getIsFinalStep() { return isFinalStep; }
    public String getInstructions() { return instructions; }

    public void setId(Long id) { this.id = id; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }
    public void setLevelNumber(Integer levelNumber) { this.levelNumber = levelNumber; }
    public void setApproverRole(String approverRole) { this.approverRole = approverRole; }
    public void setIsFinalStep(Boolean isFinalStep) { this.isFinalStep = isFinalStep; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}
