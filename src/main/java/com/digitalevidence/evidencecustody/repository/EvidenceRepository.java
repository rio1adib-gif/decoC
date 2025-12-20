package com.digitalevidence.evidencecustody.repository;

import com.digitalevidence.evidencecustody.entity.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    // Finds evidence list by the ID inside the CaseFile object
    List<Evidence> findByCaseFile_CaseId(Long caseId);
}