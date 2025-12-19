const API = "http://localhost:9091";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", () => {
    // Basic user info from localStorage (requires you to save fullName during login)
    const name = localStorage.getItem("username") || "Officer";
    document.getElementById('user-display-name').innerText = name;
    document.getElementById('header-user-name').innerText = name;
    
    refreshData();
});

function refreshData() {
    loadCases();
    loadAllEvidence();
}

function showSection(sectionId) {
    document.querySelectorAll('section').forEach(s => s.classList.add('d-none'));
    document.getElementById(sectionId + '-section').classList.remove('d-none');

    // Update sidebar active state
    document.querySelectorAll('.sidebar .nav-link').forEach(l => {
        l.classList.remove('active');
        l.classList.add('text-white');
    });
    
    const activeLink = {
        'overview': 'link-overview',
        'add-evidence': 'link-evidence',
        'evidence-list': 'link-evidence',
        'create-case': 'link-case',
        'case-list': 'link-case'
    }[sectionId];
    
    if (activeLink) {
        document.getElementById(activeLink).classList.add('active');
        document.getElementById(activeLink).classList.remove('text-white');
    }
}

function updateFileLabel() {
    const file = document.getElementById("evidenceFile").files[0];
    if (file) document.getElementById("file-label").innerText = file.name;
}

// Load Cases into both the Table and the Dropdown
function loadCases() {
    fetch(API + "/api/cases", { headers: { "Authorization": "Bearer " + token } })
    .then(res => res.json())
    .then(cases => {
        const table = document.getElementById("caseTable");
        const select = document.getElementById("evidenceCaseSelect");
        table.innerHTML = "";
        select.innerHTML = '<option value="">Select a case (optional)</option>';
        
        document.getElementById('stat-cases').innerText = cases.length;

        cases.forEach(c => {
            table.innerHTML += `<tr><td>${c.caseNumber}</td><td>${c.title}</td>
                <td><span class="badge bg-info">${c.status}</span></td><td>${c.createdAt}</td></tr>`;
            select.innerHTML += `<option value="${c.caseId}">${c.caseNumber} - ${c.title}</option>`;
        });
    });
}

function createCase() {
    const data = {
        caseNumber: document.getElementById("caseNumber").value,
        title: document.getElementById("title").value,
        description: document.getElementById("description").value
    };

    fetch(API + "/api/cases/create", {
        method: "POST",
        headers: { "Content-Type": "application/json", "Authorization": "Bearer " + token },
        body: JSON.stringify(data)
    }).then(() => {
        alert("Case created");
        refreshData();
        showSection('case-list');
    });
}

// Load All Evidence for the Evidence Section
function loadAllEvidence() {
    // Note: Requires backend to support /api/evidence listing
    fetch(API + "/api/evidence", { headers: { "Authorization": "Bearer " + token } })
    .then(res => res.json())
    .then(list => {
        const table = document.getElementById("evidenceTable");
        table.innerHTML = "";
        document.getElementById('stat-evidence').innerText = list.length;
        list.forEach(e => {
            table.innerHTML += `<tr><td>${e.evidenceId}</td><td>${e.fileName}</td>
                <td><span class="badge bg-success">${e.status}</span></td><td>${e.uploadedAt}</td></tr>`;
        });
    });
}

function uploadEvidence() {
    const caseId = document.getElementById("evidenceCaseSelect").value;
    const file = document.getElementById("evidenceFile").files[0];

    if (!caseId || !file) return alert("Select case and file");

    const formData = new FormData();
    formData.append("file", file);

    fetch(API + `/api/evidence/upload/${caseId}`, {
        method: "POST",
        headers: { "Authorization": "Bearer " + token },
        body: formData
    }).then(() => {
        alert("Evidence uploaded");
        refreshData();
        showSection('evidence-list');
    });
}

function logout() { localStorage.clear(); window.location.href = "/"; }