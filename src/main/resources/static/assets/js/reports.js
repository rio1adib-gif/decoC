/* File: src/assets/js/reports.js */
const API = ""; // Changed from http://localhost:9091
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", loadReports);

function loadReports() {
    fetch(API + "/api/reports", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(list => {
        const table = document.getElementById("reportTable");
        table.innerHTML = "";

        list.forEach(r => {
            table.innerHTML += `
                <tr>
                    <td>${r.reportId}</td>
                    <td>${r.evidence.evidenceId}</td>
                    <td>${r.reportFileName}</td>
                    <td>${r.uploadedBy}</td>
                    <td>${r.uploadedAt}</td>
                    <td>
                        <button class="btn btn-sm btn-primary"
                                onclick="downloadReport(${r.reportId})">
                            Download
                        </button>
                    </td>
                </tr>
            `;
        });
    });
}

function downloadReport(reportId) {
    fetch(API + `/api/reports/download/${reportId}`, {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "forensic-report-" + reportId;
        document.body.appendChild(a);
        a.click();
        a.remove();
    });
}