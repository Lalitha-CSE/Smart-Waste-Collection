\# Smart Waste Collection Router



An AI-based smart waste collection system that helps prioritize waste bins and generate efficient collection routes.



\## Features



\- Bin Management

\- Truck Management

\- AI-based Fill Prediction

\- Collection Priority using Max Heap

\- Route Planning using A\* Algorithm

\- Analytics Dashboard

\- MySQL Database Integration

\- Java Backend

\- Python FastAPI Prediction Service



\## Technologies Used



\- HTML

\- CSS

\- JavaScript

\- Java

\- Python

\- FastAPI

\- MySQL

\- JDBC

\- A\* Algorithm

\- Priority Queue / Max Heap



\## Project Architecture



Frontend → Java Backend → MySQL



Frontend → Python FastAPI → Fill Prediction



Java Backend → A\* Route Planning



Java Backend → Priority Queue → Collection Priority



\## Project Modules



\### 1. Dashboard

Displays total bins, critical bins, warning bins, normal bins and available trucks.



\### 2. Bin Management

Displays bin locations, current fill level, predicted fill level and status.



\### 3. Truck Management

Displays truck details, driver, capacity and current status.



\### 4. AI Fill Prediction

Uses Python to predict the future fill level of a waste bin and determine its risk level.



\### 5. Collection Priority

Uses a Max Heap to prioritize bins based on predicted fill level.



\### 6. Route Planning

Uses graph-based route planning and the A\* algorithm to calculate a route between locations.



\### 7. Analytics

Displays waste collection statistics, average fill levels, predicted fill levels and route efficiency.



\## How to Run



\### Java Backend



Run the Java API server from the backend project.



The Java backend runs on:



http://localhost:8080



\### Python Prediction API



Open the Python folder and activate the virtual environment:



```powershell

.\\venv\\Scripts\\Activate.ps1
Start FastAPI:

```powershell
uvicorn app:app --reload

Python API runs on:

http://127.0.0.1:8000

### Frontend

Open the frontend using a local server such as VS Code Live Server.

## Database

The project uses MySQL database `smart_waste`.

Database setup SQL is available in:

`database/smart_waste.sql`

## Project Type

B.Tech AIML Mini Project

## Author

Lalitha-CSE

