// Fridge.java
package itss.convenience.entity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class Fridge {
    private final List<Food> foodList = new ArrayList<>();

    public Fridge(@NotNull Food... foods) {
        Map<Food, Food> mergedFoods = new HashMap<>();

        for (Food food : foods) {
            if (food == null) continue;

            Food key = new Food(food.getName(), food.getUnitType(), 0); // để dùng làm khóa
            Food existing = mergedFoods.get(key);

            if (existing == null) {
                // Tạo một bản sao để tránh thay đổi food gốc bên ngoài
                Food copy = new Food(food.getName(), food.getUnitType(), 0);
                for (Map.Entry<LocalDate, Double> entry : food.getExpirationMap().entrySet()) {
                    copy.addQuantity(entry.getKey(), entry.getValue());
                }
                mergedFoods.put(key, copy);
            } else {
                for (Map.Entry<LocalDate, Double> entry : food.getExpirationMap().entrySet()) {
                    existing.addQuantity(entry.getKey(), entry.getValue());
                }
            }
        }

        foodList.addAll(mergedFoods.values());
    }


    public List<Food> getEdibleFoods() {
        List<Food> result = new ArrayList<>();
        for (Food food : foodList) {
            if (!food.isExpired()) {
                result.add(food);
            }
        }
        return result;
    }

    private void updateFoods(@NotNull List<Food> foods, boolean plus) {
        for (Food food : foods) {
            Optional<Food> existing = foodList.stream()
                    .filter(f -> f.equals(food))
                    .findFirst();
            if (existing.isPresent()) {
                Food existingFood = existing.get();
                if (plus) {
                    for (Map.Entry<LocalDate, Double> entry : food.getExpirationMap().entrySet()) {
                        existingFood.addQuantity(entry.getKey(), entry.getValue());
                    }
                } else {
                    existingFood.subtractQuantity(food.getTotalQuantity());
                }
            } else if (plus) {
                foodList.add(food);
            }
        }
    }
    public void addFood(@NotNull List<Food> foods) {
        updateFoods(foods, true);
    }
    public void removeFood(@NotNull List<Food> foods) {
        updateFoods(foods, false);
    }
}
