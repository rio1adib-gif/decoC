const role = localStorage.getItem("role");
const token = localStorage.getItem("token");

if (!token || (role !== "ROLE_ADMIN" && role !== "ROLE_OFFICER")) {
    alert("Unauthorized access");
    window.location.href = "/";
}
