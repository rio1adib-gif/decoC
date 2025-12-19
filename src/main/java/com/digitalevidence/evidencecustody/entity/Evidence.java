package com.digitalevidence.evidencecustody.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evidenceId;

    private String fileName;
    private String filePath;

    @Column(nullable = false, unique = true)
    private String hash;

    private String status;
    private String uploadedBy; // Required for the store method
    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "case_id", nullable = false)
    private CaseFile caseFile;

    // Standard Getters and Setters
    public Long getEvidenceId() { return evidenceId; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }
    public CaseFile getCaseFile() { return caseFile; }
    public void setCaseFile(CaseFile caseFile) { this.caseFile = caseFile; }
}