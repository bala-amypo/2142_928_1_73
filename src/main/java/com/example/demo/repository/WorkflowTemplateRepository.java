package com.example.demo.repository;

import com.example.demo.entity.WorkflowTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowTemplateRepository extends JpaRepository<WorkflowTemplate, Long> {

    List<WorkflowTemplate> findByActive(boolean active);
}
