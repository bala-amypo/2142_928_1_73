package com.example.demo.repository;

import com.example.demo.entity.WorkflowStepConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkflowStepConfigRepository
        extends JpaRepository<WorkflowStepConfig, Long> {

    List<WorkflowStepConfig> findByWorkflowTemplate_IdOrderByLevelAsc(Long templateId);
}
