package itss.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.scene.Node;

public class GroupSetupController extends BaseController {

    @FXML
    private VBox joinGroupPane;

    @FXML
    private VBox createGroupPane;

    @FXML
    private ListView<String> groupListView;

    @FXML
    private TextField groupNameField;

    @Override
    public String getFxmlPath() {
        return "/view/group-setup.fxml";
    }

    @FXML
    public void showJoinGroup() {
        // Giả lập danh sách nhóm có sẵn
        groupListView.getItems().setAll(
                "Gia đình thân yêu",
                "Lớp 12A1",
                "Cùng nhau nấu ăn",
                "Team văn phòng IT"
        );
        switchPane(joinGroupPane, createGroupPane);
    }

    @FXML
    public void showCreateGroup() {
        switchPane(createGroupPane, joinGroupPane);
    }

    @FXML
    public void handleJoinGroup() {
        String selectedGroup = groupListView.getSelectionModel().getSelectedItem();
        if (selectedGroup != null) {
            System.out.println("✅ Tham gia nhóm: " + selectedGroup);
            // TODO: Gửi yêu cầu tham gia nhóm tới DB
        } else {
            System.out.println("⚠️ Bạn chưa chọn nhóm để tham gia.");
        }
    }

    @FXML
    public void handleCreateGroup() {
        String groupName = groupNameField.getText().trim();
        if (!groupName.isEmpty()) {
            System.out.println("✅ Tạo nhóm mới: " + groupName);
            // TODO: Gửi yêu cầu tạo nhóm tới DB
        } else {
            System.out.println("⚠️ Vui lòng nhập tên nhóm.");
        }
    }

    /**
     * Chuyển giữa hai pane: ẩn cái cũ, hiện cái mới kèm hiệu ứng mượt
     */
    private void switchPane(VBox show, VBox hide) {
        if (hide != null) {
            hide.setVisible(false);
            hide.setManaged(false);
        }

        if (show != null) {
            show.setOpacity(0);
            show.setVisible(true);
            show.setManaged(true);

            FadeTransition ft = new FadeTransition(Duration.millis(250), show);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();
        }
    }
}
