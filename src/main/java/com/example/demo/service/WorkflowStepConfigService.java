package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;

@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository workflowstepconfigRepository;

    // ✅ Constructor order MUST match
    public WorkflowStepConfigService(WorkflowStepConfigRepository workflowstepconfigRepository) {
        this.workflowstepconfigRepository = workflowstepconfigRepository;
    }

    public WorkflowStepConfig createstep(WorkflowStepConfig step) {
        return workflowstepconfigRepository.save(step);
    }

    public List<WorkflowStepConfig> getStepsForTemplate(Long templateld) {
        return workflowstepconfigRepository
                .findByTemplateldOrderByLevelNumberAsc(templateld);
    }
}