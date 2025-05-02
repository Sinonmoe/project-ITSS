package itss.convenience.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Fridge {
    private final ArrayList<Food> foodList;
    public Fridge(Food ...foods) {
        foodList = new ArrayList<>();
        foodList.addAll(Arrays.asList(foods));
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
