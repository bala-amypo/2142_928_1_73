package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_steps")
public class WorkflowStepConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int level;

    private String approverRole;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private WorkflowTemplate workflowTemplate;

    public WorkflowStepConfig() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public String getApproverRole() { return approverRole; }
    public void setApproverRole(String approverRole) { this.approverRole = approverRole; }

    public WorkflowTemplate getWorkflowTemplate() { return workflowTemplate; }
    public void setWorkflowTemplate(WorkflowTemplate workflowTemplate) {
        this.workflowTemplate = workflowTemplate;
    }
}
