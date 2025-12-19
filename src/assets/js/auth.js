const API_BASE = "http://localhost:9090";

function login() {

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

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

        // Save JWT
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);

        // ROLE BASED REDIRECT
        if (data.role === "ROLE_ADMIN") {
            window.location.href = "/admin";
        }
        else if (data.role === "ROLE_OFFICER") {
            window.location.href = "/officer";
        }
        else if (data.role === "ROLE_FORENSIC") {
            window.location.href = "/forensic";
        }
        else {
            alert("Unknown role");
        }
    })
    .catch(err => {
        document.getElementById("error").innerText = "Login failed";
    });
}
