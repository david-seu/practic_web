package david_seu.practicweb.dao;

import david_seu.practicweb.model.Parent;
import david_seu.practicweb.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ParentDao extends GenericDao<Parent> {
    public ParentDao() {
        super(Parent.class);
    }

    public Parent findByName(String name) {
        Parent entity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.createQuery("from Parent where name = :name", Parent.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<Parent> findAllByUserId(Long userId) {
        List<Parent> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Parent where user.id = :userId", Parent.class)
                    .setParameter("userId", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List<Parent> findAllByNameContaining(String name) {
        List<Parent> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Parent where name like :name", Parent.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List<Parent> findAllByUserIdByNameContaining(Long userId, String name) {
        List<Parent> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Parent where user.id = :userId and name like :name", Parent.class)
                    .setParameter("userId", userId)
                    .setParameter("name", "%" + name + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
}
