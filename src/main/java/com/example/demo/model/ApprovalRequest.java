package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "approval_requests")
@Data
public class ApprovalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long templateId;
    
    @Column(nullable = false)
    private Long requesterId;
    
    @Column(nullable = false)
    private String requestTitle;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String requestPayloadJson;
    
    @Column(nullable = false)
    private String status = "PENDING";
    
    private Integer currentLevel = 1;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}