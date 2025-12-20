package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.entity.AuditLog;
import com.digitalevidence.evidencecustody.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    // =================================================
    // SAVE AUDIT LOG (IMMUTABLE)
    // =================================================
    public void log(String username, String action, String entity, Long entityId) {

        AuditLog log = new AuditLog();
        log.setUsername(username);
        log.setAction(action);
        log.setEntityType(entity);
        log.setEntityId(entityId);
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
    }
}
