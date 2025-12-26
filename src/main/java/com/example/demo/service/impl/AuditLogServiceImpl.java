package com.example.demo.service.impl;

import com.example.demo.model.AuditLogRecord;
import com.example.demo.repository.AuditLogRecordRepository;
import com.example.demo.service.AuditLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    
    private final AuditLogRecordRepository auditRepository;
    
    public AuditLogServiceImpl(AuditLogRecordRepository auditRepository) {
        this.auditRepository = auditRepository;
    }
    
    @Override
    @Transactional
    public void logEvent(Long requestId, String eventType, String details) {
        AuditLogRecord log = new AuditLogRecord();
        log.setRequestId(requestId);
        log.setEventType(eventType);
        log.setDetails(details);
        auditRepository.save(log);
    }
}