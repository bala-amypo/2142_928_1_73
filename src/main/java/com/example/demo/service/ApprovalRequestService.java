package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ApprovalRequest;
import com.example.demo.repository.ApprovalRequestRepository;

@Service
public class ApprovalRequestService {

    private final ApprovalRequestRepository repository;

    public ApprovalRequestService(ApprovalRequestRepository repository) {
        this.repository = repository;
    }

    public ApprovalRequest createRequest(ApprovalRequest request) {
        return repository.save(request);
    }

    public List<ApprovalRequest> getAllRequests() {
        return repository.findAll();
    }

    public ApprovalRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ApprovalRequest approveRequest(Long id) {
        Optional<ApprovalRequest> req = repository.findById(id);
        if (req.isPresent()) {
            ApprovalRequest r = req.get();
            r.setStatus("APPROVED");
            return repository.save(r);
        }
        return null;
    }

    public ApprovalRequest rejectRequest(Long id) {
        Optional<ApprovalRequest> req = repository.findById(id);
        if (req.isPresent()) {
            ApprovalRequest r = req.get();
            r.setStatus("REJECTED");
            return repository.save(r);
        }
        return null;
    }
}
