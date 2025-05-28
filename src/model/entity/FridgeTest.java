package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FridgeTest {
    public static void main(String[] args) {
        Fridge fridge = new Fridge(1, 1, new ArrayList<>());

        Ingredient egg = new Ingredient(1, "Trứng", 6, Unit.PCS, LocalDate.of(2024, 6, 1));
        Ingredient milk = new Ingredient(2, "Sữa", 2, Unit.L, LocalDate.of(2024, 5, 30));

        // Thêm thực phẩm vào tủ
        fridge.addIngredient(egg);
        fridge.addIngredient(milk);

        // Nhắc nhở thực phẩm sắp hết hạn
        List<Ingredient> warningList = fridge.getExpiringIngredients(3);
        System.out.println("⚠️ Thực phẩm sắp hết hạn:");
        for (Ingredient ing : warningList) {
            System.out.println(ing);
        }

        // Sử dụng thực phẩm
        boolean used = fridge.useIngredient("Trứng", 2, Unit.PCS);
        System.out.println("Đã sử dụng trứng? " + used);
    }
}
