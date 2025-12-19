<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/assets/css/dashboard.css">
</head>

<body>

<!-- ADMIN AUTH GUARD -->
<script src="/assets/js/auth-guard-admin.js"></script>

<nav class="navbar navbar-dark bg-dark px-4">
    <span class="navbar-brand fw-bold">Digital Evidence Custody – Admin</span>
    <button class="btn btn-danger btn-sm" onclick="logout()">Logout</button>
</nav>

<div class="container-fluid mt-4">
    <div class="row">

        <!-- SIDEBAR -->
        <div class="col-md-3 sidebar">
            <h5>Admin Panel</h5>
            <ul class="list-group">
                <li class="list-group-item active">Users</li>
                <li class="list-group-item">
                    <a href="/audit" class="text-decoration-none">Audit Logs</a>
                </li>
            </ul>
        </div>

        <!-- MAIN -->
        <div class="col-md-9">

            <h4>User Management</h4>

            <!-- CREATE USER -->
            <div class="card p-3 mb-4">
                <h6>Create New User</h6>

                <div class="row g-2">
                    <div class="col">
                        <input id="username" class="form-control" placeholder="Username">
                    </div>

                    <div class="col">
                        <input id="password" type="password" class="form-control" placeholder="Password">
                    </div>

                    <div class="col">
                        <select id="role" class="form-select">
                            <option value="ADMIN">ADMIN</option>
                            <option value="OFFICER">OFFICER</option>
                            <option value="FORENSIC">FORENSIC</option>
                        </select>
                    </div>

                    <div class="col">
                        <button class="btn btn-primary w-100" onclick="createUser()">Create</button>
                    </div>
                </div>
            </div>

            <!-- USER TABLE -->
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="userTable"></tbody>
            </table>

        </div>
    </div>
</div>

<script src="/assets/js/logout.js"></script>
<script src="/assets/js/admin.js"></script>
</body>
</html>
