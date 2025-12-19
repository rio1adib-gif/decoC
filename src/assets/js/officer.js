const API = "http://localhost:9090";
const token = localStorage.getItem("token");

let selectedCaseId = null;

document.addEventListener("DOMContentLoaded", loadCases);

// ================= LOAD CASES =================
function loadCases() {
    fetch(API + "/api/cases", {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(cases => {
        const table = document.getElementById("caseTable");
        table.innerHTML = "";

        cases.forEach(c => {
            table.innerHTML += `
                <tr>
                    <td>${c.caseId}</td>
                    <td>${c.caseNumber}</td>
                    <td>${c.title}</td>
                    <td>${c.status}</td>
                    <td>
                        <button class="btn btn-sm btn-outline-primary"
                                onclick="selectCase(${c.caseId})">
                            Select
                        </button>
                    </td>
                </tr>
            `;
        });
    });
}

// ================= CREATE CASE =================
function createCase() {

    const data = {
        caseNumber: document.getElementById("caseNumber").value,
        title: document.getElementById("title").value,
        description: document.getElementById("description").value
    };

    fetch(API + "/api/cases/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(() => {
        alert("Case created");
        loadCases();
    });
}

// ================= SELECT CASE =================
function selectCase(caseId) {
    selectedCaseId = caseId;
    loadEvidence(caseId);
}

// ================= LOAD EVIDENCE =================
function loadEvidence(caseId) {

    fetch(API + `/api/evidence/case/${caseId}`, {
        headers: { "Authorization": "Bearer " + token }
    })
    .then(res => res.json())
    .then(list => {
        const table = document.getElementById("evidenceTable");
        table.innerHTML = "";

        list.forEach(e => {
            table.innerHTML += `
                <tr>
                    <td>${e.evidenceId}</td>
                    <td>${e.fileName}</td>
                    <td>${e.status}</td>
                </tr>
            `;
        });
    });
}

// ================= UPLOAD EVIDENCE =================
function uploadEvidence() {

    if (!selectedCaseId) {
        alert("Select a case first");
        return;
    }

    const file = document.getElementById("evidenceFile").files[0];
    if (!file) {
        alert("Choose a file");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch(API + `/api/evidence/upload/${selectedCaseId}`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        },
        body: formData
    })
    .then(res => res.json())
    .then(() => {
        alert("Evidence uploaded");
        loadEvidence(selectedCaseId);
    });
}
