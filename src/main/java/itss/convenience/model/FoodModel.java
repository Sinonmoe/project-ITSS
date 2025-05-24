/**
 * Lớp này sinh ra để thao tác với bảng food trong csdl, cũng như các bảng có liên quan
 * @author Minh Hoàng
 */
package itss.convenience.model;

import itss.convenience.UnitType;
import itss.convenience.entity.Food;
import java.sql.Date;
import java.util.ArrayList;

public class FoodModel extends BaseModel {
    //Tạo
    /**
     * Thêm thực phẩm vào cơ sở dữ liệu
     * @param food thực phẩm cần thêm
     * @return true nếu thêm thành công, false nếu không thành công
     */
    public boolean insertFood(Food food) {
        try {
            String sql = "INSERT INTO food (foodName, unitType, quantity, expirationDate) VALUES (?, ?, ?, ?)";
            var preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getUnitType().toString());
            preparedStatement.setDouble(3, food.getQuantity());
            preparedStatement.setDate(4, Date.valueOf(food.getExpirationDate()));
            int rowsAffected = preparedStatement.executeUpdate();
            closeConnection();
            return rowsAffected > 0;
        }
        catch (Exception e) {
            System.out.println("Lỗi khi thêm thực phẩm: " + e.getMessage());
            return false;
        }
    }
    // Cập nhật
    public boolean updateFoodQuantity(int foodId, double removeQuantity) {
        return false;
    }

    // Đọc
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foods = new ArrayList<>();
        try {
            String sql = "SELECT * FROM food";
            var statement = getConnection().createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("foodId");
                String name = resultSet.getString("foodName");
                UnitType unitType = UnitType.valueOf(resultSet.getString("unitType"));
                double quantity = resultSet.getDouble("quantity");
                Date expirationDate = resultSet.getDate("expirationDate");
                Food food = new Food(name, unitType, (float)quantity, expirationDate.toLocalDate());
                foods.add(food);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách thực phẩm: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return foods;
    }

    public static void main(String[] args) {
        FoodModel foodModel = new FoodModel();
        ArrayList<Food> foods = foodModel.getAllFood();
        for (Food food : foods) {
            System.out.println("Tên: " + food.getName() + ", Đơn vị: " + food.getUnitType() + ", Số lượng: " + food.getQuantity() + ", Ngày hết hạn: " + food.getExpirationDate());
        }
    }
}
