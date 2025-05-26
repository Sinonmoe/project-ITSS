package itss.controller;

import itss.model.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController extends BaseController implements Initializable {

    private final UserService userService = new UserService();

    @Override
    public String getFxmlPath() {
        return "/view/dashboard-view.fxml";
    }
}
