/* File: src/assets/js/auth.js */
const API_BASE = ""; // Changed from http://localhost:9091

function toggleAuthMode(mode) {
    const signInForm = document.getElementById('signin-form');
    const createForm = document.getElementById('create-form');
    const btnSignIn = document.getElementById('btn-tab-signin');
    const btnCreate = document.getElementById('btn-tab-create');
    document.getElementById('error-message').innerText = "";

    if (mode === 'signin') {
        signInForm.classList.remove('d-none');
        createForm.classList.add('d-none');
        btnSignIn.classList.add('active');
        btnCreate.classList.remove('active');
    } else {
        signInForm.classList.add('d-none');
        createForm.classList.remove('d-none');
        btnSignIn.classList.remove('active');
        btnCreate.classList.add('active');
    }
}

function login() {
    const username = document.getElementById("login-username").value;
    const password = document.getElementById("login-password").value;

    fetch(API_BASE + "/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => {
        if (!res.ok) throw new Error("Invalid credentials");
        return res.json();
    })
    .then(data => {
        // 1. Save to LocalStorage (for API calls)
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);
        localStorage.setItem("username", data.username);

        // 2. Save to Cookie (for JSP View protection)
        document.cookie = `jwt_token=${data.token}; path=/; max-age=3600; SameSite=Strict`;

        // 3. Redirect based on role
        if (data.role === "ROLE_ADMIN") window.location.href = "/admin";
        else if (data.role === "ROLE_OFFICER") window.location.href = "/officer";
        else if (data.role === "ROLE_FORENSIC") window.location.href = "/forensic";
    })
    .catch(err => {
        document.getElementById("error-message").innerText = err.message;
    });
}

function register() {
    const fullName = document.getElementById("reg-fullname").value;
    const username = document.getElementById("reg-username").value;
    const password = document.getElementById("reg-password").value;

    fetch(API_BASE + "/api/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ fullName, username, password })
    })
    .then(res => {
        if (!res.ok) throw new Error("Registration failed");
        alert("Account created! Please sign in.");
        toggleAuthMode('signin');
    })
    .catch(err => {
        document.getElementById("error-message").innerText = err.message;
    });
}