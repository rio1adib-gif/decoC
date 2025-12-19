package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.entity.AuditLog;
import com.digitalevidence.evidencecustody.repository.AuditLogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    public AuditController(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // =====================================================
    // VIEW ALL AUDIT LOGS (ADMIN ONLY)
    // =====================================================
    @GetMapping("/logs")
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(auditLogRepository.findAll());
    }
}
