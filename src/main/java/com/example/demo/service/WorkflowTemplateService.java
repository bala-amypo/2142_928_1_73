package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class WorkflowTemplateService {

    private final WorkflowTemplateRepository repository;

    public WorkflowTemplateService(WorkflowTemplateRepository repository) {
        this.repository = repository;
    }

    public WorkflowTemplate createTemplate(WorkflowTemplate template) {
        return repository.save(template);
    }

    public List<WorkflowTemplate> getAllTemplates() {
        return repository.findAll();
    }

    public WorkflowTemplate activateTemplate(Long id) {
        WorkflowTemplate template = repository.findById(id).orElse(null);
        if (template != null) {
            template.setActive(true);
            return repository.save(template);
        }
        return null;
    }
}
