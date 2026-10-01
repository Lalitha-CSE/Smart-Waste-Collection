async function findRoute() {

const start = document.getElementById("startPoint").value;
const destination = document.getElementById("destination").value;
const truck = document.getElementById("truckSelect").value;

try {

    const response = await fetch(
        `http://localhost:8080/api/route?start=${encodeURIComponent(start)}&destination=${encodeURIComponent(destination)}`
    );

    if (!response.ok) {
        throw new Error("Route API failed");
    }

    const route = await response.json();

    console.log("Route result:", route);

    // Update route information

    document.getElementById("routeTruck").textContent = truck;

    document.getElementById("routeStart").textContent = start;

    document.getElementById("routeDestination").textContent = destination;

    document.getElementById("routePath").textContent =
        route.join(" → ");

    document.getElementById("routeStops").textContent =
        route.length;

    document.getElementById("routeStatus").textContent =
        "Route Calculated";


    // Update visualization

    const visualization =
        document.getElementById("routeVisualization");

    visualization.innerHTML = "";


    route.forEach((location, index) => {

        const locationBox =
            document.createElement("div");

        locationBox.style.textAlign = "center";


        let icon = "📍";
        let label = "Location";


        if (index === 0) {

            icon = "🚛";
            label = "Start";

        } else if (index === route.length - 1) {

            icon = "🏁";
            label = "Destination";

        } else {

            icon = "📦";
            label = `Stop ${index}`;

        }


        locationBox.innerHTML = `

            <div
                style="
                    width: 70px;
                    height: 70px;
                    border-radius: 50%;
                    background: var(--primary-bg);
                    color: var(--primary);
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    font-size: 1.5rem;
                    margin: 0 auto;
                ">

                ${icon}

            </div>


            <div
                style="
                    margin-top: 0.5rem;
                    font-weight: 600;
                    font-size: 0.9rem;
                ">

                ${label}

            </div>


            <div
                style="
                    font-size: 0.75rem;
                    color: var(--text-secondary);
                ">

                ${location}

            </div>

        `;


        visualization.appendChild(locationBox);


        // Arrow between locations

        if (index < route.length - 1) {

            const arrow =
                document.createElement("div");

            arrow.textContent = "→";

            arrow.style.color = "var(--primary)";

            arrow.style.fontSize = "1.5rem";

            visualization.appendChild(arrow);

        }

    });

}

catch (error) {

    console.error("Route error:", error);

    document.getElementById("routeStatus").textContent =
        "Route Failed";

    alert(
        "Unable to calculate route. Please check whether the Java API server is running."
    );

}


}
