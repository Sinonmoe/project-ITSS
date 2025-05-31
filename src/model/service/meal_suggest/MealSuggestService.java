package model.service.meal_suggest;

import model.entity.Dish;
import model.entity.Ingredient;
import model.service.BaseService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class MealSuggestService extends BaseService {

    /**
     * Đề xuất danh sách món ăn có thể nấu dựa trên nguyên liệu đang có trong tủ lạnh.
     * @param fridgeId ID của tủ lạnh
     * @return Danh sách món ăn có thể nấu được
     */
    public List<Dish> suggestMeals(int fridgeId) {
        List<Ingredient> availableIngredients = getIngredientsInFridge(fridgeId);
        Map<Integer, Dish> allDishes = getAllDishesWithIngredients();

        List<Dish> canCook = new ArrayList<>();

        for (Dish dish : allDishes.values()) {
            if (canCookDish(dish, availableIngredients)) {
                canCook.add(dish);
            }
        }

        return canCook;
    }

    /**
     * Lấy danh sách nguyên liệu hiện có trong tủ lạnh.
     */
    private List<Ingredient> getIngredientsInFridge(int fridgeId) {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT ingredientName, quantity, unitType FROM ingredient WHERE fridgeId = ?";

        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fridgeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ingredient ing = new Ingredient(
                        rs.getString("ingredientName"),
                        rs.getDouble("quantity"),
                        rs.getString("unitType")
                );
                ingredients.add(ing);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    /**
     * Lấy tất cả món ăn cùng nguyên liệu đi kèm từ CSDL.
     */
    private Map<Integer, Dish> getAllDishesWithIngredients() {
        Map<Integer, Dish> dishMap = new HashMap<>();
        String sql = "SELECT d.dishId, d.dishName, dui.ingredientName, dui.quantity, dui.unitType " +
                     "FROM dish d JOIN dish_use_ingredient dui ON d.dishId = dui.dishId";

        try (Connection conn = openConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int dishId = rs.getInt("dishId");
                String dishName = rs.getString("dishName");

                Ingredient ing = new Ingredient(
                        rs.getString("ingredientName"),
                        rs.getDouble("quantity"),
                        rs.getString("unitType")
                );

                dishMap.putIfAbsent(dishId, new Dish(dishId, dishName, new ArrayList<>()));
                dishMap.get(dishId).getIngredients().add(ing);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dishMap;
    }

    /**
     * Kiểm tra xem có thể nấu món ăn này từ nguyên liệu trong tủ lạnh không.
     */
    private boolean canCookDish(Dish dish, List<Ingredient> fridgeIngredients) {
        for (Ingredient required : dish.getIngredients()) {
            boolean found = fridgeIngredients.stream()
                .anyMatch(available -> available.getName().equalsIgnoreCase(required.getName())
                        && available.getQuantity() >= required.getQuantity());
            if (!found) return false;
        }
        return true;
    }
}
