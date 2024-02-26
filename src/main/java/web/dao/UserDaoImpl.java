package web.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    
    @PersistenceContext
    private  EntityManager entityManager;
    
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }
    
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void addUser(User user) {
        entityManager.persist(user);
    }
    
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public User getUser(Long id) {
        return entityManager.find(User.class, id);
    }
    
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void updateUser(User editUser, Long id) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(editUser.getFirstName());
        userToBeUpdated.setLastName(editUser.getLastName());
        userToBeUpdated.setEmail(editUser.getEmail());
    }
    
    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void deleteUser(Long id) {
        entityManager.remove(getUser(id));
    }
}