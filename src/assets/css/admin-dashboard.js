/* File: src/assets/js/admin-dashboard.js */
const API = "http://localhost:9091";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", () => {
    // Redirect if not admin
    if (!token || localStorage.getItem("role") !== "ROLE_ADMIN") {
        window.location.href = "/";
        return;
    }

    loadDashboardStats();
    loadRecentActivity();
});

/**
 * Fetches counts for Evidence, Cases, and Reports to populate the dashboard cards.
 */
function loadDashboardStats() {
    const headers = { "Authorization": `Bearer ${token}` };

    // 1. Evidence Count & Pending Count
    fetch(`${API}/api/evidence`, { headers })
        .then(res => res.json())
        .then(data => {
            // Total Evidence
            updateText("stat-evidence", data.length);
            
            // Pending = Items with status 'UPLOADED' (not yet ANALYZED)
            const pendingCount = data.filter(e => e.status === 'UPLOADED').length;
            updateText("stat-pending", pendingCount);
        })
        .catch(err => console.error("Error loading evidence stats:", err));

    // 2. Active Cases Count
    fetch(`${API}/api/cases`, { headers })
        .then(res => res.json())
        .then(data => updateText("stat-cases", data.length))
        .catch(err => console.error("Error loading case stats:", err));

    // 3. Reports Count
    fetch(`${API}/api/reports`, { headers })
        .then(res => res.json())
        .then(data => updateText("stat-reports", data.length))
        .catch(err => console.error("Error loading report stats:", err));
}

/**
 * Fetches the latest audit logs and displays the most recent 5 activities.
 */
function loadRecentActivity() {
    fetch(`${API}/api/audit/logs`, { 
        headers: { "Authorization": `Bearer ${token}` } 
    })
    .then(res => res.json())
    .then(logs => {
        const container = document.getElementById("activity-list");
        container.innerHTML = "";

        // Sort by ID descending (newest first) and take top 5
        const recentLogs = logs.sort((a, b) => b.auditId - a.auditId).slice(0, 5);

        if (recentLogs.length === 0) {
            container.innerHTML = `<p class="text-muted small text-center my-3">No recent activity recorded.</p>`;
            return;
        }

        recentLogs.forEach(log => {
            const timeString = new Date(log.timestamp).toLocaleString();
            const actionReadable = log.action.replace(/_/g, ' '); // e.g., "UPLOAD_EVIDENCE" -> "UPLOAD EVIDENCE"

            const html = `
                <div class="d-flex align-items-start border-bottom py-2">
                    <div class="me-3 mt-1 text-secondary">
                        <i class="fas fa-circle-info small"></i>
                    </div>
                    <div class="flex-grow-1">
                        <div class="d-flex justify-content-between">
                            <span class="fw-bold small text-dark">${actionReadable}</span>
                            <span class="text-muted" style="font-size: 0.75rem;">${timeString}</span>
                        </div>
                        <div class="small text-muted">
                            User: <span class="fw-semibold text-dark">${log.username}</span> 
                            ${log.entityType ? `| ${log.entityType} ID: ${log.entityId || 'N/A'}` : ''}
                        </div>
                    </div>
                </div>
            `;
            container.innerHTML += html;
        });
    })
    .catch(err => console.error("Error loading activity:", err));
}

/**
 * Helper to safely update text content of an element
 */
function updateText(elementId, value) {
    const el = document.getElementById(elementId);
    if (el) {
        el.innerText = value;
    } else {
        console.warn(`Element #${elementId} not found in DOM.`);
    }
}