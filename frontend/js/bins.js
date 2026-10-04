
async function loadBins() {
    try {
        const response = await fetch(
            "https://smart-waste-collection-1-awua.onrender.com/api/bins"
        );

        if (!response.ok) {
            throw new Error("Failed to load bins");
        }

        const bins = await response.json();

        console.log("Bins loaded from Java API:", bins);

        const tableBody = document.getElementById("binTableBody");

        tableBody.innerHTML = "";

        bins.forEach(bin => {
            const row = document.createElement("tr");

            const action =
                bin.status === "Critical"
                    ? '<button class="btn btn-danger btn-sm">Collect</button>'
                    : bin.status === "Warning"
                    ? '<button class="btn btn-warning btn-sm">Schedule</button>'
                    : '<button class="btn btn-success btn-sm">Normal</button>';

            row.innerHTML = `
                <td>${bin.binId}</td>
                <td>${bin.location}</td>
                <td>${bin.currentFill}%</td>
                <td>${bin.predictedFill}%</td>
                <td>
                    <span class="${
                        bin.status === "Critical"
                            ? "badge badge-critical"
                            : bin.status === "Warning"
                            ? "badge badge-warning"
                            : "badge badge-normal"
                    }">
                        ${bin.status}
                    </span>
                </td>
                <td>${bin.lastCollected}</td>
                <td>${action}</td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error("Error loading bins:", error);
    }
}

loadBins();
console.log("BINS JS VERSION: ACTION FIX ACTIVE");
