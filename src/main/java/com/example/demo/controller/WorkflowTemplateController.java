package com.example.demo.controller;

import com.example.demo.model.WorkflowTemplate;
import com.example.demo.service.WorkflowTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@Tag(name = "Workflow Template", description = "Workflow Template Management APIs")
public class WorkflowTemplateController {
    
    private final WorkflowTemplateService templateService;
    
    public WorkflowTemplateController(WorkflowTemplateService templateService) {
        this.templateService = templateService;
    }
    
    @PostMapping
    @Operation(summary = "Create a new workflow template")
    public ResponseEntity<WorkflowTemplate> createTemplate(@RequestBody WorkflowTemplate template) {
        return ResponseEntity.ok(templateService.createTemplate(template));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get template by ID")
    public ResponseEntity<WorkflowTemplate> getTemplate(@PathVariable Long id) {
        return templateService.getTemplateById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    @Operation(summary = "Get all templates")
    public ResponseEntity<List<WorkflowTemplate>> getAllTemplates() {
        return ResponseEntity.ok(templateService.getAllTemplates());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update template")
    public ResponseEntity<WorkflowTemplate> updateTemplate(@PathVariable Long id, 
                                                           @RequestBody WorkflowTemplate template) {
        return ResponseEntity.ok(templateService.updateTemplate(id, template));
    }
}