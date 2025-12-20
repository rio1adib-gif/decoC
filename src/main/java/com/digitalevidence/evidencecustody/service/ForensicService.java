package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.entity.Evidence;
import com.digitalevidence.evidencecustody.entity.ForensicReport;
import com.digitalevidence.evidencecustody.repository.EvidenceRepository;
import com.digitalevidence.evidencecustody.repository.ForensicReportRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List; // Added for getAllReports

@Service
public class ForensicService {

    private final EvidenceRepository evidenceRepository;
    private final ForensicReportRepository reportRepository;
    private final AuditService auditLogService;

    public ForensicService(EvidenceRepository evidenceRepository,
                           ForensicReportRepository reportRepository,
                           AuditService auditLogService) {
        this.evidenceRepository = evidenceRepository;
        this.reportRepository = reportRepository;
        this.auditLogService = auditLogService;
    }

    /**
     * Retrieves all forensic reports from the database.
     * Fixed: Added to resolve the undefined method error in ForensicController.
     */
    public List<ForensicReport> getAllReports() {
        return reportRepository.findAll();
    }

    // ================= UPLOAD REPORT =================
    public ForensicReport uploadReport(Long evidenceId,
                                       MultipartFile file,
                                       String username) {

        Evidence evidence = evidenceRepository.findById(evidenceId)
                .orElseThrow(() -> new RuntimeException("Evidence not found"));

        ForensicReport report = new ForensicReport();
        report.setEvidence(evidence);
        report.setReportFileName(file.getOriginalFilename());
        report.setReportFilePath("reports/" + file.getOriginalFilename());
        report.setUploadedBy(username);
        report.setUploadedAt(LocalDateTime.now());

        ForensicReport saved = reportRepository.save(report);

        evidence.setStatus("ANALYZED");
        evidenceRepository.save(evidence);

        auditLogService.log(
                username,
                "UPLOADED_FORENSIC_REPORT",
                "FORENSIC_REPORT",
                saved.getReportId()
        );

        return saved;
    }

    // ================= DOWNLOAD REPORT =================
    public Resource downloadReport(Long reportId, String username) {

        ForensicReport report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        try {
            Path path = Paths.get(report.getReportFilePath());
            Resource resource = new UrlResource(path.toUri());

            auditLogService.log(
                    username,
                    "DOWNLOADED_FORENSIC_REPORT",
                    "FORENSIC_REPORT",
                    reportId
            );

            return resource;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Download failed", e);
        }
    }
}