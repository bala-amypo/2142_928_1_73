package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log_records")
@Data
public class AuditLogRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long requestId;
    
    @Column(nullable = false)
    private String eventType;
    
    private String details;
    
    @CreationTimestamp
    private LocalDateTime loggedAt;
}