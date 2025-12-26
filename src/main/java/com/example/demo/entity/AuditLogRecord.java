package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log_record")
public class AuditLogRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long requestld;
    private String eventType;
    private String details;
    private LocalDateTime loggedAt;

    public AuditLogRecord() {
        this.loggedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getRequestld() {
        return requestld;
    }

    public void setRequestld(Long requestld) {
        this.requestld = requestld;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(LocalDateTime loggedAt) {
        this.loggedAt = loggedAt;
    }
}
