package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ApprovalAction;

public interface ApprovalActionRepository
        extends JpaRepository<ApprovalAction, Long> {

    List<ApprovalAction> findByLevelAndAction(Integer levelNumber, String action);
}


