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

    public List<WorkflowStepConfig> getStepsByTemplateId(Long templateId) {
        return repository.findByTemplateIdOrderByLevelNumberAsc(templateId);
    }
}
