package itss.model.entity;

import java.util.ArrayList;

public class Fridge {
    private int id;
    private int userGroupId;
    private ArrayList<Food> foodList;

    public Fridge(int id, int userGroupId, ArrayList<Food> foodList) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.foodList = foodList;
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

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(ArrayList<Food> foodList) {
        this.foodList = foodList;
    }
}
