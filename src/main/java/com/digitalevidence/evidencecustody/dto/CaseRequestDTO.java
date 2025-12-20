package com.digitalevidence.evidencecustody.dto;

public class CaseRequestDTO {

    private String caseNumber;
    private String title;
    private String description;

    // ===== Getters & Setters =====
    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
