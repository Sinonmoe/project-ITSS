package itss.convenience.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseModel {
    protected String url = "jdbc:mysql://localhost:3306/Convenience";
    protected String username = "root";
    protected String password = "Hoang!3070$";
    protected Connection connection = null;

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Kết nối thành công đến CSDL MySQL!");
            return connection;
        } catch (ClassNotFoundException e) {
            System.err.println("Lỗi: Không tìm thấy JDBC Driver (mysql-connector-j).");
        } catch (SQLException e) {
            System.err.println("Lỗi SQL khi kết nối CSDL:");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Mã lỗi: " + e.getErrorCode());
            System.err.println("Thông báo: " + e.getMessage());
        }
        return null;
    }

    protected void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối:");
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("Mã lỗi: " + e.getErrorCode());
                System.err.println("Thông báo: " + e.getMessage());
            }
        }
    }
}
