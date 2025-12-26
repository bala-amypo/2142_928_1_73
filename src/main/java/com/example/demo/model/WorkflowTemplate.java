package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "workflow_templates", uniqueConstraints = {
    @UniqueConstraint(columnNames = "templateName")
})
public class WorkflowTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String templateName;
    
    private String description;
    
    @Column(nullable = false)
    private Integer totalLevels;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    // Constructors
    public WorkflowTemplate() {}
    
    public WorkflowTemplate(String templateName, String description, Integer totalLevels, Boolean active) {
        this.templateName = templateName;
        this.description = description;
        this.totalLevels = totalLevels;
        this.active = active;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTemplateName() {
        return templateName;
    }
    
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Integer getTotalLevels() {
        return totalLevels;
    }
    
    public void setTotalLevels(Integer totalLevels) {
        this.totalLevels = totalLevels;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkflowTemplate that = (WorkflowTemplate) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(templateName, that.templateName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, templateName);
    }
    
    @Override
    public String toString() {
        return "WorkflowTemplate{" +
               "id=" + id +
               ", templateName='" + templateName + '\'' +
               ", description='" + description + '\'' +
               ", totalLevels=" + totalLevels +
               ", active=" + active +
               '}';
    }
}