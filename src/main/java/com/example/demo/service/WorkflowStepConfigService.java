package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.repository.WorkflowStepConfigRepository;

@Service
public class WorkflowStepConfigService {

    private final WorkflowStepConfigRepository repository;

    public WorkflowStepConfigService(WorkflowStepConfigRepository repository) {
        this.repository = repository;
    }

    // ✅ Controller expects this
    public WorkflowStepConfig create(WorkflowStepConfig config) {
        return repository.save(config);
    }

    // ✅ Controller expects this
    public List<WorkflowStepConfig> getByTemplateId(Long templateId) {
        return repository.findByTemplateIdOrderByLevelNumberAsc(templateId);
    }
}
