<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Officer Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>

<body>

<!-- OFFICER AUTH GUARD -->
<script src="/assets/js/auth-guard-officer.js"></script>

<nav class="navbar navbar-dark bg-primary px-4">
    <span class="navbar-brand fw-bold">Officer Dashboard</span>
    <button class="btn btn-danger btn-sm" onclick="logout()">Logout</button>
</nav>

<div class="container-fluid mt-4">

    <!-- CREATE CASE -->
    <div class="card mb-4 shadow-sm">
        <div class="card-header bg-dark text-white fw-bold">
            Create New Case
        </div>
        <div class="card-body">

            <div class="row g-2 mb-2">
                <div class="col-md-4">
                    <input id="caseNumber" class="form-control" placeholder="Case Number">
                </div>
                <div class="col-md-4">
                    <input id="title" class="form-control" placeholder="Case Title">
                </div>
            </div>

            <textarea id="description" class="form-control mb-3"
                      rows="3" placeholder="Case Description"></textarea>

            <button class="btn btn-success" onclick="createCase()">Create Case</button>
        </div>
    </div>

    <!-- CASE LIST -->
    <div class="card mb-4 shadow-sm">
        <div class="card-header bg-secondary text-white fw-bold">
            Case List
        </div>
        <div class="card-body">

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Case ID</th>
                        <th>Case Number</th>
                        <th>Title</th>
                        <th>Status</th>
                        <th>Select</th>
                    </tr>
                </thead>
                <tbody id="caseTable"></tbody>
            </table>

        </div>
    </div>

    <!-- EVIDENCE -->
    <div class="card shadow-sm">
        <div class="card-header bg-info text-white fw-bold">
            Evidence for Selected Case
        </div>
        <div class="card-body">

            <div class="row g-2 mb-3">
                <div class="col-md-6">
                    <input type="file" id="evidenceFile" class="form-control">
                </div>
                <div class="col-md-3">
                    <button class="btn btn-primary w-100" onclick="uploadEvidence()">
                        Upload Evidence
                    </button>
                </div>
            </div>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Evidence ID</th>
                        <th>File Name</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody id="evidenceTable"></tbody>
            </table>

        </div>
    </div>

</div>

<script src="/assets/js/logout.js"></script>
<script src="/assets/js/officer.js"></script>
</body>
</html>
