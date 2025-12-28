package com.example.demo.service;

import com.example.demo.model.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WorkflowTemplateService {

    private final WorkflowTemplateRepository workflowTemplateRepository;

    public WorkflowTemplateService(WorkflowTemplateRepository workflowTemplateRepository) {
        this.workflowTemplateRepository = workflowTemplateRepository;
    }

    public WorkflowTemplate save(WorkflowTemplate template) {
        return workflowTemplateRepository.save(template);
    }

    public WorkflowTemplate createTemplate(WorkflowTemplate template) {
        return workflowTemplateRepository.save(template);
    }

    public Optional<WorkflowTemplate> findById(Long id) {
        return workflowTemplateRepository.findById(id);
    }

    public Optional<WorkflowTemplate> getTemplateById(Long id) {
        return workflowTemplateRepository.findById(id);
    }

    public WorkflowTemplate updateTemplate(Long id, WorkflowTemplate template) {
        template.setId(id);
        return workflowTemplateRepository.save(template);
    }

    public WorkflowTemplate activateTemplate(Long id, boolean active) {
        WorkflowTemplate template = workflowTemplateRepository.findById(id).orElseThrow();
        template.setActive(active);
        return workflowTemplateRepository.save(template);
    }

    public List<WorkflowTemplate> findAll() {
        return workflowTemplateRepository.findAll();
    }

    public List<WorkflowTemplate> getAllTemplates() {
        return workflowTemplateRepository.findAll();
    }
}
