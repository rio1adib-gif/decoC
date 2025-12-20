package com.digitalevidence.evidencecustody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String loginPage() { return "index"; }

    @GetMapping("/admin")
    public String adminDashboard() { return "dashboard/admin"; }

    @GetMapping("/user-management")
    public String userManagementPage() { return "dashboard/user-management"; }

    @GetMapping("/admin/evidence")
    public String adminEvidenceView() { return "dashboard/evidence-admin"; }

    @GetMapping("/admin/cases")
    public String adminCasesView() { return "dashboard/cases-admin"; }

    @GetMapping("/officer")
    public String officerDashboard() { return "dashboard/officer"; }

    @GetMapping("/forensic")
    public String forensicDashboard() { return "dashboard/forensic"; }

    @GetMapping("/audit")
    public String auditLogs() { return "dashboard/audit"; }

    @GetMapping("/reports")
    public String reportsPage() { return "dashboard/reports"; }
}