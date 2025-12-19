package com.digitalevidence.evidencecustody.dto;

public class EvidenceUploadDTO {

    private Long caseId;
    private String description;

    // ===== Getters & Setters =====
    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
