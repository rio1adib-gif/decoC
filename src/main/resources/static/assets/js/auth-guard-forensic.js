const role = localStorage.getItem("role");
const token = localStorage.getItem("token");

if (!token || role !== "ROLE_FORENSIC") {
    alert("Unauthorized access");
    window.location.href = "/";
}
