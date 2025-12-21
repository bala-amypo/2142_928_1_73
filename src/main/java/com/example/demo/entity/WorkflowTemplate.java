package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class WorkflowTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String templateName;

    private String description;
    private Integer totalLevels;
    private Boolean active;

    public Long getId() { return id; }
    public String getTemplateName() { return templateName; }
    public String getDescription() { return description; }
    public Integer getTotalLevels() { return totalLevels; }
    public Boolean getActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setTemplateName(String templateName) { this.templateName = templateName; }
    public void setDescription(String description) { this.description = description; }
    public void setTotalLevels(Integer totalLevels) { this.totalLevels = totalLevels; }
    public void setActive(Boolean active) { this.active = active; }
}
