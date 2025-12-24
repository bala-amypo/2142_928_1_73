package com.example.demo.repository;

import com.example.demo.entity.AuditLogRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRecordRepository extends JpaRepository<AuditLogRecord, Long> {
}
