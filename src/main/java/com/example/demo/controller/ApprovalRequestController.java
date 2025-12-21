package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.service.ApprovalRequestService;

@RestController
@RequestMapping("/approval-requests")
public class ApprovalRequestController {

    private final ApprovalRequestService service;

    public ApprovalRequestController(ApprovalRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ApprovalRequest create(@RequestBody ApprovalRequest request) {
        return service.createRequest(request);
    }

    @GetMapping
    public List<ApprovalRequest> getAll() {
        return service.getAllRequests();
    }

    @GetMapping("/{id}")
    public ApprovalRequest getById(@PathVariable Long id) {
        return service.getRequestById(id);
    }

    @PutMapping("/{id}/approve")
    public ApprovalRequest approve(@PathVariable Long id) {
        return service.approveRequest(id);
    }

    @PutMapping("/{id}/reject")
    public ApprovalRequest reject(@PathVariable Long id) {
        return service.rejectRequest(id);
    }
}
