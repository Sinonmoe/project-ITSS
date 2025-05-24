package itss.convenience.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingList {
    private final List<Food> foods;
    private final int id;
    private final Date date;

    public ShoppingList(int id, Date date) {
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

    public Date getDate() {
        return date;
    }
}
