package com.example.demo.service.impl;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.WorkflowStepConfigRepository;
import com.example.demo.repository.WorkflowTemplateRepository;
import com.example.demo.service.ApprovalRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApprovalRequestServiceImpl implements ApprovalRequestService {
    
    private final ApprovalRequestRepository requestRepository;
    private final WorkflowStepConfigRepository stepRepository;
    private final WorkflowTemplateRepository templateRepository;
    private final ApprovalActionRepository actionRepository;
    
    public ApprovalRequestServiceImpl(ApprovalRequestRepository requestRepository,
                                     WorkflowStepConfigRepository stepRepository,
                                     WorkflowTemplateRepository templateRepository,
                                     ApprovalActionRepository actionRepository) {
        this.requestRepository = requestRepository;
        this.stepRepository = stepRepository;
        this.templateRepository = templateRepository;
        this.actionRepository = actionRepository;
    }
    
    @Override
    @Transactional
    public ApprovalRequest createRequest(ApprovalRequest request) {
        if (request.getStatus() == null) {
            request.setStatus("PENDING");
        }
        if (request.getCurrentLevel() == null) {
            request.setCurrentLevel(1);
        }
        return requestRepository.save(request);
    }
    
    @Override
    public List<ApprovalRequest> getRequestsByRequester(Long userId) {
        return requestRepository.findByRequesterId(userId);
    }
    
    @Override
    public List<ApprovalRequest> getAllRequests() {
        return requestRepository.findAll();
    }
}