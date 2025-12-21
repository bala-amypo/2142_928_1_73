package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ApprovalAction;

public interface ApprovalActionRepository
        extends JpaRepository<ApprovalAction, Long> {

    // ✅ MUST match entity fields EXACTLY
    List<ApprovalAction> findByLevelNumberAndAction(Integer levelNumber, String action);
}
