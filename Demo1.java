import entities.Department;
import entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Demo1 {


    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Employee employee1 = new Employee();
        employee1.setEmployeeName("Sean Murphy");
        employee1.setUsername("seanm");
        employee1.setPassword("pass#123");
        employee1.setAccessLevel(1);

        Employee employee2 = new Employee();
        employee2.setEmployeeName("Barry Johnson");
        employee2.setUsername("barryJ");
        employee2.setPassword("barry@123");
        employee2.setAccessLevel(1);

        Employee employee3 = new Employee();
        employee3.setEmployeeName("Janet Warren");
        employee3.setUsername("janetW");
        employee3.setPassword("janet#123");
        employee3.setAccessLevel(1);

        Employee employee4 = new Employee();
        employee4.setEmployeeName("Pamela Smith");
        employee4.setUsername("pamelaS");
        employee4.setPassword("pamela#123");
        employee4.setAccessLevel(2);

        Employee employee5 = new Employee();
        employee5.setEmployeeName("Eric Miller");
        employee5.setUsername("ericM");
        employee5.setPassword("eric#123");
        employee5.setAccessLevel(2);


        Department department1 = new Department();
        department1.setDeptName("IT");

        department1.getEmployees().add(employee1);
        employee1.setDepartment(department1);

        department1.getEmployees().add(employee2);
        employee2.setDepartment(department1);

        department1.getEmployees().add(employee3);
        employee3.setDepartment(department1);

        Department department2 = new Department();
        department2.setDeptName("Finance");

        department2.getEmployees().add(employee4);
        employee4.setDepartment(department2);


        Department department3 = new Department();
        department3.setDeptName("HR");

        department3.getEmployees().add(employee5);
        employee5.setDepartment(department3);


        session.persist(department1);
        session.persist(department2);
        session.persist(department3);

        session.getTransaction().commit();


        session = sessionFactory.openSession();

        //List<Department> departments = session.createQuery("select d From Department d", Department.class).getResultList();
        List<Department> departments = session.createQuery("select d From Department d JOIN fetch d.employees", Department.class).getResultList();

        for (Department department : departments) {
            System.out.println("Department details:::::");

            System.out.println(department.getId() + "\t" + department.getDeptName());
            List<Employee> employees = department.getEmployees();
            System.out.println("Employees details::::::");
            for (Employee employee : employees) {

                System.out.println(employee.getId() + "\t" + employee.getEmployeeName() + "\t" + employee.getUsername()
                        + "\t" + employee.getPassword() + "\t" + employee.getAccessLevel());
            }

        }
    }
}

