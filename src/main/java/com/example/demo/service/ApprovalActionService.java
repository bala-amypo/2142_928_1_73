package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalAction;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;

@Service
public class ApprovalActionService {

    private final ApprovalActionRepository actionRepository;
    private final ApprovalRequestRepository requestRepository;

    // Constructor order DOES NOT use security
    public ApprovalActionService(ApprovalActionRepository actionRepository,
                                 ApprovalRequestRepository requestRepository) {
        this.actionRepository = actionRepository;
        this.requestRepository = requestRepository;
    }

    public ApprovalAction recordAction(ApprovalAction action) {
        action.setActionDate(LocalDateTime.now());
        return actionRepository.save(action);
    }
}
