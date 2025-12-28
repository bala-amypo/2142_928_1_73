package com.example.demo.controller;

import com.example.demo.model.WorkflowStepConfig;
import com.example.demo.service.WorkflowStepConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/steps")
public class WorkflowStepConfigController {

    @Autowired
    private WorkflowStepConfigService workflowStepConfigService;

    @PostMapping("/")
    public ResponseEntity<WorkflowStepConfig> create(@RequestBody WorkflowStepConfig stepConfig) {
        WorkflowStepConfig saved = workflowStepConfigService.save(stepConfig);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/template/{templateId}")
    public ResponseEntity<List<WorkflowStepConfig>> getByTemplateId(@PathVariable Long templateId) {
        List<WorkflowStepConfig> steps = workflowStepConfigService.findByTemplateId(templateId);
        return ResponseEntity.ok(steps);
    }
}