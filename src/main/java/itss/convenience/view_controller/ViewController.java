package itss.convenience.view_controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class ViewController extends Application {
    private  static ViewController instance;
    @Override
    public void start(Stage stage) throws Exception {
        // This method is intentionally left empty.
        // The actual view will be opened through the openView method.
    }
    public BaseView openView(BaseView view, String title, int width, int height) {
        Stage stage = new Stage();
        return view.loadAndShow(stage, title, width, height);
    }


    public static void main(String[] args) {
        launch(args);
    }
    public static ViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }
}
