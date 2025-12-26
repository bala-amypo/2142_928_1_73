package com.example.demo.controller;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.service.ApprovalRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@Tag(name = "Approval Requests", description = "Approval Request Management APIs")
public class ApprovalRequestController {
    
    private final ApprovalRequestService requestService;
    
    public ApprovalRequestController(ApprovalRequestService requestService) {
        this.requestService = requestService;
    }
    
    @PostMapping
    @Operation(summary = "Create an approval request")
    public ResponseEntity<ApprovalRequest> createRequest(@RequestBody ApprovalRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }
    
    @GetMapping
    @Operation(summary = "Get all approval requests")
    public ResponseEntity<List<ApprovalRequest>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }
    
    @GetMapping("/requester/{userId}")
    @Operation(summary = "Get requests by requester")
    public ResponseEntity<List<ApprovalRequest>> getRequestsByRequester(@PathVariable Long userId) {
        return ResponseEntity.ok(requestService.getRequestsByRequester(userId));
    }
}