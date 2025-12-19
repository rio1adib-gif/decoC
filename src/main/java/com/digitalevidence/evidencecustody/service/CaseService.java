package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.entity.CaseFile;
import com.digitalevidence.evidencecustody.repository.CaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public CaseFile createCase(CaseFile caseFile) {
        caseFile.setCreatedAt(LocalDateTime.now());
        caseFile.setStatus("OPEN");
        return caseRepository.save(caseFile);
    }

    public List<CaseFile> getAllCases() {
        return caseRepository.findAll();
    }

    public CaseFile getCaseById(Long caseId) {
        return caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }
}
