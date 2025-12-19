<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Audit Logs</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>

<body>

<!-- ADMIN AUTH GUARD -->
<script src="/assets/js/auth-guard-admin.js"></script>

<nav class="navbar navbar-dark bg-dark px-4">
    <span class="navbar-brand fw-bold">Audit Logs</span>
    <button class="btn btn-danger btn-sm" onclick="logout()">Logout</button>
</nav>

<div class="container-fluid mt-4">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-3 sidebar">
            <h5>Admin Panel</h5>
            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/admin" class="text-decoration-none">Users</a>
                </li>
                <li class="list-group-item active">Audit Logs</li>
            </ul>
        </div>

        <!-- MAIN CONTENT -->
        <div class="col-md-9">
            <h4>System Audit Logs</h4>

            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Action</th>
                        <th>Entity</th>
                        <th>Entity ID</th>
                        <th>Timestamp</th>
                    </tr>
                </thead>
                <tbody id="auditTable"></tbody>
            </table>
        </div>

    </div>
</div>

<script src="/assets/js/logout.js"></script>
<script src="/assets/js/audit.js"></script>

</body>
</html>
