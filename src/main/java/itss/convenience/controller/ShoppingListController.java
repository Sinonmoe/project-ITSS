package itss.convenience.controller;

import itss.convenience.entity.Food;
import itss.convenience.entity.Fridge;
import itss.convenience.entity.ShoppingList;

import java.time.LocalDate;

public class ShoppingListController {
    private final ShoppingList shoppingList;
    private final Fridge fridge;

    public ShoppingListController(ShoppingList shoppingList, Fridge fridge) {
        this.shoppingList = shoppingList;
        this.fridge = fridge;
    }

    /**
     * Đánh dấu món ăn trong shoppingList là đã mua.
     * Nếu thành công, thêm món đó vào fridge.
     * 
     * @param foodToMark món cần đánh dấu
     * @return true nếu đánh dấu thành công, false nếu không tìm thấy
     */
    public boolean markFoodAsPurchased(Food foodToMark) {
        boolean marked = shoppingList.markFoodAsPurchased(foodToMark);
        if (marked) {
            fridge.getFoodList().add(foodToMark);
            return true;
        }
        return false;
    }

    /**
     * Hiển thị danh sách mua sắm
     */
    public void displayShoppingList() {
        System.out.println("🛒 Danh sách mua sắm #" + shoppingList.getId() + " - Ngày: " + shoppingList.getDate());
        for (Food food : shoppingList.getFoods()) {
            String status = food.isPurchased() ? "✅ Đã mua" : "❌ Chưa mua";
            System.out.println(
                    "- " + food.getName() + " (" + food.getQuantity() + " " + food.getUnitType() + ") " + status);
        }
    }

    /**
     * Hiển thị thực phẩm trong tủ lạnh
     */
    public void displayFridge() {
        System.out.println("❄️ Tủ lạnh #" + fridge.getId() + ":");
        for (Food food : fridge.getFoodList()) {
            System.out.println("- " + food.getName() + " (" + food.getQuantity() + " " + food.getUnitType() + ")");
        }
    }

    public static void main(String[] args) {
        // Tạo một số món ăn
        Food rice = new Food("Gạo", itss.convenience.UnitType.kg, 2, LocalDate.of(2025, 12, 31));
        Food milk = new Food("Sữa", itss.convenience.UnitType.l, 1, LocalDate.of(2024, 5, 30));
        Food apple = new Food("Táo", itss.convenience.UnitType.kg, 5, LocalDate.of(2024, 6, 15));

        ShoppingList shoppingList = new ShoppingList(1, LocalDate.now());
        // Thêm món ăn vào danh sách
        shoppingList.getFoods().add(rice);
        shoppingList.getFoods().add(milk);
        shoppingList.getFoods().add(apple);

        // Tạo tủ lạnh rỗng
        Fridge fridge = new Fridge(2);

        // Tạo controller
        ShoppingListController controller = new ShoppingListController(shoppingList, fridge);

        // Hiển thị danh sách ban đầu
        controller.displayShoppingList();
        System.out.println();

        // Đánh dấu món "Sữa" là đã mua
        controller.markFoodAsPurchased(milk);

        // Đánh dấu món "Táo" là đã mua
        controller.markFoodAsPurchased(apple);

        // Hiển thị lại danh sách mua sắm và tủ lạnh
        System.out.println("\nSau khi đánh dấu mua:");
        controller.displayShoppingList();
        System.out.println();
        controller.displayFridge();
    }
}
