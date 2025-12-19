package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.dto.CaseRequestDTO;
import com.digitalevidence.evidencecustody.entity.CaseFile;
import com.digitalevidence.evidencecustody.service.CaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {

    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    // CREATE CASE (OFFICER)
    @PostMapping("/create")
    public ResponseEntity<CaseFile> createCase(
            @RequestBody CaseRequestDTO dto,
            Authentication authentication) {

        CaseFile caseFile = new CaseFile();
        caseFile.setCaseNumber(dto.getCaseNumber());
        caseFile.setTitle(dto.getTitle());
        caseFile.setDescription(dto.getDescription());
        caseFile.setCreatedBy(authentication.getName());

        return ResponseEntity.ok(caseService.createCase(caseFile));
    }

    // VIEW ALL CASES
    @GetMapping("/all")
    public ResponseEntity<List<CaseFile>> getAllCases() {
        return ResponseEntity.ok(caseService.getAllCases());
    }
}
