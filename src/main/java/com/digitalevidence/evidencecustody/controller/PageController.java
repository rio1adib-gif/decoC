package com.digitalevidence.evidencecustody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // ================= LOGIN =================
    // http://localhost:9090/
    @GetMapping("/")
    public String loginPage() {
        return "index"; // index.jsp
    }

    // ================= ADMIN =================
    // http://localhost:9090/admin
    @GetMapping("/admin")
    public String adminDashboard() {
        return "dashboard/admin"; // dashboard/admin.jsp
    }

    // ================= OFFICER =================
    // http://localhost:9090/officer
    @GetMapping("/officer")
    public String officerDashboard() {
        return "dashboard/officer"; // dashboard/officer.jsp
    }

    // ================= FORENSIC =================
    // http://localhost:9090/forensic
    @GetMapping("/forensic")
    public String forensicDashboard() {
        return "dashboard/forensic"; // dashboard/forensic.jsp
    }

    // ================= AUDIT LOGS =================
    // http://localhost:9090/audit
    @GetMapping("/audit")
    public String auditLogs() {
        return "dashboard/audit"; // dashboard/audit.jsp
    }

    // ================= REPORTS =================
    // http://localhost:9090/reports
    @GetMapping("/reports")
    public String reportsPage() {
        return "dashboard/reports"; // dashboard/reports.jsp
    }
}
