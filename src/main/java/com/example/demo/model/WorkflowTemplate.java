package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "workflow_templates", uniqueConstraints = {
    @UniqueConstraint(columnNames = "templateName")
})
@Data
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
}