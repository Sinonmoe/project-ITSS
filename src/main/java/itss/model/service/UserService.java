package itss.model.service;

import itss.model.dao.UserDAO;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public boolean register(String username) {
        return userDAO.registerUser(username);
    }

    public boolean isUsernameTaken(String username) {
        return userDAO.isUsernameTaken(username);
    }

    public Integer getUserId(String username) {
        return userDAO.getUserIdByUsername(username);
    }

    public boolean hasGroup(int userId) {
        Integer groupId = userDAO.getGroupIdByUserId(userId);
        return groupId != null && groupId > 0;
    }
}
