<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login | Digital Evidence Custody</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="assets/css/auth.css">
</head>
<body class="bg-dark d-flex justify-content-center align-items-center vh-100">

<div class="card p-4 shadow" style="width:360px;">
    <h4 class="text-center mb-3">🔐 Login</h4>

    <input id="username" class="form-control mb-2" placeholder="Username">
    <input id="password" type="password" class="form-control mb-3" placeholder="Password">

    <button class="btn btn-primary w-100" onclick="login()">Login</button>

    <p id="error" class="text-danger text-center mt-2"></p>
</div>

<script src="assets/js/auth.js"></script>
</body>
</html>
