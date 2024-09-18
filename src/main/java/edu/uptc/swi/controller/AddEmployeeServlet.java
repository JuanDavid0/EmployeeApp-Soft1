package edu.uptc.swi.controller;

import java.io.IOException;
import java.util.ArrayList;

import edu.uptc.swi.model.Employee;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        // Inicializar la lista de empleados si no existe
        ServletContext context = getServletContext();
        if (context.getAttribute("employeeList") == null) {
            context.setAttribute("employeeList", new ArrayList<Employee>());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la lista de empleados del contexto
        ServletContext context = getServletContext();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        // Crear el empleado y añadirlo a la lista
        Employee emp = new Employee();
        emp.setId(id);
        emp.setName(name);
        emp.setEmail(email);
        emp.setPhone(phone);
        employeeList.add(emp);

        // Actualizar la lista de empleados en el contexto
        context.setAttribute("employeeList", employeeList);

        // Imprimir para depuración
        System.out.println("Empleado añadido:");
        for (Employee employee : employeeList) {
            System.out.println(employee.getId() + " - " + employee.getName());
        }

        req.getSession().setAttribute("oper", "success");
        resp.sendRedirect("index.jsp");
    }
}
