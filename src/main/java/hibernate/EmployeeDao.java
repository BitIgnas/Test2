package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao {

    public static void findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Employee";
        Query query = session.createQuery(hql);
        List employees = query.list();

        for (Object employee : employees) {
            System.out.println(employee);
        }
    }

    public static void findNames(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "Select e.name From Employee e";
        Query query = session.createQuery(hql);
        List employees = query.list();

        for (Object employee : employees) {
            System.out.println(employee);
        }
    }
}
