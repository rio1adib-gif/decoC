package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.entity.Evidence;
import com.digitalevidence.evidencecustody.service.EvidenceService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @GetMapping
    public ResponseEntity<List<Evidence>> getAllEvidence() {
        return ResponseEntity.ok(evidenceService.getAllEvidence()); 
    }

    @GetMapping("/download/{evidenceId}")
    public ResponseEntity<Resource> downloadEvidence(@PathVariable Long evidenceId) {
        Resource resource = evidenceService.downloadEvidenceFile(evidenceId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/upload/{caseId}")
    public ResponseEntity<Evidence> uploadEvidence(
            @PathVariable Long caseId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) throws IOException { // 1. Inject Authentication
        
        // 2. Pass username to service
        return ResponseEntity.ok(evidenceService.uploadEvidence(caseId, file, authentication.getName()));
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