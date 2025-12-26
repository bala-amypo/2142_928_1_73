package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ApprovalRequest;

public interface ApprovalRequestRepository
        extends JpaRepository<ApprovalRequest, Long> {

    List<ApprovalRequest> findByRequesterld(Long requesterld);
}
