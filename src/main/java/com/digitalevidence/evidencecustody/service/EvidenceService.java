package com.digitalevidence.evidencecustody.service;

import com.digitalevidence.evidencecustody.config.EvidenceStorageProperties;
import com.digitalevidence.evidencecustody.entity.Evidence;
import com.digitalevidence.evidencecustody.entity.CaseFile;
import com.digitalevidence.evidencecustody.repository.EvidenceRepository;
import com.digitalevidence.evidencecustody.repository.CaseRepository;
import org.springframework.core.io.Resource; // Required for downloads
import org.springframework.core.io.UrlResource; // Required for file resolution
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException; // Required for path exceptions
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EvidenceService {

    private final Path rootLocation;
    private final EvidenceRepository repository;
    private final CaseRepository caseRepository;
    private final HashUtil hashUtil;

    public EvidenceService(EvidenceStorageProperties props, EvidenceRepository repo, CaseRepository caseRepository, HashUtil hashUtil) {
        this.rootLocation = Paths.get(props.getUploadDir()).toAbsolutePath().normalize();
        this.repository = repo;
        this.caseRepository = caseRepository;
        this.hashUtil = hashUtil;
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    /**
     * Retrieves all evidence records from the database.
     * Required for Forensic and Admin dashboards.
     */
    public List<Evidence> getAllEvidence() {
        return repository.findAll();
    }

    /**
     * Loads an evidence file from storage as a Resource for downloading.
     * Checks if the file exists and is readable before returning.
     */
    public Resource downloadEvidenceFile(Long evidenceId) {
        Evidence evidence = repository.findById(evidenceId)
                .orElseThrow(() -> new RuntimeException("Evidence not found"));
        try {
            Path path = Paths.get(evidence.getFilePath());
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error resolving file path: " + e.getMessage());
        }
    }

    public Evidence uploadEvidence(Long caseId, MultipartFile file) throws IOException {
        CaseFile caseFile = caseRepository.findById(caseId)
                .orElseThrow(() -> new RuntimeException("Case not found"));

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path destinationFile = this.rootLocation.resolve(filename);
        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

        String hash = hashUtil.generateSHA256(destinationFile);

        Evidence evidence = new Evidence();
        evidence.setFileName(filename);
        evidence.setFilePath(destinationFile.toString());
        evidence.setHash(hash);
        evidence.setStatus("UPLOADED");
        evidence.setUploadedAt(LocalDateTime.now());
        evidence.setCaseFile(caseFile);
        
        return repository.save(evidence);
    }

    public Evidence updateStatus(Long evidenceId, String status) {
        Evidence evidence = repository.findById(evidenceId)
                .orElseThrow(() -> new RuntimeException("Evidence not found"));
        evidence.setStatus(status);
        return repository.save(evidence);
    }

    public List<Evidence> getEvidenceByCase(Long caseId) {
        return repository.findByCaseFile_CaseId(caseId);
    }
}