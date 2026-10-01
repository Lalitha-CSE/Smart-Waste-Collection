async function loadDashboard() {
    try {
        const [binsResponse, trucksResponse] = await Promise.all([
            fetch("http://localhost:8080/api/bins"),
            fetch("http://localhost:8080/api/trucks")
        ]);

        if (!binsResponse.ok || !trucksResponse.ok) {
            throw new Error("Failed to load dashboard data");
        }

        const bins = await binsResponse.json();
        const trucks = await trucksResponse.json();

        const totalBins = bins.length;

        const criticalBins = bins.filter(
            bin => bin.status.toLowerCase() === "critical"
        ).length;

        const warningBins = bins.filter(
            bin => bin.status.toLowerCase() === "warning"
        ).length;

        const normalBins = bins.filter(
            bin => bin.status.toLowerCase() === "normal"
        ).length;

        const availableTrucks = trucks.filter(
            truck => truck.status.toLowerCase() === "available"
        ).length;

        const onRouteTrucks = trucks.filter(
            truck => truck.status.toLowerCase() === "on route"
        ).length;

        document.getElementById("totalBins").textContent = totalBins;
        document.getElementById("criticalBins").textContent = criticalBins;
        document.getElementById("warningBins").textContent = warningBins;
        document.getElementById("normalBins").textContent = normalBins;
        document.getElementById("availableTrucks").textContent = availableTrucks;
        document.getElementById("onRouteTrucks").textContent = onRouteTrucks;

        console.log("Dashboard data loaded successfully");

    } catch (error) {
        console.error("Error loading dashboard:", error);
    }
}

loadDashboard();