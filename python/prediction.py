"""
Simple Waste Bin Fill Prediction Module
Uses average fill-rate increase to predict future fill levels.
"""

def predict_fill(current_fill, previous_fill):
    """
    Predict the next fill level based on historical data.
    
    Args:
        current_fill (float): Current fill percentage (0-100)
        previous_fill (list): List of previous fill percentages
    
    Returns:
        float: Predicted fill percentage (clamped 0-100)
    """
    if not previous_fill:
        # No history available, assume no change
        return max(0, min(100, current_fill))
    
    # Calculate average increase between consecutive readings
    increases = []
    for i in range(1, len(previous_fill)):
        increase = previous_fill[i] - previous_fill[i - 1]
        increases.append(increase)
    
    if not increases:
        return max(0, min(100, current_fill))
    
    avg_increase = sum(increases) / len(increases)
    
    # Predict next fill level
    predicted = current_fill + avg_increase
    
    # Clamp between 0 and 100
    return max(0, min(100, predicted))


def get_risk_level(predicted_fill):
    """
    Classify risk level based on predicted fill percentage.
    
    Args:
        predicted_fill (float): Predicted fill percentage (0-100)
    
    Returns:
        str: Risk level - "NORMAL", "WARNING", or "HIGH"
    """
    if predicted_fill <= 60:
        return "NORMAL"
    elif predicted_fill <= 80:
        return "WARNING"
    else:
        return "HIGH"


def get_recommendation(risk):
    """
    Get collection recommendation based on risk level.
    
    Args:
        risk (str): Risk level - "NORMAL", "WARNING", or "HIGH"
    
    Returns:
        str: Collection recommendation message
    """
    if risk == "NORMAL":
        return "No immediate collection needed"
    elif risk == "WARNING":
        return "Schedule collection soon"
    elif risk == "HIGH":
        return "Collect this bin soon"
    else:
        return "Unknown risk level"


# Test with sample data
if __name__ == "__main__":
    current_fill = 72
    previous_fill = [40, 52, 61, 68, 72]
    
    predicted = predict_fill(current_fill, previous_fill)
    risk = get_risk_level(predicted)
    recommendation = get_recommendation(risk)
    
    print("=== Waste Bin Fill Prediction Test ===")
    print(f"Current Fill:    {current_fill}%")
    print(f"Previous Fills:  {previous_fill}")
    print(f"Predicted Fill:  {predicted:.1f}%")
    print(f"Risk Level:      {risk}")
    print(f"Recommendation:  {recommendation}")