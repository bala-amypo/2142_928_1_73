package com.example.demo.controller;

import com.example.demo.model.ApprovalAction;
import com.example.demo.service.ApprovalActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actions")
public class ApprovalActionController {

    @Autowired
    private ApprovalActionService approvalActionService;

    @PostMapping("/")
    public ResponseEntity<ApprovalAction> create(@RequestBody ApprovalAction action) {
        ApprovalAction saved = approvalActionService.save(action);
        return ResponseEntity.ok(saved);
    }
}