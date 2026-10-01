
async function loadPriority() {

    try {

        const response = await fetch(
            "http://localhost:8080/api/priority"
        );

        if (!response.ok) {
            throw new Error("Priority API failed");
        }

        const bins = await response.json();

        console.log(
            "Priority result:",
            bins
        );


        // Calculate priority summary counts

        const criticalCount = bins.filter(
            bin => bin.status.toLowerCase() === "critical"
        ).length;

        const warningCount = bins.filter(
            bin => bin.status.toLowerCase() === "warning"
        ).length;

        const normalCount = bins.filter(
            bin => bin.status.toLowerCase() === "normal"
        ).length;


        // Update summary cards

        document.getElementById(
            "criticalPriority"
        ).textContent = criticalCount;

        document.getElementById(
            "warningPriority"
        ).textContent = warningCount;

        document.getElementById(
            "normalPriority"
        ).textContent = normalCount;


        // Get priority table

        const tableBody =
            document.getElementById(
                "priorityTableBody"
            );

        tableBody.innerHTML = "";


        // Display priority queue

        bins.forEach((bin, index) => {

            const row =
                document.createElement("tr");


            let badgeClass = "badge-normal";
            let buttonClass = "btn-success";
            let action = "Normal";


            if (
                bin.status.toLowerCase()
                === "critical"
            ) {

                badgeClass = "badge-critical";
                buttonClass = "btn-danger";
                action = "Collect Now";

            } else if (
                bin.status.toLowerCase()
                === "warning"
            ) {

                badgeClass = "badge-warning";
                buttonClass = "btn-warning";
                action = "Schedule";

            }


            row.innerHTML = `

                <td>
                    <strong>${index + 1}</strong>
                </td>

                <td>
                    ${bin.binId}
                </td>

                <td>
                    ${bin.location}
                </td>

                <td>
                    ${bin.currentFill}%
                </td>

                <td>
                    ${bin.predictedFill}%
                </td>

                <td>
                    <span class="badge ${badgeClass}">
                        ${bin.status}
                    </span>
                </td>

                <td>
                    <button
                        class="btn ${buttonClass} btn-sm">
                        ${action}
                    </button>
                </td>

            `;


            tableBody.appendChild(row);

        });


    } catch (error) {

        console.error(
            "Priority error:",
            error
        );

    }

}


loadPriority();

