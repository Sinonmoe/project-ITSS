package itss.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class AdminDashboardController extends BaseController {

    @FXML private StackPane contentPane;
    @FXML private ListView<String> foodListView;
    @FXML private ListView<String> dishListView;
    @FXML private ListView<String> userListView;

    private enum ViewType { FOOD, DISH, USER }
    private ViewType currentView = ViewType.FOOD;

    @Override
    public String getFxmlPath() {
        return "/view/admin-dashboard.fxml";
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        showFoodPane();
    }

    // ==== Chuyển nội dung trung tâm ====

    @FXML
    public void showFoodPane() {
        switchView(ViewType.FOOD);
    }

    @FXML
    public void showDishPane() {
        switchView(ViewType.DISH);
    }

    @FXML
    public void showUserPane() {
        switchView(ViewType.USER);
    }

    private void switchView(ViewType type) {
        foodListView.setVisible(type == ViewType.FOOD);
        foodListView.setManaged(type == ViewType.FOOD);

        dishListView.setVisible(type == ViewType.DISH);
        dishListView.setManaged(type == ViewType.DISH);

        userListView.setVisible(type == ViewType.USER);
        userListView.setManaged(type == ViewType.USER);

        currentView = type;
    }

    // ==== Xử lý nút Thêm / Sửa / Xoá ====

    @FXML
    public void handleAdd() {
        switch (currentView) {
            case FOOD -> showAddFoodDialog();
            case DISH -> showAddDishDialog();
            case USER -> showAddUserDialog();
        }
    }

    @FXML
    public void handleEdit() {
        System.out.println("Sửa " + currentView);
    }

    @FXML
    public void handleDelete() {
        System.out.println("Xoá " + currentView);
    }

    private void showAddFoodDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm loại thực phẩm");
        dialog.setHeaderText("Nhập tên loại:");
        dialog.showAndWait().ifPresent(name -> {
            foodListView.getItems().add(name);
        });
    }

    private void showAddDishDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm món ăn");
        dialog.setHeaderText("Nhập tên món:");
        dialog.showAndWait().ifPresent(name -> {
            dishListView.getItems().add(name);
        });
    }

    private void showAddUserDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Thêm người dùng");
        dialog.setHeaderText("Nhập tên người dùng:");
        dialog.showAndWait().ifPresent(name -> {
            userListView.getItems().add(name);
        });
    }

    // ==== Xử lý đăng xuất ====

    @FXML
    public void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        new AuthController().loadAndShow(stage, "Đăng nhập", 800, 600);
        System.out.println("🔒 Đã đăng xuất.");
    }
}
