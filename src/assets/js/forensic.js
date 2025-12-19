const API = "http://localhost:9090";
const token = localStorage.getItem("token");

document.addEventListener("DOMContentLoaded", loadEvidence);

// ================= LOAD EVIDENCE =================
function loadEvidence() {

    fetch(API + "/api/evidence", {
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
                    <td>
                        <input type="file" id="file-${e.evidenceId}" class="form-control mb-1">
                        <button class="btn btn-sm btn-success"
                                onclick="uploadReport(${e.evidenceId})">
                            Upload Report
                        </button>
                    </td>
                </tr>
            `;
        });
    });
}

// ================= UPLOAD REPORT =================
function uploadReport(evidenceId) {

    const fileInput = document.getElementById(`file-${evidenceId}`);
    const file = fileInput.files[0];

    if (!file) {
        alert("Choose a report file");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    fetch(API + `/api/reports/upload-analysis/${evidenceId}`, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token
        },
        body: formData
    })
    .then(res => res.json())
    .then(() => {
        alert("Report uploaded");
        loadEvidence();
    });
}
