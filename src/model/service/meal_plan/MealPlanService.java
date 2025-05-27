package model.service.meal_plan;

import model.entity.Dish;
import model.service.BaseService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MealPlanService extends BaseService {
    public ArrayList<Dish> getAllDishes(){
        ArrayList<Dish> dishes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dish";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            AtomicReference<ResultSet> resultSet = new AtomicReference<>(preparedStatement.executeQuery());
            while (resultSet.get().next()) {
                int id = resultSet.get().getInt("id");
                String name = resultSet.get().getString("name");
                String description = resultSet.get().getString("description");

                Dish dish = new Dish();
                dish.setId(id);
                dish.setName(name);
                dish.setDescription(description);
                dishes.add(dish);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return  dishes;
    }
}
