# 📁 Digital Evidence Custody System – Project_v2

## 🧾 Project Overview
The **Digital Evidence Custody System** is a secure, role-based web application designed to manage the **chain of custody for digital evidence**.  
It ensures **evidence integrity, accountability, and legal admissibility** by enforcing strict access control, cryptographic hashing, and immutable audit logging.

This system is built using **Spring Boot, MySQL, and JWT-based security**, following real-world digital forensic workflows.

---

## 🎯 Objectives
- Maintain a **secure chain of custody** for digital evidence  
- Enforce **role-based access control (RBAC)**
- Ensure **evidence integrity** using cryptographic hashing
- Provide **audit-ready logs** for legal compliance
- Support **case-based evidence management**

---

## 👥 User Roles & Responsibilities

### 🔑 ADMIN (System Owner)
- Create and manage users (Admin, Officer, Forensic)
- Enable / disable user accounts
- View all cases and evidence metadata
- Access complete audit logs
- Cannot upload or modify raw evidence

### 👮 OFFICER (Investigator)
- Create new cases
- Upload digital evidence
- View assigned cases and evidence
- Download forensic reports

### 🧪 FORENSIC OFFICER
- Download evidence for analysis
- Verify file integrity using hash comparison
- Upload forensic analysis reports
- Update evidence status

---

## 🔐 Security Features
- **JWT Authentication**
- **BCrypt Password Encryption**
- **Role-Based Access Control**
- **Stateless REST APIs**
- **401 / 403 enforcement**
- **Immutable audit logs**

---

## 🗂️ System Architecture
```
Controller → Service → Repository → Database
                     ↓
                 File System
```

- Evidence files stored outside database
- Metadata stored in MySQL
- Hash verification ensures integrity
- All actions logged for auditing

---

## 📁 Project Structure
```
src/main/java/com/digitalevidence/evidencecustody
│
├── config
│   ├── SecurityConfig.java
│   └── EvidenceStorageProperties.java
│
├── controller
│   ├── AuthController.java
│   ├── AdminController.java
│   ├── CaseController.java
│   ├── EvidenceController.java
│   ├── ForensicController.java
│   └── AuditController.java
│
├── dto
│   ├── UserRegisterDTO.java
│   ├── CaseRequestDTO.java
│   └── EvidenceUploadDTO.java
│
├── entity
│   ├── User.java
│   ├── CaseFile.java
│   ├── Evidence.java
│   ├── ForensicReport.java
│   └── AuditLog.java
│
├── repository
│   ├── UserRepository.java
│   ├── CaseRepository.java
│   ├── EvidenceRepository.java
│   ├── ForensicReportRepository.java
│   └── AuditLogRepository.java
│
├── security
│   ├── JwtAuthenticationFilter.java
│   ├── JwtAuthenticationEntryPoint.java
│   └── CustomUserDetailsService.java
│
├── service
│   ├── AuthService.java
│   ├── CaseService.java
│   ├── EvidenceService.java
│   ├── ForensicService.java
│   ├── AuditService.java
│   └── HashUtil.java
│
└── DigitalevidenceCustodyApplication.java
```

---

## 🧪 Testing
All APIs are tested using **Postman**.

### Tested Scenarios
- Admin creation
- User registration
- JWT authentication
- Case creation
- Evidence upload
- Forensic report upload
- Audit logging
- Role-based access enforcement

---

## 🛠️ Technologies Used
- **Java 21**
- **Spring Boot 4**
- **Spring Security + JWT**
- **MySQL**
- **Hibernate / JPA**
- **Postman**
- **Maven**

---

## ⚙️ Configuration
- Server Port: `9090`
- Database: `MySQL`
- Evidence Storage:
```
${user.home}/evidence_uploads
```
- File size limit: `300MB`

---

## 📜 Legal & Compliance Features
- Immutable audit logs
- Hash-based integrity verification
- Strict separation of duties
- Case-based evidence lifecycle

