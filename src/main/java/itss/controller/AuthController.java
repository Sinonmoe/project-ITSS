package itss.controller;

import itss.model.service.UserService;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController extends BaseController {

    // Hai form (pane)
    @FXML private VBox loginPane;
    @FXML private VBox registerPane;

    // Các input và message
    @FXML private TextField loginUsername;
    @FXML private Label loginMessage;

    @FXML private TextField registerUsername;
    @FXML private Label registerMessage;

    private final UserService userService = new UserService();

    @Override
    public String getFxmlPath() {
        return "/view/auth-view.fxml";
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginMessage.setVisible(false);
        loginMessage.setManaged(false);
        registerMessage.setVisible(false);
        registerMessage.setManaged(false);

        // Mặc định hiện login pane
        showLoginPane();
    }

    // ========================= LOGIN ==========================

    @FXML
    protected void handleLogin(ActionEvent event) {
        String username = loginUsername.getText().trim();

        if (username.isEmpty()) {
            showTemporaryMessage(loginMessage, "Tên đăng nhập không được để trống.");
            return;
        }

        Integer userId = userService.getUserId(username);
        if (userId == null) {
            showTemporaryMessage(loginMessage, "Tài khoản không tồn tại. Vui lòng đăng ký.");
            return;
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (userService.hasGroup(userId)) {
            new DashboardController().loadAndShow(stage, "Trang chính", 800, 600);
        } else {
            new GroupSetupController().loadAndShow(stage, "Chào mừng!", 800, 600);
        }
    }

    @FXML
    public void showRegisterPane() {
        loginPane.setVisible(false);
        loginPane.setManaged(false);
        registerPane.setVisible(true);
        registerPane.setManaged(true);
    }

    // ========================= REGISTER ==========================

    @FXML
    protected void handleRegister(ActionEvent event) {
        String username = registerUsername.getText().trim();

        if (username.isEmpty()) {
            showTemporaryMessage(registerMessage, "Tên đăng ký không được để trống.");
            return;
        }

        // TODO: thêm user vào DB
        System.out.println("Đăng ký username: " + username);

        // Sau khi đăng ký xong -> quay lại login
        showLoginPane();
        showTemporaryMessage(loginMessage, "Đăng ký thành công. Hãy đăng nhập.");
    }

    @FXML
    public void showLoginPane() {
        registerPane.setVisible(false);
        registerPane.setManaged(false);
        loginPane.setVisible(true);
        loginPane.setManaged(true);
    }

    // ========================= UTILS ==========================

    private void showTemporaryMessage(Label label, String message) {
        label.setText(message);
        label.setVisible(true);
        label.setManaged(true);

        PauseTransition pause = new PauseTransition(Duration.millis(3000));
        pause.setOnFinished(e -> {
            label.setVisible(false);
            label.setManaged(false);
        });
        pause.play();
    }
}
