package com.digitalevidence.evidencecustody.repository;

import com.digitalevidence.evidencecustody.entity.ForensicReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForensicReportRepository extends JpaRepository<ForensicReport, Long> {

    List<ForensicReport> findByEvidence_EvidenceId(Long evidenceId);
}
