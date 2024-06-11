package david_seu.practicweb.dao;

import david_seu.practicweb.model.Child;
import david_seu.practicweb.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ChildDao extends GenericDao<Child>{
    public ChildDao() {
        super(Child.class);
    }

    public List<Child> findByParentId(int parentId) {
        List<Child> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Child where parent.id = :parentId", Child.class)
                    .setParameter("parentId", parentId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List<Child> findAllByNameContaining(String name) {
        List<Child> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Child where name like :name", Child.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }

    public List<Child> findAllByParentIdByNameContaining(int parentId, String name) {
        List<Child> entities = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entities = session.createQuery("from Child where parent.id = :parentId and name like :name", Child.class)
                    .setParameter("parentId", parentId)
                    .setParameter("name", "%" + name + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
}
