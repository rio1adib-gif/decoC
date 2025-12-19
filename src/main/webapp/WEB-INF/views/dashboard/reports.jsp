<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forensic Reports</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>

<body>

<!-- AUTH GUARD (ADMIN + OFFICER) -->
<script src="/assets/js/auth-guard-report.js"></script>

<nav class="navbar navbar-dark bg-secondary px-4">
    <span class="navbar-brand fw-bold">Forensic Reports</span>
    <button class="btn btn-danger btn-sm" onclick="logout()">Logout</button>
</nav>

<div class="container-fluid mt-4">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-3 sidebar">
            <h5 class="fw-bold mb-3">Reports Panel</h5>
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/dashboard/admin.jsp" class="text-decoration-none text-dark">
                        Dashboard
                    </a>
                </li>
                <li class="list-group-item active">Reports</li>
            </ul>
        </div>

        <!-- MAIN -->
        <div class="col-md-9">
            <h4 class="mb-3">Available Forensic Reports</h4>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Report ID</th>
                        <th>Evidence ID</th>
                        <th>File Name</th>
                        <th>Uploaded By</th>
                        <th>Uploaded At</th>
                        <th>Download</th>
                    </tr>
                </thead>
                <tbody id="reportTable"></tbody>
            </table>
        </div>

    </div>
</div>

<script src="/assets/js/logout.js"></script>
<script src="/assets/js/reports.js"></script>
</body>
</html>
