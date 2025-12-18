
WorkflowTemplate

package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "workflow_template")
public class WorkflowTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String templateName;

    private String description;
    private Integer totalLevels;
    private Boolean active;

    // getters & setters
}