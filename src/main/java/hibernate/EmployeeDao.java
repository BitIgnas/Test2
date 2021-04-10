package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class EmployeeDao {

    public static void findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Employee";
        Query query = session.createQuery(hql);
        List employees = query.list();

        for (Object employee : employees) {
            System.out.println(employee);
        }

        session.close();
    }

    public static List<Employee> findNames() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List employee = session.createCriteria(Employee.class).list();

        session.close();

        return employee;
    }

    public static void deleteById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from Employee Where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        int affectedRows = query.executeUpdate();
        System.out.println("deleted deleted: " + affectedRows);

        session.flush();
        session.close();

    }

    public static void updateEmployee(Employee employee, Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "Update Employee SET name = :empName, department = :empDepartment, salary = :empSalary WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("empName", employee.getName());
        query.setParameter("empDepartment", employee.getDepartment());
        query.setParameter("empSalary", employee.getSalary());
        query.setParameter("id", id);
        int affectedRows = query.executeUpdate();
        System.out.println("updated deleted: " + affectedRows);
        session.flush();
        session.close();
    }

    public static Employee returnEmployeeObjectByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = (Employee) session.createCriteria(Employee.class)
                .add(Restrictions.eq("name", name))
                .list().get(0);

        session.flush();
        session.close();

        return employee;
    }

    public static List<Employee> getSalaryGreaterThan2000() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        List employeeList = session.createCriteria(Employee.class)
                .add(Restrictions.gt("salary", 2000))
                .list();
        session.close();

        return employeeList;
    }

    public static Employee getEmployeePassword(String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Employee employee = (Employee) session.createCriteria(Employee.class)
                .add(Restrictions.eq("password", password)).list().get(0);
        session.close();

        return employee;
    }

    public static List<Employee> getAllWithCriteriaJPA() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        Query query = session.createQuery(criteriaQuery.select(employeeRoot));
        List<Employee> employeeList = query.getResultList();
        session.close();

        return employeeList;
    }

    public static Employee getUserByPassword(String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);

        Query query = session.createQuery(criteriaQuery.select(employeeRoot)
                .where(criteriaBuilder.equal(employeeRoot.get("password"), password)));

        Employee employeeList = (Employee) query.getResultList().get(0);
        session.close();

        return employeeList;

    }

    public static void test() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> employeeCriteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = employeeCriteriaQuery.from(Employee.class);

        CriteriaQuery<Employee> criteriaQuery = employeeCriteriaQuery
                .select(root)
                .where(criteriaBuilder.gt(root.get("salary"), 2000));

        Query query = session.createQuery(criteriaQuery);
        List<Employee> employees = query.getResultList();

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    public static Employee getEmployeePasswordWithName(String name, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> critQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = critQuery.from(Employee.class);

        Predicate employeeName = criteriaBuilder.equal(employeeRoot.get("name"), name);
        Predicate employeePassword = criteriaBuilder.equal(employeeRoot.get("password"), password);
        Predicate nameAndPassword = criteriaBuilder.and(employeeName, employeePassword);
        critQuery.where(nameAndPassword);

        List<Employee> employeeList = session.createQuery(critQuery).getResultList();

        return employeeList.get(0);
    }
}
