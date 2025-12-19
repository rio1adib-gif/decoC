const API = "http://localhost:9091";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", loadUsers);

/**
 * Fetches the user list from the Admin API and renders the management table.
 * Includes the new Badge #, Department, and Joined Date fields.
 */
function loadUsers() {
    fetch(`${API}/api/admin/users`, { 
        headers: { "Authorization": `Bearer ${token}` } 
    })
    .then(res => {
        if (!res.ok) {
            if (res.status === 401 || res.status === 403) logout();
            throw new Error("Failed to load users");
        }
        return res.json();
    })
    .then(users => {
        const table = document.getElementById("userTable");
        table.innerHTML = "";

        users.forEach(u => {
            // Render the table row with real identification data
            table.innerHTML += `
                <tr>
                    <td>${u.fullName || 'N/A'}</td>
                    <td>${u.username}</td>
                    <td>${u.badgeNumber || '-'}</td>   
                    <td>${u.department || '-'}</td>    
                    <td><span class="badge-role">${u.role.replace("ROLE_", "")}</span></td>
                    <td>${u.joinedDate ? new Date(u.joinedDate).toLocaleDateString() : '12/19/2025'}</td>
                    <td class="text-end">
                        <button class="btn btn-link btn-sm text-decoration-none text-muted" 
                                onclick="changeRole(${u.userId})">
                            <i class="fas fa-pencil small me-1"></i> Edit Role
                        </button>
                    </td>
                </tr>`;
        });
    })
    .catch(err => console.error("Error loading user management table:", err));
}

/**
 * Prompts the admin to enter a new role for a specific user.
 * Sends a PUT request to update the database.
 */
function changeRole(userId) {
    const newRole = prompt("Enter New Role (ADMIN, OFFICER, or FORENSIC):");
    if (!newRole) return;

    fetch(`${API}/api/admin/users/${userId}/role?newRole=${newRole.toUpperCase()}`, {
        method: "PUT",
        headers: { "Authorization": `Bearer ${token}` }
    })
    .then(res => {
        if (res.ok) {
            alert("Role updated successfully");
            loadUsers(); // Refresh the table
        } else {
            alert("Failed to update role. Ensure you have admin privileges.");
        }
    })
    .catch(err => alert("Error updating role: " + err.message));
}

/**
 * Clears local session data and redirects the user to the login page.
 */
function logout() { 
    localStorage.clear(); 
    window.location.replace("/"); 
}