package david_seu.practicweb.dao;

import david_seu.practicweb.model.Parent;
import david_seu.practicweb.util.HibernateUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

public class ParentDao extends GenericDao<Parent>{
    public ParentDao() {
        super(Parent.class);
    }

    public Parent findByUsername(String username) {
        Parent entity = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            entity = session.createQuery("from Parent where username = :username", Parent.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
}
