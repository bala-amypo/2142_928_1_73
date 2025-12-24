package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.WorkflowStep;
import com.example.demo.service.WorkflowStepService;

@RestController
@RequestMapping("/workflow-steps")
public class WorkflowStepController {

    private final WorkflowStepService service;

    public WorkflowStepController(WorkflowStepService service) {
        this.service = service;
    }

    @PostMapping
    public WorkflowStep create(@RequestBody WorkflowStep step) {
        return service.createStep(step);
    }

    @GetMapping("/template/{templateId}")
    public List<WorkflowStep> getByTemplate(@PathVariable Long templateId) {
        return service.getStepsByTemplate(templateId);
    }
}
