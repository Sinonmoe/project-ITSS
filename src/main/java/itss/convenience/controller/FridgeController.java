package itss.convenience.controller;

import itss.convenience.UnitType;
import itss.convenience.entity.Food;
import itss.convenience.entity.Fridge;

import java.time.LocalDate;

public class FridgeController {
    private Fridge fridge = new Fridge(new Food("mồng tơi", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("thịt gà", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("thịt bò", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("thịt heo", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("trứng", UnitType.PCS, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("sữa", UnitType.L, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("bánh mì", UnitType.PCS, 10, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("cà chua", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("dưa hấu", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("dưa leo", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("rau muống", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("cà rốt", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("tía tô", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay()),
            new Food("hành khô", UnitType.KG, 1, LocalDate.of(2023, 10, 1).atStartOfDay())
    );
}
