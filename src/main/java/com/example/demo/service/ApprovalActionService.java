package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalAction;
import com.example.demo.entity.ApprovalRequest;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;

@Service
public class ApprovalActionService {

    private final ApprovalActionRepository actionRepository;
    private final ApprovalRequestRepository requestRepository;

    public ApprovalActionService(ApprovalActionRepository actionRepository,
                                 ApprovalRequestRepository requestRepository) {
        this.actionRepository = actionRepository;
        this.requestRepository = requestRepository;
    }

    public ApprovalAction takeAction(Long requestId, ApprovalAction action) {
        ApprovalRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        action.setApprovalRequest(request);
        action.setActionAt(LocalDateTime.now());

        return actionRepository.save(action);
    }
}
