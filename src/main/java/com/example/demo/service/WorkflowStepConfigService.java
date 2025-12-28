package com.example.demo.service;

import com.example.demo.model.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository workflowStepConfigRepository;

    public WorkflowStepConfigService(WorkflowStepConfigRepository workflowStepConfigRepository) {
        this.workflowStepConfigRepository = workflowStepConfigRepository;
    }

    public WorkflowStepConfig save(WorkflowStepConfig stepConfig) {
        return workflowStepConfigRepository.save(stepConfig);
    }

    public List<WorkflowStepConfig> findByTemplateId(Long templateId) {
        return workflowStepConfigRepository.findByTemplateIdOrderByLevelNumberAsc(templateId);
    }
}