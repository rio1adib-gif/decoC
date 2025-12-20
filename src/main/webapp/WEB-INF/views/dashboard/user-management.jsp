<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>
<body class="d-flex">
    <div class="sidebar d-flex flex-column p-3 bg-dark-blue text-white" style="width: 280px; min-height: 100vh;">
        <div class="mb-4 px-2"><i class="fas fa-shield-halved me-2"></i> <b>Evidence Custody</b></div>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="/admin" class="nav-link"><i class="fas fa-th-large me-2"></i> Dashboard</a></li>
            <li><a href="/admin/evidence" class="nav-link"><i class="fas fa-cube me-2"></i> Evidence</a></li>
            <li><a href="/admin/cases" class="nav-link"><i class="fas fa-folder me-2"></i> Cases</a></li>
            <li><a href="/reports" class="nav-link"><i class="fas fa-file-lines me-2"></i> Reports</a></li>
            <li><a href="/audit" class="nav-link"><i class="fas fa-list-check me-2"></i> Audit Logs</a></li>
            <li><a href="/user-management" class="nav-link active"><i class="fas fa-users-gear me-2"></i> User Management</a></li>
        </ul>
        <hr><button class="btn btn-link text-danger p-0 text-decoration-none" onclick="logout()"><i class="fas fa-sign-out-alt me-2"></i> Sign Out</button>
    </div>
    <div class="flex-grow-1 p-5">
        <h4 class="fw-bold mb-4">User Management</h4>
        <div class="card border-0 shadow-sm p-4">
            <h5 class="fw-bold">System Users</h5>
            <input type="text" class="form-control mb-4" style="max-width: 300px;" placeholder="Search...">
            <table class="table align-middle">
                <thead class="text-muted small"><tr><th>Name</th><th>Email</th><th>Badge #</th><th>Department</th><th>Role</th><th>Joined</th><th class="text-end">Actions</th></tr></thead>
                <tbody id="userTable"></tbody>
            </table>
        </div>
    </div>
    <script src="/assets/js/logout.js"></script><script src="/assets/js/admin.js"></script>
</body>
</html>