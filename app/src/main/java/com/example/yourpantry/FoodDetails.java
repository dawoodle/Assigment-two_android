package com.example.yourpantry;

public class FoodDetails {

    private String FoodName;
    private String FoodType;
    private String FoodExpiry;

    public FoodDetails(String foodName, String foodType, String foodExpiry) {
        FoodName = foodName;
        FoodType = foodType;
        FoodExpiry = foodExpiry;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodType() {
        return FoodType;
    }

    public void setFoodType(String foodType) {
        FoodType = foodType;
    }

    public String getFoodExpiry() {
        return FoodExpiry;
    }

    public void setFoodExpiry(String foodExpiry) {
        FoodExpiry = foodExpiry;
    }
}
