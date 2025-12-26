package com.example.demo.service;

public interface AuditLogService {
    void logEvent(Long requestId, String eventType, String details);
}