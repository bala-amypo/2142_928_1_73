package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowStepConfig;
import com.example.demo.service.WorkflowStepConfigService;

@RestController
@RequestMapping("/workflow-steps")
public class WorkflowStepConfigController {

    private final WorkflowStepConfigService service;

    public WorkflowStepConfigController(WorkflowStepConfigService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowStepConfig create(@RequestBody WorkflowStepConfig step) {
        return service.create(step);
    }

    @GetMapping("/template/{templateId}")
    public List<WorkflowStepConfig> getByTemplate(@PathVariable Long templateId) {
        return service.getByTemplateId(templateId);
    }
}
