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

    public WorkflowStepConfig create(WorkflowStepConfig step) {
        return repository.save(step);
    }

    public List<WorkflowStepConfig> getByTemplateId(Long templateld) {
        return repository.findByTemplateldOrderByLevelNumberAsc(templateld);
    }
}
