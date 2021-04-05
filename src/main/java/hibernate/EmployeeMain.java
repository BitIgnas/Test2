package hibernate;

import org.hibernate.Session;

public class EmployeeMain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee1 = new Employee();
        employee1.setName("Mikas");
        employee1.setDepartment("it");
        employee1.setSalary(5000);

        Employee employee2 = new Employee();
        employee2.setName("Ignas");
        employee2.setDepartment("it");
        employee2.setSalary(3000);

        Employee employee3 = new Employee();
        employee3.setName("Dominyka");
        employee3.setDepartment("HR");
        employee3.setSalary(1000);

        session.save(employee1);
        session.save(employee2);
        session.save(employee3);

        EmployeeDao.findAll();
        EmployeeDao.findNames();

        Employee employeeToSave = new Employee();
        employeeToSave.setName("Ivan");
        employeeToSave.setDepartment("Management");
        employeeToSave.setSalary(500);

        EmployeeDao.saveEmployee(employeeToSave);


    }
}
