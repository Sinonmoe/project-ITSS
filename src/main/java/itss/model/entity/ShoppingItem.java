package itss.model.entity;

public class ShoppingItem {
    private FoodType foodType;
    private boolean isPurchased = false;

    public ShoppingItem(FoodType foodType, boolean isPurchased ) {
        this.foodType = foodType;
        this.isPurchased = isPurchased;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public void setPurchased(boolean purchased) {
        isPurchased = purchased;
    }


}
