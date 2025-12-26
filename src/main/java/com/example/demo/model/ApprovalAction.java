package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "approval_actions")
@Data
public class ApprovalAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long requestId;
    
    @Column(nullable = false)
    private Long approverId;
    
    @Column(nullable = false)
    private Integer levelNumber;
    
    @Column(nullable = false)
    private String action;
    
    private String comments;
    
    @CreationTimestamp
    private LocalDateTime actionDate;
}