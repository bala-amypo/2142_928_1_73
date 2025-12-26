package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "approval_requests")
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
    
    // Constructors
    public ApprovalRequest() {}
    
    public ApprovalRequest(Long templateId, Long requesterId, String requestTitle, String requestPayloadJson, String status, Integer currentLevel) {
        this.templateId = templateId;
        this.requesterId = requesterId;
        this.requestTitle = requestTitle;
        this.requestPayloadJson = requestPayloadJson;
        this.status = status;
        this.currentLevel = currentLevel;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getTemplateId() {
        return templateId;
    }
    
    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
    
    public Long getRequesterId() {
        return requesterId;
    }
    
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }
    
    public String getRequestTitle() {
        return requestTitle;
    }
    
    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }
    
    public String getRequestPayloadJson() {
        return requestPayloadJson;
    }
    
    public void setRequestPayloadJson(String requestPayloadJson) {
        this.requestPayloadJson = requestPayloadJson;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Integer getCurrentLevel() {
        return currentLevel;
    }
    
    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalRequest that = (ApprovalRequest) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ApprovalRequest{" +
               "id=" + id +
               ", templateId=" + templateId +
               ", requesterId=" + requesterId +
               ", requestTitle='" + requestTitle + '\'' +
               ", status='" + status + '\'' +
               ", currentLevel=" + currentLevel +
               ", createdAt=" + createdAt +
               '}';
    }
}