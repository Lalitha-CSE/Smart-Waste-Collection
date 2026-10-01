async function predictFill() {
    const binId = document.getElementById("binId").value;
    const currentFill = Number(document.getElementById("currentFill").value);
    const previousFillValue = Number(document.getElementById("previousFill").value);
    const days = Number(document.getElementById("daysPredict").value);

    const previousFill = [
        previousFillValue,
        currentFill
    ];

    try {
        const response = await fetch("https://smart-waste-collection-tv8v.onrender.com/predict", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                bin_id: binId,
                current_fill: currentFill,
                previous_fill: previousFill
            })
        });

        if (!response.ok) {
            throw new Error("Prediction API failed");
        }

        const result = await response.json();

        console.log("Prediction result:", result);

        document.getElementById("currentFillResult").textContent =
            currentFill + "%";

        document.getElementById("predictedFill").textContent =
            result.predicted_fill + "%";

        document.getElementById("riskLevel").textContent =
            result.risk;

        document.getElementById("recommendation").textContent =
            result.recommendation;

    } catch (error) {
        console.error("Prediction error:", error);
    }
}