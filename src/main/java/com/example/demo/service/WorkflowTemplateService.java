package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class WorkflowTemplateService {

    private final WorkflowTemplateRepository repo;

    public WorkflowTemplateService(WorkflowTemplateRepository repo) {
        this.repo = repo;
    }

    public WorkflowTemplate createTemplate(WorkflowTemplate t) {
        return repo.save(t);
    }

    public WorkflowTemplate getTemplate(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<WorkflowTemplate> getAll() {
        return repo.findAll();
    }
}
