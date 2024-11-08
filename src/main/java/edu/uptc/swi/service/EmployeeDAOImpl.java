package edu.uptc.swi.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.uptc.swi.model.Employee;

public class EmployeeDAOImpl implements IEmployeeDAO {

    private static String USER;
    private static String PASSWORD;
    private static String DRIVER;
    private static String URL;
    private static Connection connection = null;
    private Statement stmt = null;

    static {
        Properties properties = new Properties();
        try (InputStream input = EmployeeDAOImpl.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo application.properties");
            }
        
            properties.load(input);
            URL = properties.getProperty("db.url");
            USER = properties.getProperty("db.user");
            PASSWORD = properties.getProperty("db.password");
            DRIVER = properties.getProperty("db.driver");
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + ex.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        return this.getEmployees();
    }

    @Override
    public Employee findById(String id) {
        return this.getEmployee(String.valueOf(id));
    }

    @Override
    public boolean save(Employee employee) {
        boolean res;
        String id = this.getEmployee(employee.getId()).getId();
        if (id != null)
            res = this.executeQuery("update employee set id='" + employee.getId() + "', name='" + employee.getName()
                    + "', email='" + employee.getEmail() + "', phone='" + employee.getPhone() + "' where id='"
                    + employee.getId() + "';");
        else
            res = this.executeQuery("insert into employee (id, name, email, phone) values('" + employee.getId() + "','"
                    + employee.getName() + "','" + employee.getEmail() + "','" + employee.getPhone() + "');");
        return res;
    }

    @Override
    public boolean deleteEmployeeById(String id) {
        boolean res = false;
        if (this.getEmployee(id) != null)
            res = this.executeQuery("delete from employee where id='" + id + "';");
        return res;
    }

    private boolean executeQuery(String query) {
        boolean res = false;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            connection.close();
            res = true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return res;
    }

    private Employee getEmployee(String id) {
        String query = "select * from employee where id=" + id + ";";
        Employee emp = new Employee();
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                emp.setId(rs.getString("id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return emp;
    }

    private List<Employee> getEmployees() {
        String query = "select * from employee";
        List<Employee> list = new ArrayList<>();
        Employee employee;
        ResultSet rs;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getString("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setPhone(rs.getString("phone"));
                list.add(employee);
            }
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return list;
    }
}
