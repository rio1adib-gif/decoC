<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Officer Dashboard | Digital Evidence Custody</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/officer-dashboard.css">
</head>
<body class="d-flex">

    <script src="/assets/js/auth-guard-officer.js"></script>

    <div class="sidebar d-flex flex-column p-3 text-white bg-dark-blue" style="width: 280px; min-height: 100vh;">
        <div class="d-flex align-items-center mb-4 px-2">
            <i class="fas fa-shield-halved me-2"></i>
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
                    <i class="fas fa-cube me-2"></i> Evidence
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
                <h2 class="fw-bold">Welcome back, <span id="user-display-name">Officer</span></h2>
                <p class="text-muted">Here's an overview of the evidence management system</p>
            </div>
            <div class="text-end">
                <span class="fw-bold d-block" id="header-user-name">Officer Name</span>
                <span class="small text-muted">Investigator</span>
            </div>
        </header>

        <section id="overview-section">
            <div class="row g-4 mb-5">
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div><div class="h3 fw-bold mb-0" id="stat-evidence">0</div><div class="small text-muted">Items in custody</div></div>
                            <div class="stat-icon bg-light rounded p-2"><i class="fas fa-cube text-muted"></i></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div><div class="h3 fw-bold mb-0">0</div><div class="small text-muted">Pending Review</div></div>
                            <div class="stat-icon bg-warning-light rounded p-2"><i class="fas fa-bolt text-warning"></i></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card border-0 shadow-sm p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <div><div class="h3 fw-bold mb-0" id="stat-cases">0</div><div class="small text-muted">Active Cases</div></div>
                            <div class="stat-icon bg-info-light rounded p-2"><i class="fas fa-folder text-info"></i></div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row g-4">
                <div class="col-md-4">
                    <div class="card border-0 shadow-sm p-4 action-card" onclick="showSection('add-evidence')">
                        <i class="fas fa-plus text-primary mb-3 h4"></i>
                        <h5 class="fw-bold">Add New Evidence</h5>
                        <p class="small text-muted">Log a new piece of digital evidence</p>
                        <button class="btn btn-link p-0 text-decoration-none text-dark fw-bold mt-2">Add Evidence <i class="fas fa-arrow-right small"></i></button>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card border-0 shadow-sm p-4 action-card" onclick="showSection('create-case')">
                        <i class="fas fa-folder-plus text-info mb-3 h4"></i>
                        <h5 class="fw-bold">Create New Case</h5>
                        <p class="small text-muted">Open a new investigation case</p>
                        <button class="btn btn-link p-0 text-decoration-none text-dark fw-bold mt-2">Create Case <i class="fas fa-arrow-right small"></i></button>
                    </div>
                </div>
            </div>
        </section>

        <section id="add-evidence-section" class="d-none">
            <div class="mb-4">
                <button class="btn btn-link text-muted p-0" onclick="showSection('overview')"><i class="fas fa-arrow-left"></i></button>
                <h4 class="fw-bold d-inline ms-2">Add New Evidence</h4>
            </div>
            <div class="card border-0 shadow-sm mx-auto" style="max-width: 700px;">
                <div class="card-body p-4">
                    <div class="mb-3">
                        <label class="form-label small fw-bold">Title *</label>
                        <input type="text" id="evidenceTitle" class="form-control" placeholder="e.g., Hard Drive">
                    </div>
                    <div class="mb-3">
                        <label class="form-label small fw-bold">Associated Case</label>
                        <select id="evidenceCaseSelect" class="form-select"></select>
                    </div>
                    <div class="mb-4">
                        <label class="form-label small fw-bold">Evidence File</label>
                        <div class="upload-area text-center p-5 border rounded" onclick="document.getElementById('evidenceFile').click()">
                            <i class="fas fa-upload mb-2 text-muted h3"></i>
                            <div id="file-label" class="small text-muted">Click to upload or drag and drop</div>
                            <input type="file" id="evidenceFile" class="d-none" onchange="updateFileLabel()">
                        </div>
                    </div>
                    <div class="d-flex justify-content-end gap-2">
                        <button class="btn btn-light" onclick="showSection('overview')">Cancel</button>
                        <button class="btn btn-primary-dark" onclick="uploadEvidence()">Add Evidence</button>
                    </div>
                </div>
            </div>
        </section>

        <section id="create-case-section" class="d-none">
            <div class="mb-4">
                <button class="btn btn-link text-muted p-0" onclick="showSection('overview')"><i class="fas fa-arrow-left"></i></button>
                <h4 class="fw-bold d-inline ms-2">Create New Case</h4>
            </div>
            <div class="card border-0 shadow-sm mx-auto" style="max-width: 600px;">
                <div class="card-body p-4">
                    <div class="mb-3">
                        <label class="form-label small fw-bold">Case Number *</label>
                        <input type="text" id="caseNumber" class="form-control" placeholder="CASE-2025-XXXX">
                    </div>
                    <div class="mb-3">
                        <label class="form-label small fw-bold">Case Title *</label>
                        <input type="text" id="title" class="form-control" placeholder="Investigation Title">
                    </div>
                    <div class="mb-3">
                        <label class="form-label small fw-bold">Description</label>
                        <textarea id="description" class="form-control" rows="3"></textarea>
                    </div>
                    <div class="d-flex justify-content-end gap-2">
                        <button class="btn btn-light" onclick="showSection('overview')">Cancel</button>
                        <button class="btn btn-primary-dark" onclick="createCase()">Create Case</button>
                    </div>
                </div>
            </div>
        </section>

        <section id="case-list-section" class="d-none">
            <h4 class="fw-bold mb-4">Case Management</h4>
            <div class="card border-0 shadow-sm p-4">
                <table class="table align-middle">
                    <thead class="text-muted small">
                        <tr><th>Case #</th><th>Title</th><th>Status</th><th>Created</th></tr>
                    </thead>
                    <tbody id="caseTable"></tbody>
                </table>
            </div>
        </section>

        <section id="evidence-list-section" class="d-none">
            <h4 class="fw-bold mb-4">Evidence Management</h4>
            <div class="card border-0 shadow-sm p-4">
                <table class="table align-middle">
                    <thead class="text-muted small">
                        <tr><th>Evidence ID</th><th>File Name</th><th>Status</th><th>Uploaded At</th></tr>
                    </thead>
                    <tbody id="evidenceTable"></tbody>
                </table>
            </div>
        </section>

    </div>

    <script src="/assets/js/logout.js"></script>
    <script src="/assets/js/officer.js"></script>
</body>
</html>