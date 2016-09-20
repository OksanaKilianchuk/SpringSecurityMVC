package ua.kiev.prog.daoImpl;

import org.springframework.stereotype.Repository;
import ua.kiev.prog.dao.UserDAO;
import ua.kiev.prog.entity.Contact;
import ua.kiev.prog.entity.CustomUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by uzer on 18.09.2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CustomUser findByLogin(String login) {
        Query query = entityManager.createQuery("SELECT u FROM CustomUser u WHERE u.login = :login", CustomUser.class);
        query.setParameter("login", login);
        return (CustomUser)query.getSingleResult();
    }

    @Override
    public void add(CustomUser user) {
        entityManager.merge(user);
    }
}
