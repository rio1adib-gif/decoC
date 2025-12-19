package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.entity.Evidence;
import com.digitalevidence.evidencecustody.service.EvidenceService;
import org.springframework.core.io.Resource; // Required for file download
import org.springframework.http.HttpHeaders; // Required for attachment headers
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

    /**
     * List all evidence (Required for Forensic/Admin dashboards)
     * Matches the fetch() call in forensic.js
     */
    @GetMapping
    public ResponseEntity<List<Evidence>> getAllEvidence() {
        return ResponseEntity.ok(evidenceService.getAllEvidence()); 
    }

    /**
     * Download raw evidence file (Required for Forensic Analysis)
     * This endpoint handles fetching the file from storage and serving it as an attachment.
     */
    @GetMapping("/download/{evidenceId}")
    public ResponseEntity<Resource> downloadEvidence(@PathVariable Long evidenceId) {
        Resource resource = evidenceService.downloadEvidenceFile(evidenceId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * Upload evidence (Officer functionality)
     */
    @PostMapping("/upload/{caseId}")
    public ResponseEntity<Evidence> uploadEvidence(
            @PathVariable Long caseId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(evidenceService.uploadEvidence(caseId, file));
    }

    /**
     * Update status (Forensic/Admin functionality)
     */
    @PutMapping("/status/{evidenceId}")
    public ResponseEntity<Evidence> updateStatus(
            @PathVariable Long evidenceId,
            @RequestParam String status) {
        return ResponseEntity.ok(evidenceService.updateStatus(evidenceId, status));
    }

    /**
     * List evidence for a specific case
     */
    @GetMapping("/case/{caseId}")
    public ResponseEntity<List<Evidence>> getEvidenceByCase(@PathVariable Long caseId) {
        return ResponseEntity.ok(evidenceService.getEvidenceByCase(caseId));
    }
}