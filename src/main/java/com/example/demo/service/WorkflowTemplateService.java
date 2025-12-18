WorkflowTemplateService

package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class WorkflowTemplateService {

    private final WorkflowTemplateRepository workflowTemplateRepository;

    // ✅ Constructor order MUST match
    public WorkflowTemplateService(WorkflowTemplateRepository workflowTemplateRepository) {
        this.workflowTemplateRepository = workflowTemplateRepository;
    }

    public WorkflowTemplate createTemplate(WorkflowTemplate t) {
        return workflowTemplateRepository.save(t);
    }

    public WorkflowTemplate getTemplateByld(Long id) {
        return workflowTemplateRepository.findById(id).orElseThrow();
    }

    public List<WorkflowTemplate> getAllTemplates() {
        return workflowTemplateRepository.findAll();
    }

    public WorkflowTemplate updateTemplate(Long id, WorkflowTemplate t) {
        WorkflowTemplate existing = getTemplateByld(id);
        existing.setTemplateName(t.getTemplateName());
        existing.setDescription(t.getDescription());
        existing.setTotalLevels(t.getTotalLevels());
        existing.setActive(t.getActive());
        return workflowTemplateRepository.save(existing);
    }

    public WorkflowTemplate activateTemplate(Long id, boolean active) {
        WorkflowTemplate template = getTemplateByld(id);
        template.setActive(active);
        return workflowTemplateRepository.save(template);
    }
}


///////

