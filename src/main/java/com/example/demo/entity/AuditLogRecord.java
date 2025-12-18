package com.example.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "audit_log_record")
public class AuditLogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestld;
    private String eventType;
    private String details;

    @Column(nullable = false)
    private LocalDateTime loggedAt = LocalDateTime.now();

    // getters & setters
}
