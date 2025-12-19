<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Forensic Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>

<body>

<!-- FORENSIC AUTH GUARD -->
<script src="/assets/js/auth-guard-forensic.js"></script>

<nav class="navbar navbar-dark bg-success px-4">
    <span class="navbar-brand fw-bold">Forensic Dashboard</span>
    <button class="btn btn-danger btn-sm" onclick="logout()">Logout</button>
</nav>

<div class="container-fluid mt-4">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-3 sidebar">
            <h5>Forensic Panel</h5>
            <ul class="list-group">
                <li class="list-group-item active">Evidence</li>
            </ul>
        </div>

        <!-- MAIN -->
        <div class="col-md-9">
            <h4>Evidence List</h4>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>Evidence ID</th>
                        <th>File Name</th>
                        <th>Status</th>
                        <th>Upload Report</th>
                    </tr>
                </thead>
                <tbody id="evidenceTable"></tbody>
            </table>
        </div>

    </div>
</div>

<script src="/assets/js/logout.js"></script>
<script src="/assets/js/forensic.js"></script>
</body>
</html>
