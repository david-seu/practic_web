package david_seu.practicweb.dao;

import david_seu.practicweb.model.Parent;
import david_seu.practicweb.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class ParentDao extends GenericDao<Parent>{

    public ParentDao() {
        super(Parent.class);
    }

    public List<Parent> findAllByNameContaining(String name) {
        List<Parent> parents = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Parent> cq = cb.createQuery(Parent.class);
            Root<Parent> root = cq.from(Parent.class);
            cq.select(root).where(cb.like(root.get("name"), "%" + name + "%"));
            parents = session.createQuery(cq).getResultList();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return parents;
    }

    public Parent findByName(String name) {
        Parent parent = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Parent where name = :name");
            query.setParameter("name", name);
            parent = (Parent) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parent;
    }
}
