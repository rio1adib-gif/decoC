package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.entity.Evidence;
import com.digitalevidence.evidencecustody.service.EvidenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceController {

    private final EvidenceService evidenceService;

    public EvidenceController(EvidenceService evidenceService) {
        this.evidenceService = evidenceService;
    }

    @PostMapping("/upload/{caseId}")
    public ResponseEntity<Evidence> uploadEvidence(
            @PathVariable Long caseId,
            @RequestParam("file") MultipartFile file) throws IOException { // IOException handled
        return ResponseEntity.ok(evidenceService.uploadEvidence(caseId, file));
    }

    @PutMapping("/status/{evidenceId}")
    public ResponseEntity<Evidence> updateStatus(
            @PathVariable Long evidenceId,
            @RequestParam String status) {
        return ResponseEntity.ok(evidenceService.updateStatus(evidenceId, status));
    }

    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<Evidence>> getEvidenceByCase(@PathVariable Long caseId) {
        return ResponseEntity.ok(evidenceService.getEvidenceByCase(caseId));
    }
}