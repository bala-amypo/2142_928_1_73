package com.example.demo.repository;

import com.example.demo.entity.ApprovalAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalActionRepository extends JpaRepository<ApprovalAction, Long> {

    List<ApprovalAction> findByApprovalRequest_Id(Long requestId);
}
