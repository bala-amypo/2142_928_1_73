package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowStep;
import com.example.demo.repository.WorkflowStepRepository;

@Service
public class WorkflowStepService {

    private final WorkflowStepRepository repository;

    public WorkflowStepService(WorkflowStepRepository repository) {
        this.repository = repository;
    }

    public WorkflowStep createStep(WorkflowStep step) {
        return repository.save(step);
    }

    public List<WorkflowStep> getStepsByTemplate(Long templateId) {
        return repository.findByWorkflowTemplateId(templateId);
    }
}
