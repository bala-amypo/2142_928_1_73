package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "audit_log_records")
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
    
    // Constructors
    public AuditLogRecord() {}
    
    public AuditLogRecord(Long requestId, String eventType, String details) {
        this.requestId = requestId;
        this.eventType = eventType;
        this.details = details;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getRequestId() {
        return requestId;
    }
    
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditLogRecord that = (AuditLogRecord) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "AuditLogRecord{" +
               "id=" + id +
               ", requestId=" + requestId +
               ", eventType='" + eventType + '\'' +
               ", details='" + details + '\'' +
               ", loggedAt=" + loggedAt +
               '}';
    }
}