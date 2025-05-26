package itss.model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class Meal {
    private int id;
    private int userGroupId;
    private LocalDate date;
    private ArrayList<Dish> dishList;
    private MealType mealType;

    public Meal(int id, int userGroupId, LocalDate date, ArrayList<Dish> dishList, MealType mealType) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.date = date;
        this.dishList = dishList;
        this.mealType = mealType;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<Dish> dishList) {
        this.dishList = dishList;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }
}
