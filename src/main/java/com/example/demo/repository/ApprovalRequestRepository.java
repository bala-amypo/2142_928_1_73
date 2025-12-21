package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ApprovalRequest;

public interface ApprovalRequestRepository
        extends JpaRepository<ApprovalRequest, Long> {

    // ✅ FIXED: Id (capital I)
    List<ApprovalRequest> findByRequesterId(Long requesterId);
}
