package itss.convenience.entity;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ShoppingList {
    private final List<Food> foods;
    private final int id;
    private final LocalDate date;

    public ShoppingList(int id, LocalDate date) {
        this.id = id;
        this.date = date;
        this.foods = new ArrayList<>();
    }

    public List<Food> getFoods() {
        return foods;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Đánh dấu món ăn là đã mua.
     * 
     * @param foodToMark món cần đánh dấu
     * @return true nếu tìm thấy và đánh dấu thành công
     */
    public boolean markFoodAsPurchased(Food foodToMark) {
        for (Food food : foods) {
            if (food.equals(foodToMark)) {
                food.setPurchased(true);
                return true;
            }
        }
        return false;
    }
}
