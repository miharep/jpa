package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    
    void addUser(User user);
    
    User getUser(Long id);
    
    void updateUser(User editUser, Long id);
    
    void deleteUser(Long id);
}