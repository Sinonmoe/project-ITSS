package itss.model.entity;

import java.util.ArrayList;

public class Dish {
    private int id;
    private String name;
    private String description;
    private ArrayList<FoodType> foodTypeList;

    public Dish(int id, String name, String description, ArrayList<FoodType> foodTypeList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.foodTypeList = foodTypeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<FoodType> getFoodTypeList() {
        return foodTypeList;
    }

    public void setFoodTypeList(ArrayList<FoodType> foodTypeList) {
        this.foodTypeList = foodTypeList;
    }
}
