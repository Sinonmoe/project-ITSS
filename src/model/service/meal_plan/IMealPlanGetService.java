package model.service.meal_plan;

import model.entity.Dish;
import model.entity.Ingredient;
import model.entity.Unit;

import java.util.ArrayList;

public interface IMealPlanGetService {
    /**
     * Retrieves a Dish by its ID, including its ingredients and meal type.
     * @param id The ID of the dish to retrieve.
     * @return A Dish object containing the dish details, or null if not found.
     */
    public Dish getDish(int id);

    /**
     * Retrieves a Dish by its name, including its ingredients and meal type.
     * @param name The name of the dish to retrieve.
     * @return A Dish object containing the dish details, or null if not found.
     */
    public Dish getDish(String name);

    /**
     * Retrieves all dishes from the database.
     * @return An ArrayList of Dish objects containing all dishes.
     */
    public ArrayList<Dish> getAllDishes();

    /**
     * Retrieves the total quantity of a specific ingredient in a fridge.
     * @param ingredientName The name of the ingredient to check.
     * @param unit The unit of measurement for the ingredient.
     * @param fridgeId The ID of the fridge to check.
     * @return The total quantity of the ingredient in the specified fridge.
     */
    public double getTotalQuantityOfIngredient(String ingredientName, Unit unit, int fridgeId);

    /**
     * Checks if a dish can be cooked with the available ingredients in a fridge.
     * @param dishId The ID of the dish to check.
     * @param fridgeId The ID of the fridge to check ingredients against.
     * @return True if all ingredients are available, false otherwise.
     */
    public boolean canCookDish(int dishId, int fridgeId);

    /**
     * Retrieves a list of dishes that can be cooked with the ingredients available in a fridge.
     * @param fridgeId The ID of the fridge to check ingredients against.
     * @return A list of Dish objects that can be cooked.
     */
    public ArrayList<Dish> getCookableDishes(int fridgeId);

    /**
     * Retrieves a list of cookable dishes for a specific day index.
     * @param fridgeId The ID of the fridge to check ingredients against.
     * @param dayIndex The index of the day (0-6) to filter dishes by their eat date.
     * @return A list of Dish objects that can be cooked on the specified day.
     */
    public ArrayList<Dish> getCookableDishesByDayIndex(int fridgeId, int dayIndex);

}
