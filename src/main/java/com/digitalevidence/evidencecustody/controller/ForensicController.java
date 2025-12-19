package com.digitalevidence.evidencecustody.controller;

import com.digitalevidence.evidencecustody.entity.ForensicReport;
import com.digitalevidence.evidencecustody.service.ForensicService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/reports")
public class ForensicController {

    private final ForensicService forensicService;

    public ForensicController(ForensicService forensicService) {
        this.forensicService = forensicService;
    }

    // ================= UPLOAD FORENSIC REPORT =================
    @PostMapping("/upload-analysis/{evidenceId}")
    public ResponseEntity<ForensicReport> uploadReport(
            @PathVariable Long evidenceId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) {

        return ResponseEntity.ok(
                forensicService.uploadReport(
                        evidenceId,
                        file,
                        authentication.getName()
                )
        );
    }

    // ================= DOWNLOAD FORENSIC REPORT =================
    @GetMapping("/download/{reportId}")
    public ResponseEntity<Resource> downloadReport(
            @PathVariable Long reportId,
            Authentication authentication) {

        Resource resource = forensicService.downloadReport(
                reportId,
                authentication.getName()
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
