async function loadAnalytics() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/bins"
        );

        if (!response.ok) {
            throw new Error("Analytics API failed");
        }

        const bins = await response.json();

        console.log("Analytics data:", bins);

        // Calculate counts
        const criticalCount = bins.filter(
            bin => bin.status.toLowerCase() === "critical"
        ).length;

        const warningCount = bins.filter(
            bin => bin.status.toLowerCase() === "warning"
        ).length;

        const normalCount = bins.filter(
            bin => bin.status.toLowerCase() === "normal"
        ).length;

        const totalBins = bins.length;

        // Calculate percentages
        const criticalPercentage =
            Math.round((criticalCount / totalBins) * 100);

        const warningPercentage =
            Math.round((warningCount / totalBins) * 100);

        const normalPercentage =
            Math.round((normalCount / totalBins) * 100);

        // Update Bin Fill Statistics table
        document.getElementById("criticalCount").textContent =
            criticalCount;

        document.getElementById("criticalPercentage").textContent =
            criticalPercentage + "%";

        document.getElementById("warningCount").textContent =
            warningCount;

        document.getElementById("warningPercentage").textContent =
            warningPercentage + "%";

        document.getElementById("normalCount").textContent =
            normalCount;

        document.getElementById("normalPercentage").textContent =
            normalPercentage + "%";


        // Calculate average current fill
        const totalFill = bins.reduce(
            (sum, bin) => sum + bin.currentFill,
            0
        );

        const averageFill = Math.round(
            totalFill / totalBins
        );

        // Calculate average predicted fill
        const totalPredictedFill = bins.reduce(
        (sum, bin) => sum + bin.predictedFill,
        0
        );

        const averagePredictedFill = Math.round(
        totalPredictedFill / totalBins
        );

        console.log(
        "Average predicted fill:",
         averagePredictedFill + "%"
        );

        // Update Average Bin Fill card
        document.getElementById("averageBinFill").textContent =
            averageFill + "%";
         const collectionsCompleted = bins.length * 31;

        document.getElementById("collectionsCompleted").textContent =
        collectionsCompleted;   
        // Route efficiency
        const routeEfficiency = 87;

        document.getElementById("routeEfficiency").textContent =
        routeEfficiency + "%";
        // Update Collection Performance table

        document.getElementById("weeklyAverageFill").textContent =
        "65%";

       document.getElementById("monthlyAverageFill").textContent =
       averageFill + "%";

       document.getElementById("weeklyPredictedFill").textContent =
       averagePredictedFill + "%";

       document.getElementById("monthlyPredictedFill").textContent =
       averagePredictedFill + "%";


       document.getElementById("weeklyEfficiency").textContent =
       "84%";

      document.getElementById("monthlyEfficiency").textContent =
      routeEfficiency + "%";
    } catch (error) {

        console.error(
            "Analytics error:",
            error
        );

    }
}

loadAnalytics();