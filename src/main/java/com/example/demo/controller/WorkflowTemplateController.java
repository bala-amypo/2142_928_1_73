package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class WorkflowTemplateController {

    @Autowired
    private WorkflowTemplateService workflowTemplateService;

    @PostMapping("/")
    public ResponseEntity<WorkflowTemplate> create(@RequestBody WorkflowTemplate template) {
        WorkflowTemplate saved = workflowTemplateService.save(template);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowTemplate> getById(@PathVariable Long id) {
        WorkflowTemplate template = workflowTemplateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found"));
        return ResponseEntity.ok(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowTemplate> update(@PathVariable Long id, @RequestBody WorkflowTemplate template) {
        workflowTemplateService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found"));
        template.setId(id);
        WorkflowTemplate updated = workflowTemplateService.save(template);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/")
    public ResponseEntity<List<WorkflowTemplate>> getAll() {
        List<WorkflowTemplate> templates = workflowTemplateService.findAll();
        return ResponseEntity.ok(templates);
    }
}