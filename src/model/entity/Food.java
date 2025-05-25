package model.entity;

import java.time.LocalDate;

public class Food {
    private int id;
    int foodTypeId;
    private LocalDate expirationDate;
    private int fridgeId;

    public Food(int id, int foodTypeId, LocalDate expirationDate, int fridgeId) {
        this.id = id;
        this.foodTypeId = foodTypeId;
        this.expirationDate = expirationDate;
        this.fridgeId = fridgeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(int fridgeId) {
        this.fridgeId = fridgeId;
    }
}
