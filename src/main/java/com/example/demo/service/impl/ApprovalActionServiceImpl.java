package com.example.demo.service.impl;

import com.example.demo.model.ApprovalAction;
import com.example.demo.model.ApprovalRequest;
import com.example.demo.model.AuditLogRecord;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.AuditLogRecordRepository;
import com.example.demo.service.ApprovalActionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApprovalActionServiceImpl implements ApprovalActionService {
    
    private final ApprovalActionRepository actionRepository;
    private final ApprovalRequestRepository requestRepository;
    private final AuditLogRecordRepository auditRepository;
    
    public ApprovalActionServiceImpl(ApprovalActionRepository actionRepository,
                                    ApprovalRequestRepository requestRepository,
                                    AuditLogRecordRepository auditRepository) {
        this.actionRepository = actionRepository;
        this.requestRepository = requestRepository;
        this.auditRepository = auditRepository;
    }
    
    @Override
    @Transactional
    public ApprovalAction recordAction(ApprovalAction action) {
        ApprovalAction savedAction = actionRepository.save(action);
        
        // Update request status
        ApprovalRequest request = requestRepository.findById(action.getRequestId())
            .orElseThrow(() -> new RuntimeException("Request not found"));
        
        if ("APPROVED".equals(action.getAction())) {
            request.setCurrentLevel(request.getCurrentLevel() + 1);
            request.setStatus("IN_PROGRESS");
        } else if ("REJECTED".equals(action.getAction())) {
            request.setStatus("REJECTED");
        }
        requestRepository.save(request);
        
        // Create audit log
        AuditLogRecord auditLog = new AuditLogRecord();
        auditLog.setRequestId(action.getRequestId());
        auditLog.setEventType("ACTION_RECORDED");
        auditLog.setDetails("Action: " + action.getAction() + " at level " + action.getLevelNumber());
        auditRepository.save(auditLog);
        
        return savedAction;
    }
}