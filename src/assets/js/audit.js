/* File: src/assets/js/audit.js */
const API = "http://localhost:9091";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", loadAuditLogs);

function loadAuditLogs() {
    fetch(API + "/api/audit/logs", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(logs => {
        const table = document.getElementById("auditTable");
        table.innerHTML = "";

        logs.forEach(log => {
            table.innerHTML += `
                <tr>
                    <td>${log.auditId}</td> <td>${log.username}</td>
                    <td>${log.action}</td>
                    <td>${log.entityType}</td>
                    <td>${log.entityId ?? "-"}</td>
                    <td>${log.timestamp}</td>
                </tr>
            `;
        });
    });
}