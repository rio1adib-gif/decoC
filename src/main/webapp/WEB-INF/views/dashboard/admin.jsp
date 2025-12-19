<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard | Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>
<body class="d-flex">
    <div class="sidebar d-flex flex-column p-3 bg-dark-blue text-white" style="width: 280px; min-height: 100vh;">
        <div class="mb-4 px-2"><i class="fas fa-shield-halved me-2"></i> <b>Evidence Custody</b></div>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="/admin" class="nav-link active"><i class="fas fa-th-large me-2"></i> Dashboard</a></li>
            <li><a href="/admin/evidence" class="nav-link"><i class="fas fa-cube me-2"></i> Evidence</a></li>
            <li><a href="/admin/cases" class="nav-link"><i class="fas fa-folder me-2"></i> Cases</a></li>
            <li><a href="/reports" class="nav-link"><i class="fas fa-file-lines me-2"></i> Reports</a></li>
            <li><a href="/audit" class="nav-link"><i class="fas fa-list-check me-2"></i> Audit Logs</a></li>
            <li><a href="/user-management" class="nav-link"><i class="fas fa-users-gear me-2"></i> User Management</a></li>
        </ul>
        <hr><button class="btn btn-link text-danger p-0 text-decoration-none" onclick="logout()"><i class="fas fa-sign-out-alt me-2"></i> Sign Out</button>
    </div>
    <div class="flex-grow-1 p-5">
        <header class="d-flex justify-content-between mb-5">
            <div><h2 class="fw-bold">Welcome back, John Admin</h2><p class="text-muted">System Overview</p></div>
            <div class="text-end"><b>John Admin</b><br><small class="text-muted">Admin</small></div>
        </header>
        <div class="row g-4 mb-5">
            <div class="col-md-3"><div class="card border-0 shadow-sm p-3"><div class="d-flex justify-content-between"><div><h3>1</h3><small>Evidence</small></div><div class="stat-icon bg-light"><i class="fas fa-cube"></i></div></div></div></div>
            <div class="col-md-3"><div class="card border-0 shadow-sm p-3"><div class="d-flex justify-content-between"><div><h3>1</h3><small>Pending</small></div><div class="stat-icon bg-warning-light"><i class="fas fa-bolt"></i></div></div></div></div>
            <div class="col-md-3"><div class="card border-0 shadow-sm p-3"><div class="d-flex justify-content-between"><div><h3>1</h3><small>Cases</small></div><div class="stat-icon bg-info-light"><i class="fas fa-folder"></i></div></div></div></div>
            <div class="col-md-3"><div class="card border-0 shadow-sm p-3"><div class="d-flex justify-content-between"><div><h3>1</h3><small>Reports</small></div><div class="stat-icon bg-success-light"><i class="fas fa-file"></i></div></div></div></div>
        </div>
        <div class="card border-0 shadow-sm p-4"><h5 class="fw-bold mb-4">Recent Activity</h5><div id="activity-list"></div></div>
    </div>
    <script src="/assets/js/logout.js"></script><script src="/assets/js/admin-dashboard.js"></script>
</body>
</html>