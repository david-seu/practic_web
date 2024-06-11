package david_seu.practicweb.dao;

import david_seu.practicweb.model.User;
import david_seu.practicweb.util.HibernateUtil;
import org.hibernate.Session;

public class UserDao extends GenericDao<User>{

    public UserDao() {
        super(User.class);
    }

    public User findByUsername(String username) {
        User entity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
