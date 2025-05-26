package itss.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends BaseDAO {
    public boolean isUsernameTaken(String username) {
        Connection conn = getConnection();
        try {
            String sql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    public boolean registerUser(String username) {
        if (isUsernameTaken(username)) return false;

        Connection conn = getConnection();
        try {
            String sql = "INSERT INTO users (username) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return false;
    }
    public Integer getUserIdByUsername(String username) {
        Connection conn = getConnection();
        try {
            String sql = "SELECT id FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
    public Integer getGroupIdByUserId(int userId) {
        Connection conn = getConnection();
        try {
            String sql = "SELECT group_id FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int groupId = rs.getInt("group_id");
                return rs.wasNull() ? null : groupId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }
}
