async function loadBins() {
    try {
        const response = await fetch("https://smart-waste-collection-1-awua.onrender.com/api/bins");
        if (!response.ok) {
            throw new Error("Failed to load bins");
        }

        const bins = await response.json();

        console.log("Bins loaded from Java API:", bins);

        const tableBody = document.getElementById("binTableBody");

        tableBody.innerHTML = "";

        bins.forEach(bin => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${bin.binId}</td>
                <td>${bin.location}</td>
                <td>${bin.currentFill}%</td>
                <td>${bin.predictedFill}%</td>
                <td>${bin.status}</td>
                <td>${bin.lastCollected}</td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error("Error loading bins:", error);
    }
}

loadBins();