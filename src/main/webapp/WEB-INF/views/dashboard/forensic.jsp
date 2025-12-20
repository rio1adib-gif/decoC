<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Forensic Dashboard | Digital Evidence Custody</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>
<body class="d-flex">

    <script src="/assets/js/auth-guard-forensic.js"></script>

    <div class="sidebar d-flex flex-column p-3 text-white bg-dark-blue" style="width: 280px; min-height: 100vh;">
        <div class="d-flex align-items-center mb-4 px-2">
            <i class="fas fa-microscope me-2"></i>
            <span class="fw-bold">Evidence Custody</span>
        </div>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="#" id="link-overview" class="nav-link active" onclick="showSection('overview')">
                    <i class="fas fa-th-large me-2"></i> Dashboard
                </a>
            </li>
            <li>
                <a href="#" id="link-evidence" class="nav-link text-white" onclick="showSection('evidence-list')">
                    <i class="fas fa-dna me-2"></i> Evidence
                </a>
            </li>
            <li>
                <a href="#" id="link-case" class="nav-link text-white" onclick="showSection('case-list')">
                    <i class="fas fa-folder me-2"></i> Cases
                </a>
            </li>
        </ul>
        <hr>
        <div class="px-2">
            <button class="btn btn-link text-danger p-0 text-decoration-none" onclick="logout()">
                <i class="fas fa-arrow-right-from-bracket me-2"></i> Sign Out
            </button>
        </div>
    </div>

    <div class="flex-grow-1 p-5 bg-light overflow-auto">
        <header class="d-flex justify-content-between align-items-start mb-5">
            <div>
                <h2 class="fw-bold">Forensic Analysis Workspace</h2>
                <p class="text-muted">Analyze evidence and maintain the chain of custody integrity</p>
            </div>
            <div class="text-end">
                <span class="fw-bold d-block" id="header-user-name">Forensic Officer</span>
                <span class="small text-muted">Forensic Analyst</span>
            </div>
        </header>

        <section id="overview-section">
            <div class="row g-4 mb-5">
                <div class="col-md-4">
                    <div class="card border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div><div class="h3 fw-bold mb-0" id="stat-total-evidence">0</div><div class="small text-muted">Total Evidence</div></div>
                            <div class="stat-icon bg-info-light rounded p-2"><i class="fas fa-cube text-info"></i></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div><div class="h3 fw-bold mb-0" id="stat-pending">0</div><div class="small text-muted">Awaiting Analysis</div></div>
                            <div class="stat-icon bg-warning-light rounded p-2"><i class="fas fa-flask text-warning"></i></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card border-0 shadow-sm p-4 action-card" style="max-width: 400px;" onclick="showSection('evidence-list')">
                <i class="fas fa-file-medical text-success mb-3 h4"></i>
                <h5 class="fw-bold">Analyze Evidence</h5>
                <p class="small text-muted">Download raw evidence and upload analysis reports</p>
                <button class="btn btn-link p-0 text-decoration-none text-dark fw-bold mt-2">View Evidence <i class="fas fa-arrow-right small"></i></button>
            </div>
        </section>

        <section id="evidence-list-section" class="d-none">
            <h4 class="fw-bold mb-4">Evidence Analysis</h4>
            <div class="card border-0 shadow-sm p-4">
                <table class="table align-middle">
                    <thead class="text-muted small">
                        <tr>
                            <th>Evidence ID</th>
                            <th>File Name</th>
                            <th>Status</th>
                            <th>Download Raw</th>
                            <th class="text-end">Upload Report</th>
                        </tr>
                    </thead>
                    <tbody id="evidenceTable"></tbody>
                </table>
            </div>
        </section>

        <section id="case-list-section" class="d-none">
            <h4 class="fw-bold mb-4">Investigation Cases</h4>
            <div class="card border-0 shadow-sm p-4">
                <table class="table align-middle">
                    <thead class="text-muted small">
                        <tr><th>Case #</th><th>Title</th><th>Status</th><th>Officer</th></tr>
                    </thead>
                    <tbody id="caseTable"></tbody>
                </table>
            </div>
        </section>
    </div>

    <script src="/assets/js/logout.js"></script>
    <script src="/assets/js/forensic.js"></script>
</body>
</html>