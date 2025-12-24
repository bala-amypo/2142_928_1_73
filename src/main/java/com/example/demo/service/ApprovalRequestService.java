package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.entity.WorkflowTemplate;
import com.example.demo.repository.ApprovalRequestRepository;
import com.example.demo.repository.WorkflowTemplateRepository;

@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository requestRepository;
    private final WorkflowTemplateRepository templateRepository;

    public ApprovalRequestService(ApprovalRequestRepository requestRepository,
                                  WorkflowTemplateRepository templateRepository) {
        this.requestRepository = requestRepository;
        this.templateRepository = templateRepository;
    }

    public ApprovalRequest createRequest(ApprovalRequest request, Long templateId) {
        WorkflowTemplate template = templateRepository.findById(templateId)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        request.setWorkflowTemplate(template);
        request.setStatus("PENDING");
        request.setCurrentLevel(1);
        request.setCreatedAt(LocalDateTime.now());

        return requestRepository.save(request);
    }

    public List<ApprovalRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public ApprovalRequest getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }
}
