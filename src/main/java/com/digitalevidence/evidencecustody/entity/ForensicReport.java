package com.digitalevidence.evidencecustody.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "forensic_reports")
public class ForensicReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(nullable = false)
    private String reportFileName;

    // ✅ NEW FIELD (IMPORTANT)
    @Column(nullable = false)
    private String reportFilePath;

    @Column(nullable = false)
    private String uploadedBy; // forensic username

    private LocalDateTime uploadedAt;

    // Many reports → one evidence
    @ManyToOne
    @JoinColumn(name = "evidence_id", nullable = false)
    private Evidence evidence;

    // ===== GETTERS & SETTERS =====

    public Long getReportId() {
        return reportId;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public String getReportFilePath() {
        return reportFilePath;
    }

    public void setReportFilePath(String reportFilePath) {
        this.reportFilePath = reportFilePath;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }
}
