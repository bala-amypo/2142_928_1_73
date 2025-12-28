package com.example.demo.controller;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.service.ApprovalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class ApprovalRequestController {

    @Autowired
    private ApprovalRequestService approvalRequestService;

    @PostMapping("/")
    public ResponseEntity<ApprovalRequest> create(@RequestBody ApprovalRequest request) {
        ApprovalRequest saved = approvalRequestService.save(request);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/")
    public ResponseEntity<List<ApprovalRequest>> getAll() {
        List<ApprovalRequest> requests = approvalRequestService.findAll();
        return ResponseEntity.ok(requests);
    }
}