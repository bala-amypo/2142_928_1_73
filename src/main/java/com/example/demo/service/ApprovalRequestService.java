package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.WorkflowStepConfigRepository;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository approvalRequestRepository;
    private final WorkflowStepConfigRepository workflowStepConfigRepository;
    private final WorkflowTemplateRepository workflowTemplateRepository;
    private final ApprovalActionRepository approvalActionRepository;

    // ✅ Constructor order MUST match EXACTLY
    public ApprovalRequestService(
            ApprovalRequestRepository approvalRequestRepository,
            WorkflowStepConfigRepository workflowStepConfigRepository,
            WorkflowTemplateRepository workflowTemplateRepository,
            ApprovalActionRepository approvalActionRepository) {

        this.approvalRequestRepository = approvalRequestRepository;
        this.workflowStepConfigRepository = workflowStepConfigRepository;
        this.workflowTemplateRepository = workflowTemplateRepository;
        this.approvalActionRepository = approvalActionRepository;
    }

    public ApprovalRequest createRequest(ApprovalRequest req) {
        req.setStatus("PENDING");
        req.setCreatedAt(LocalDateTime.now());
        req.setCurrentLevel(1);
        return approvalRequestRepository.save(req);
    }

    public List<ApprovalRequest> getRequestsByRequester(Long userld) {
        return approvalRequestRepository.findByRequesterld(userld);
    }

    public List<ApprovalRequest> getAllRequests() {
        return approvalRequestRepository.findAll();
    }
}
