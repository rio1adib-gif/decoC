package com.digitalevidence.evidencecustody.repository;

import com.digitalevidence.evidencecustody.entity.CaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<CaseFile, Long> {

    Optional<CaseFile> findByCaseNumber(String caseNumber);
}
