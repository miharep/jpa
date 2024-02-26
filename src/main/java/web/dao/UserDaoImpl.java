package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }
    
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    
    @Override
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    public void updateUser(User editUser, Long id) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(editUser.getFirstName());
        userToBeUpdated.setLastName(editUser.getLastName());
        userToBeUpdated.setEmail(editUser.getEmail());
    }
    
    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUser(id));
    }
}