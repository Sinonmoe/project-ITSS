package itss.convenience.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Fridge {
    private final int id;

    private final ArrayList<Food> foodList;

    public Fridge(int id, Food... foods) {
        this.id = id;
        this.foodList = new ArrayList<>();
        this.foodList.addAll(Arrays.asList(foods));
    }

    public int getId() {
        return id;
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public ArrayList<Food> getEdibleFoods() {
        ArrayList<Food> edibleFoods = new ArrayList<>();
        for (Food food : foodList) {
            if (edibleFoods.isEmpty()) {
                edibleFoods.add(food);
            } else {
                boolean isFoodExist = false;
                for (Food edibleFood : edibleFoods) {
                    if (food.equals(edibleFood)) {
                        isFoodExist = true;
                        double newQuantity = food.getQuantity() + edibleFood.getQuantity();
                        edibleFood.setQuantity(newQuantity);
                        break;
                    }
                }
                if (!isFoodExist) {
                    edibleFoods.add(food);
                }
            }
        }
        return edibleFoods;
    }
}
