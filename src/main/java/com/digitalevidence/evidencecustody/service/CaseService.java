package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.entity.CaseFile;
import com.digitalevidence.evidencecustody.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;
    private final AuditService auditService; // 1. Inject AuditService

    public CaseService(CaseRepository caseRepository, AuditService auditService) {
        this.caseRepository = caseRepository;
        this.auditService = auditService;
    }

    public CaseFile createCase(CaseFile caseFile) {
        caseFile.setCreatedAt(LocalDateTime.now());
        caseFile.setStatus("OPEN");
        
        CaseFile savedCase = caseRepository.save(caseFile);

        // 2. Log the "Create Case" action
        auditService.log(
            savedCase.getCreatedBy(),
            "CREATED_CASE",
            "CASE",
            savedCase.getCaseId()
        );

        return savedCase;
    }

    public List<CaseFile> getAllCases() {
        return caseRepository.findAll();
    }

    public CaseFile getCaseById(Long caseId) {
        return caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }
}