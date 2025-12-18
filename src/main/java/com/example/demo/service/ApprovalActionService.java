package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalAction;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;

@Service
public class ApprovalActionService {

    private final ApprovalActionRepository approvalActionRepository;
    private final ApprovalRequestRepository approvalRequestRepository;

    // ✅ Constructor order MUST match
    public ApprovalActionService(
            ApprovalActionRepository approvalActionRepository,
            ApprovalRequestRepository approvalRequestRepository) {

        this.approvalActionRepository = approvalActionRepository;
        this.approvalRequestRepository = approvalRequestRepository;
    }

    public ApprovalAction recordAction(ApprovalAction action) {
        return approvalActionRepository.save(action);
    }
}
