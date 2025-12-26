package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "approval_request")
public class ApprovalRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long templateld;
    private Long requesterld;

    private String requestTitle;

    @Lob
    private String requestPayloadJson;

    private String status;
    private Integer currentLevel;
    private LocalDateTime createdAt;

    public ApprovalRequest() {
        this.status = "PENDING";
        this.createdAt = LocalDateTime.now();
        this.currentLevel = 1;
    }

    public Long getId() {
        return id;
    }

    public Long getTemplateld() {
        return templateld;
    }

    public void setTemplateld(Long templateld) {
        this.templateld = templateld;
    }

    public Long getRequesterld() {
        return requesterld;
    }

    public void setRequesterld(Long requesterld) {
        this.requesterld = requesterld;
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
}
