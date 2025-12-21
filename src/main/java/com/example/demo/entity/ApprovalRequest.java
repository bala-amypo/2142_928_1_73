package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateId;
    private Long requesterId;
    private String requestTitle;

    @Lob
    private String requestPayloadJson;

    private String status;
    private Integer currentLevel;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public Long getTemplateId() { return templateId; }
    public Long getRequesterId() { return requesterId; }

    public void setId(Long id) { this.id = id; }
    public void setTemplateId(Long templateId) { this.templateId = templateId; }
    public void setRequesterId(Long requesterId) { this.requesterId = requesterId; }
    public void setStatus(String status) { this.status = status; }
    public void setCurrentLevel(Integer currentLevel) { this.currentLevel = currentLevel; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
