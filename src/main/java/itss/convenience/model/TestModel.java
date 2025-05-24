package itss.convenience.model;

public class TestModel extends BaseModel {
    public static void main(String[] args) {
        TestModel testModel = new TestModel();
        testModel.getConnection();
        testModel.closeConnection();
    }
}
