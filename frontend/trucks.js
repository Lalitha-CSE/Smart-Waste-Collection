async function loadTrucks() {
    try {
        const response = await fetch("http://localhost:8080/api/trucks");

        if (!response.ok) {
            throw new Error("Failed to load trucks");
        }

        const trucks = await response.json();

        console.log("Trucks loaded from Java API:", trucks);

        const tableBody = document.getElementById("truckTableBody");

        tableBody.innerHTML = "";

        trucks.forEach(truck => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${truck.truckId}</td>
                <td>${truck.driver}</td>
                <td>${truck.capacity} kg</td>
                <td>${truck.status}</td>
                <td>${truck.currentRoute || "-"}</td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error("Error loading trucks:", error);
    }
}

loadTrucks();