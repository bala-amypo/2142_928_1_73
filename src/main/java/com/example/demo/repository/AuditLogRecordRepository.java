package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AuditLogRecord;

public interface AuditLogRecordRepository
        extends JpaRepository<AuditLogRecord, Long> {

    // ✅ MUST match entity field exactly
    List<AuditLogRecord> findByRequestId(Long requestId);
}
