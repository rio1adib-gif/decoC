const API = "http://localhost:9090";
const token = localStorage.getItem("token");

async function loadUsers() {
    try {
        const res = await fetch(`${API}/api/admin/users`, {
            headers: { "Authorization": `Bearer ${token}` }
        });

        if (res.status === 401 || res.status === 403) {
            logout(); // Redirect to login if token is invalid
            return;
        }

        const users = await res.json();
        // logic to render table...
    } catch (err) {
        console.error("System Error:", err);
    }
}

function logout() {
    localStorage.clear();
    window.location.replace("/");
}