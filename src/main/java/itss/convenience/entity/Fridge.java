// Fridge.java
package itss.convenience.entity;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class Fridge {
    private final List<Food> foodList = new ArrayList<>();

    public Fridge() {
        // Constructor rỗng
    }
    public Fridge(@NotNull Food... foods) {
        Map<String, Food> mergedFoods = new HashMap<>();

        for (Food food : foods) {
            if (food == null) continue;

            String key = food.getName() + "_" + food.getUnitType();
            Food merged = mergedFoods.get(key);

            if (merged == null) {
                // Tạo một bản sao thực phẩm mới và sao chép toàn bộ expirationMap
                Food copy = new Food(food.getName(), food.getUnitType());
                for (Map.Entry<LocalDate, Double> entry : food.getExpirationMap().entrySet()) {
                    copy.addQuantity(entry.getKey(), entry.getValue());
                }
                mergedFoods.put(key, copy);
            } else {
                // Nếu đã tồn tại thực phẩm cùng loại, cộng dồn theo từng ngày
                for (Map.Entry<LocalDate, Double> entry : food.getExpirationMap().entrySet()) {
                    merged.addQuantity(entry.getKey(), entry.getValue());
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
