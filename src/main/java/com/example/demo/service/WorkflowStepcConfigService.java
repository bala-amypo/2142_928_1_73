package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;

@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository workflowStepConfigRepository;

    public WorkflowStepConfigService(WorkflowStepConfigRepository workflowStepConfigRepository) {
        this.workflowStepConfigRepository = workflowStepConfigRepository;
    }

    public WorkflowStepConfig createstep(WorkflowStepConfig step) {
        return workflowStepConfigRepository.save(step);
    }

    public List<WorkflowStepConfig> getStepsForTemplate(Long templateld) {
        return workflowStepConfigRepository
                .findByTemplateldOrderByLevelNumberAsc(templateld);
    }
}
