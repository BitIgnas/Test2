package hibernate;

import org.hibernate.Session;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class EmployeeMain {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee1 = new Employee();
        employee1.setName("Mikas");
        employee1.setPassword("654321");
        employee1.setDepartment("it");
        employee1.setSalary(5000);

        Employee employee2 = new Employee();
        employee2.setName("Ignas");
        employee2.setPassword("null123");
        employee2.setDepartment("it");
        employee2.setSalary(3000);

        Employee employee3 = new Employee();
        employee3.setName("Dominyka");
        employee3.setPassword("Debora");
        employee3.setDepartment("HR");
        employee3.setSalary(1000);

        session.save(employee1);
        session.save(employee2);
        session.save(employee3);

        EmployeeDao.findAll();

        Employee employeeToSave = new Employee();
        employeeToSave.setName("Ivan");
        employeeToSave.setDepartment("Management");
        employeeToSave.setSalary(500);

        session.save(employeeToSave);
        EmployeeDao.deleteById(4L);

        Employee employeeToUpdate = new Employee();
        employeeToUpdate.setName("Mark");
        employeeToUpdate.setPassword("123456");
        employeeToUpdate.setDepartment("Hr");
        employeeToUpdate.setSalary(2000);

        EmployeeDao.updateEmployee(employeeToUpdate, 3L);

        System.out.println("--------------------------------");
        List<Employee> employeeList = EmployeeDao.findNames();
        employeeList.stream().forEach(s -> System.out.println(s));

        List <Employee> employeeListOf2000 = EmployeeDao.getSalaryGreaterThan2000();
        employeeListOf2000.stream().forEach(s -> System.out.println(s));

        Employee employee = EmployeeDao.getEmployeePassword("654321");
        System.out.println(employee.getName());

        List<Employee> employeeListWithJPA = EmployeeDao.getAllWithCriteriaJPA();
        employeeListWithJPA.stream().forEach(s -> System.out.println(s));

        Employee employeeJPAPassword = EmployeeDao.getUserByPassword("null123");
        System.out.println(employeeJPAPassword);

        EmployeeDao.test();

        Employee employeeWithPassAndName = EmployeeDao.getEmployeePasswordWithName("Ignas", "null123");
        System.out.println("---------------------");
        System.out.println(employeeWithPassAndName);

    }
}
