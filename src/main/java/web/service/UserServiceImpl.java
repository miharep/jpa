package web.service;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, SmartInitializingSingleton {
    
    private final UserDao userDao;
    
    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.listUsers();
    }
    
    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }
    
    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
    
    @Override
    @Transactional
    public void updateUser(User editUser, Long id) {
        userDao.updateUser(editUser, id);
    }
    
    @Override
    @Transactional
    public void afterSingletonsInstantiated() {
        userDao.addUser(new User("Name1", "Lastname1", "user1@mail.ru"));
        userDao.addUser(new User("Name2", "Lastname2", "user2@mail.ru"));
        userDao.addUser(new User("Name3", "Lastname3", "user3@mail.ru"));
        userDao.addUser(new User("Name4", "Lastname4", "user4@mail.ru"));
    }
    
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}