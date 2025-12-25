package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApprovalAction;
import com.example.demo.service.ApprovalActionService;

@RestController
@RequestMapping("/approval-actions")
public class ApprovalActionController {

    private final ApprovalActionService service;

    public ApprovalActionController(ApprovalActionService service) {
        this.service = service;
    }

    @PostMapping("/{requestId}")
    public ApprovalAction act(@PathVariable Long requestId,
                              @RequestBody ApprovalAction action) {
        return service.takeAction(requestId, action);
    }
}
