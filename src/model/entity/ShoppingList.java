package model.entity;

import java.time.LocalDate;
import java.util.ArrayList;

public class ShoppingList {
    private int id;
    private int userGroupId;
    private LocalDate buyDate;
    private ArrayList<ShoppingItem> shoppingItemList;

    public ShoppingList(int id, int userGroupId, LocalDate buyDate, ArrayList<ShoppingItem> shoppingItemList) {
        this.id = id;
        this.userGroupId = userGroupId;
        this.buyDate = buyDate;
        this.shoppingItemList = shoppingItemList;
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

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public ArrayList<ShoppingItem> getShoppingItemList() {
        return shoppingItemList;
    }

    public void setShoppingItemList(ArrayList<ShoppingItem> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }
}
    
