package com.example.demo.service.impl;

import com.example.demo.model.ApprovalRequest;
import com.example.demo.repository.ApprovalActionRepository;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.WorkflowStepConfigRepository;
import com.example.demo.repository.WorkflowTemplateRepository;
import com.example.demo.service.ApprovalRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalRequestServiceImpl implements ApprovalRequestService {
    
    private final ApprovalRequestRepository requestRepository;
    private final WorkflowStepConfigRepository stepRepository;
    private final WorkflowTemplateRepository templateRepository;
    private final ApprovalActionRepository actionRepository;
    
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