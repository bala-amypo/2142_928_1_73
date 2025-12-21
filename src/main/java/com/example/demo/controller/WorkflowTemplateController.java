package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;

@RestController
@RequestMapping("/workflow-templates")
public class WorkflowTemplateController {

    private final WorkflowTemplateService service;

    public WorkflowTemplateController(WorkflowTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowTemplate create(@RequestBody WorkflowTemplate template) {
        return service.createTemplate(template);
    }

    @GetMapping
    public List<WorkflowTemplate> getAll() {
        return service.getAllTemplates();
    }

    @PutMapping("/{id}/activate")
    public WorkflowTemplate activate(@PathVariable Long id) {
        return service.activateTemplate(id);
    }
}
