package itss.convenience.controller;

import itss.convenience.UnitType;
import itss.convenience.entity.Food;
import itss.convenience.entity.Fridge;

import java.time.LocalDate;
import java.util.List;

public class FridgeController {
    private Fridge fridge = new Fridge(1, new Food("mồng tơi", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("thịt gà", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("thịt bò", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("thịt heo", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("trứng", UnitType.PCS, 1, LocalDate.of(2023, 10, 1)),
            new Food("sữa", UnitType.L, 1, LocalDate.of(2023, 10, 1)),
            new Food("bánh mì", UnitType.PCS, 10, LocalDate.of(2023, 10, 1)),
            new Food("cà chua", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("dưa hấu", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("dưa leo", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("rau muống", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("cà rốt", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("tía tô", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("hành khô", UnitType.KG, 1, LocalDate.of(2023, 10, 1)));

    public List<Food> getEdibleFoods() {
        return fridge.getEdibleFoods();
    }

    public static void main(String[] args) {
        FridgeController controller = new FridgeController();
        List<Food> edibleFoods = controller.getEdibleFoods();

        System.out.println("Các thực phẩm trong tủ lạnh:");
        for (Food food : edibleFoods) {
            System.out.println("- " + food.getName() + ": " + food.getQuantity() + " " + food.getUnitType());
        }
    }
}
