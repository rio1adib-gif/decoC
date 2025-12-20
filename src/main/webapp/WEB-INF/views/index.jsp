<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login | Digital Evidence Custody</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <link rel="stylesheet" href="/assets/css/auth.css"> 
</head>
<body class="auth-bg">

<div class="auth-container">
    <div class="text-center mb-4">
        <div class="shield-icon-wrapper mb-3">
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/></svg>
        </div>
        <h2 class="system-title">Digital Evidence</h2>
        <p class="system-subtitle">Chain of Custody System</p>
    </div>

    <div class="card auth-card border-0 shadow-sm">
        <div class="card-body p-0">
            <div class="auth-tabs d-flex p-1 bg-light rounded-top">
                <button id="btn-tab-signin" class="btn btn-tab active flex-fill" onclick="toggleAuthMode('signin')">Sign In</button>
                <button id="btn-tab-create" class="btn btn-tab flex-fill" onclick="toggleAuthMode('create')">Create Account</button>
            </div>

            <div class="p-4">
                <div id="signin-form">
                    <p class="auth-instruction">Enter your credentials to access the system</p>
                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Email</label>
                        <input type="email" id="login-username" class="form-control" placeholder="officer@department.gov">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Password</label>
                        <input type="password" id="login-password" class="form-control">
                    </div>
                    <button class="btn btn-primary-dark w-100 py-2 mt-2" onclick="login()">Sign In</button>
                </div>

                <div id="create-form" class="d-none">
                    <p class="auth-instruction">Create a new account to get started</p>
                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Full Name</label>
                        <input type="text" id="reg-fullname" class="form-control" placeholder="John Smith">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Email</label>
                        <input type="email" id="reg-username" class="form-control" placeholder="officer@department.gov">
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-semibold small">Password</label>
                        <input type="password" id="reg-password" class="form-control" placeholder="Minimum 6 characters">
                    </div>
                    <button class="btn btn-primary-dark w-100 py-2 mt-2" onclick="register()">Create Account</button>
                </div>
                <p id="error-message" class="text-danger text-center small mt-3 mb-0"></p>
            </div>
        </div>
    </div>
    <div class="text-center mt-4">
        <p class="auth-footer">Authorized personnel only. All activities are logged.</p>
    </div>
</div>

<script src="/assets/js/auth.js"></script>

</body>
</html>