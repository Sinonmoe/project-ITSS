package itss.convenience.controller;

import itss.convenience.UnitType;
import itss.convenience.entity.Dish;
import itss.convenience.entity.Food;
import itss.convenience.entity.Fridge;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class FridgeController {
    private final Fridge fridge = new Fridge(new Food("mồng tơi", UnitType.KG, 1, LocalDate.of(2023, 10, 1)),
            new Food("thịt gà", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("thịt bò", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("thịt heo", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("trứng", UnitType.PCS, 1, LocalDate.of(2025, 10, 1)),
            new Food("sữa", UnitType.L, 1, LocalDate.of(2025, 10, 1)),
            new Food("bánh mì", UnitType.PCS, 10, LocalDate.of(2025, 10, 1)),
            new Food("cà chua", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("dưa hấu", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("dưa leo", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("rau muống", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("cà rốt", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("tía tô", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("hành khô", UnitType.KG, 1, LocalDate.of(2025, 10, 1)),
            new Food("mồng tơi", UnitType.KG, 1, LocalDate.of(2025, 10, 1))
    );

    private Dish canhLaoNhao = new Dish("Canh Láo Nháo", Arrays.asList(
            new Food("mồng tơi", UnitType.KG, 0.1),
            new Food("thịt gà", UnitType.KG, 0.1),
            new Food("tía tô", UnitType.KG, 1, LocalDate.of(2025, 10, 1))
    ), new String[]{
            "Bước 1: Rửa sạch mồng tơi và thịt gà.",
            "Bước 2: Nấu nước sôi và cho thịt gà vào nấu chín.",
            "Bước 3: Thêm mồng tơi vào nấu cùng."
    }, 30, 200);

    private Dish thitNuong = new Dish("Thịt Nướng", Arrays.asList(
            new Food("thịt bò", UnitType.KG, 0.2),
            new Food("thịt heo", UnitType.KG, 0.2),
            new Food("hành khô", UnitType.KG, 0.1)
    ), new String[]{
            "Bước 1: Ướp thịt với gia vị.",
            "Bước 2: Nướng thịt trên lửa than.",},
            45, 500);
    private Dish canhXuong = new Dish("Canh Xương", Arrays.asList(
            new Food("xương heo", UnitType.KG, 0.5),
            new Food("cà rốt", UnitType.KG, 0.2),
            new Food("dưa leo", UnitType.KG, 0.2)
    ), new String[]{
            "Bước 1: Ninh xương với nước.",
            "Bước 2: Thêm cà rốt và dưa leo vào nấu cùng."
    }, 60, 300);

    private ArrayList<Dish> dishes = new ArrayList<>(Arrays.asList(
            canhLaoNhao,
            thitNuong,
            canhXuong
    ));

    public static void main(String[] args) {
        FridgeController fridgeController = new FridgeController();
        System.out.println("Dishes that can be cooked:");
        for (Dish dish : fridgeController.hintDishes()) {
            System.out.println(dish.getName());
        }
    }

    public ArrayList<Dish> hintDishes() {
        ArrayList<Dish> hintDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.canCook(fridge.getEdibleFoods())) {
                hintDishes.add(dish);
            }
        }
        return hintDishes;
    }

    public void addFoods(Food... foods) {
        fridge.addFood(Arrays.stream(foods).toList());
    }
    public void removeFoods(Food... foods) {
        fridge.removeFood(Arrays.stream(foods).toList());
    }
}
