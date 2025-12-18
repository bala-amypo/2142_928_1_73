package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;

@RestController
@RequestMapping("/api/templates")
public class WorkflowTemplateController {

    private final WorkflowTemplateService service;

    public WorkflowTemplateController(WorkflowTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowTemplate create(@RequestBody WorkflowTemplate t) {
        return service.createTemplate(t);
    }

    @GetMapping("/{id}")
    public WorkflowTemplate get(@PathVariable Long id) {
        return service.getTemplateByld(id);
    }

    @GetMapping
    public List<WorkflowTemplate> list() {
        return service.getAllTemplates();
    }

    @PutMapping("/{id}")
    public WorkflowTemplate update(@PathVariable Long id,
                                   @RequestBody WorkflowTemplate t) {
        return service.updateTemplate(id, t);
    }
}
