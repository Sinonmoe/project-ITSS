// Dish.java
package itss.convenience.entity;

import java.util.List;

public class Dish {
    private final String name;
    private final List<Food> ingredients;
    private final String[] instructions;
    private final int time;
    private final int calories;

    public Dish(String name, List<Food> ingredients, String[] instructions, int time, int calories) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.time = time;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public List<Food> getIngredients() {
        return ingredients;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public int getTime() {
        return time;
    }

    public int getCalories() {
        return calories;
    }

    public boolean canCook(List<Food> foods) {
        for (Food ingredient : ingredients) {
            boolean found = false;
            for (Food food : foods) {
                if (ingredient.equals(food) && ingredient.getTotalQuantity() <= food.getTotalQuantity()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
