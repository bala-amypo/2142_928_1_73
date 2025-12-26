package com.example.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "approval_actions")
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
    
    // Constructors
    public ApprovalAction() {}
    
    public ApprovalAction(Long requestId, Long approverId, Integer levelNumber, String action, String comments) {
        this.requestId = requestId;
        this.approverId = approverId;
        this.levelNumber = levelNumber;
        this.action = action;
        this.comments = comments;
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
    
    public Long getApproverId() {
        return approverId;
    }
    
    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }
    
    public Integer getLevelNumber() {
        return levelNumber;
    }
    
    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }
    
    public String getAction() {
        return action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public String getComments() {
        return comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public LocalDateTime getActionDate() {
        return actionDate;
    }
    
    public void setActionDate(LocalDateTime actionDate) {
        this.actionDate = actionDate;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalAction that = (ApprovalAction) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "ApprovalAction{" +
               "id=" + id +
               ", requestId=" + requestId +
               ", approverId=" + approverId +
               ", levelNumber=" + levelNumber +
               ", action='" + action + '\'' +
               ", comments='" + comments + '\'' +
               ", actionDate=" + actionDate +
               '}';
    }
}