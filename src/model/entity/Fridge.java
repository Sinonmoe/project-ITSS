package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fridge {
    private int id;
    private int userGroupId;
    private ArrayList<Ingredient> ingredients;

    public Fridge(int id, int userGroupId, ArrayList<Ingredient> ingredients) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    // Chức năng 1: Nhập thực phẩm mới vào tủ lạnh
    public void addIngredient(Ingredient newIngredient) {
        for (Ingredient ing : ingredients) {
            if (ing.getName().equalsIgnoreCase(newIngredient.getName()) && ing.getUnit() == newIngredient.getUnit()) {
                // Nếu trùng loại thực phẩm, cộng dồn số lượng
                ing.setQuantity(ing.getQuantity() + newIngredient.getQuantity());
                return;
            }
        }
        ingredients.add(newIngredient);
    }

    // Chức năng 2: Nhắc nhở thực phẩm sắp hết hạn (trong n ngày)
    public List<Ingredient> getExpiringIngredients(int daysBeforeExpire) {
        LocalDate now = LocalDate.now();
        List<Ingredient> warningList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (!ingredient.getExpirationDate().isBefore(now) &&
                    !ingredient.getExpirationDate().isAfter(now.plusDays(daysBeforeExpire))) {
                warningList.add(ingredient);
            }
        }
        return warningList;
    }

    // Chức năng 3+4: Sử dụng thực phẩm và tự động trừ trong kho
    public boolean useIngredient(String name, double quantity, Unit unit) {
        for (Ingredient ing : ingredients) {
            if (ing.getName().equalsIgnoreCase(name) && ing.getUnit() == unit) {
                if (ing.getQuantity() >= quantity) {
                    ing.setQuantity(ing.getQuantity() - quantity);
                    return true;
                }
                return false; // Không đủ nguyên liệu
            }
        }
        return false; // Không tìm thấy nguyên liệu
    }
}
