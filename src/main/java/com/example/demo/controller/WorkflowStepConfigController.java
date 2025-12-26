package com.example.demo.controller;

import com.example.demo.model.WorkflowStepConfig;
import com.example.demo.service.WorkflowStepConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/steps")
@Tag(name = "Workflow Steps", description = "Workflow Step Configuration APIs")
public class WorkflowStepConfigController {
    
    private final WorkflowStepConfigService stepService;
    
    public WorkflowStepConfigController(WorkflowStepConfigService stepService) {
        this.stepService = stepService;
    }
    
    @PostMapping
    @Operation(summary = "Create a workflow step")
    public ResponseEntity<WorkflowStepConfig> createStep(@RequestBody WorkflowStepConfig step) {
        return ResponseEntity.ok(stepService.createStep(step));
    }
    
    @GetMapping("/template/{templateId}")
    @Operation(summary = "Get steps for template")
    public ResponseEntity<List<WorkflowStepConfig>> getStepsForTemplate(@PathVariable Long templateId) {
        return ResponseEntity.ok(stepService.getStepsForTemplate(templateId));
    }
}