/* File: src/assets/js/forensic.js */
const API = "http://localhost:9091";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById('header-user-name').innerText = localStorage.getItem('username') || 'Forensic Officer';
    loadEvidence();
    loadCases();
});

function showSection(sectionId) {
    document.querySelectorAll('section').forEach(s => s.classList.add('d-none'));
    document.getElementById(sectionId + '-section').classList.remove('d-none');

    document.querySelectorAll('.sidebar .nav-link').forEach(l => {
        l.classList.remove('active');
        l.classList.add('text-white');
    });
    
    const mapping = { 'overview': 'link-overview', 'evidence-list': 'link-evidence', 'case-list': 'link-case' };
    document.getElementById(mapping[sectionId]).classList.add('active');
    document.getElementById(mapping[sectionId]).classList.remove('text-white');
}

// 1. VIEW EVIDENCE & 2. DOWNLOAD EVIDENCE
function loadEvidence() {
    fetch(API + "/api/evidence", { headers: { "Authorization": "Bearer " + token } })
    .then(res => res.json())
    .then(list => {
        const table = document.getElementById("evidenceTable");
        table.innerHTML = "";
        
        let pending = 0;
        document.getElementById('stat-total-evidence').innerText = list.length;

        list.forEach(e => {
            if (e.status === "UPLOADED") pending++;
            table.innerHTML += `
                <tr>
                    <td>${e.evidenceId}</td>
                    <td>${e.fileName}</td>
                    <td><span class="badge ${e.status === 'ANALYZED' ? 'bg-success' : 'bg-warning'}">${e.status}</span></td>
                    <td>
                        <button class="btn btn-sm btn-outline-primary" onclick="downloadEvidence(${e.evidenceId}, '${e.fileName}')">
                            <i class="fas fa-download"></i> Download
                        </button>
                    </td>
                    <td class="text-end">
                        <div class="input-group input-group-sm" style="max-width: 250px; float: right;">
                            <input type="file" id="report-${e.evidenceId}" class="form-control">
                            <button class="btn btn-success" onclick="uploadReport(${e.evidenceId})">Upload</button>
                        </div>
                    </td>
                </tr>`;
        });
        document.getElementById('stat-pending').innerText = pending;
    });
}

function downloadEvidence(id, name) {
    fetch(`${API}/api/evidence/download/${id}`, { headers: { "Authorization": "Bearer " + token } })
    .then(res => res.blob())
    .then(blob => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "RAW_" + name;
        document.body.appendChild(a);
        a.click();
        a.remove();
    });
}

// 3. VIEW CASES
function loadCases() {
    fetch(API + "/api/cases", { headers: { "Authorization": "Bearer " + token } })
    .then(res => res.json())
    .then(cases => {
        const table = document.getElementById("caseTable");
        table.innerHTML = "";
        cases.forEach(c => {
            table.innerHTML += `<tr><td>${c.caseNumber}</td><td>${c.title}</td><td>${c.status}</td><td>${c.createdBy}</td></tr>`;
        });
    });
}

// 4. UPLOAD ANALYSIS REPORT
function uploadReport(evidenceId) {
    const fileInput = document.getElementById(`report-${evidenceId}`);
    const file = fileInput.files[0];

    if (!file) return alert("Select a report file first");

    const formData = new FormData();
    formData.append("file", file);

    fetch(`${API}/api/reports/upload-analysis/${evidenceId}`, {
        method: "POST",
        headers: { "Authorization": "Bearer " + token },
        body: formData
    }).then(() => {
        alert("Forensic Report Uploaded & Logged");
        loadEvidence();
    });
}

function logout() { localStorage.clear(); window.location.href = "/"; }