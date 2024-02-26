package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();
    
    void addUser(User user);
    
    User getUser(Long id);
    
    void updateUser(User editUser, Long id);
    
    void deleteUser(Long id);
}