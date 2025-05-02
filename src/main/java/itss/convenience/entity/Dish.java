package itss.convenience.entity;

import java.util.List;

public class Dish {
    private String name;
    private List<Food> ingredients;
    private String[] instructions;
    private int time;
    private int calories;

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
                if (ingredient.equals(food) && ingredient.getQuantity() <= food.getQuantity()) {
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
