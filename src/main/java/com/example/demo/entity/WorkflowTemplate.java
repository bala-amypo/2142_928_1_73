package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_templates")
public class WorkflowTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String templateName;

    private String description;

    private int totalLevels;

    private boolean active;

    public WorkflowTemplate() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTemplateName() { return templateName; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTotalLevels() { return totalLevels; }
    public void setTotalLevels(int totalLevels) { this.totalLevels = totalLevels; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
