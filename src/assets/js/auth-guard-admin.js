(function() {
    const role = localStorage.getItem("role");
    const token = localStorage.getItem("token");

    if (!token || role !== "ROLE_ADMIN") {
        window.location.replace("/"); // Redirect to login
    }
})();