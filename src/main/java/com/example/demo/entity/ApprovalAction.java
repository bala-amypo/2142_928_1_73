package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "approval_actions")
public class ApprovalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action; // APPROVED / REJECTED

    private String comments;

    private LocalDateTime actionAt;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ApprovalRequest approvalRequest;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User actionBy;

    public ApprovalAction() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDateTime getActionAt() { return actionAt; }
    public void setActionAt(LocalDateTime actionAt) { this.actionAt = actionAt; }

    public ApprovalRequest getApprovalRequest() { return approvalRequest; }
    public void setApprovalRequest(ApprovalRequest approvalRequest) {
        this.approvalRequest = approvalRequest;
    }

    public User getActionBy() { return actionBy; }
    public void setActionBy(User actionBy) { this.actionBy = actionBy; }
}
