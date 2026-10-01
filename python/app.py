"""
FastAPI REST API for Smart Waste Collection Prediction
Exposes the prediction module as a web service for the frontend.
"""

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

from prediction import predict_fill, get_risk_level, get_recommendation


# Create FastAPI application
app = FastAPI(title="Smart Waste Prediction API")


# Enable CORS so the frontend can access this API
app.add_middleware(
    CORSMiddleware,
    allow_origins=[
        "http://127.0.0.1:5500",
        "http://localhost:5500"
    ],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class PredictionRequest(BaseModel):
    """Request model for fill level prediction."""

    bin_id: str
    current_fill: float
    previous_fill: list[float]


@app.get("/")
def health_check():
    """Health check endpoint."""

    return {
        "message": "Smart Waste Prediction API is running"
    }


@app.post("/predict")
def predict(request: PredictionRequest):
    """
    Predict future fill level for a waste bin.

    Receives bin data from the frontend,
    calculates predicted fill, risk level,
    and recommendation.
    """

    # Calculate prediction
    predicted_fill = predict_fill(
        request.current_fill,
        request.previous_fill
    )

    # Calculate risk level
    risk = get_risk_level(predicted_fill)

    # Generate recommendation
    recommendation = get_recommendation(risk)

    # Return JSON response
    return {
        "bin_id": request.bin_id,
        "current_fill": request.current_fill,
        "predicted_fill": round(predicted_fill, 1),
        "risk": risk,
        "recommendation": recommendation
    }