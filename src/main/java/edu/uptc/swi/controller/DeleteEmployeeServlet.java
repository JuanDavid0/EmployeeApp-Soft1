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

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        String id = req.getParameter("searchId");
        boolean found = false;
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                System.out.println("Empleado encontrado: " + id);
                req.getSession().setAttribute("employee", employee);
                found = true;
                break;
            }
        }

        if (found) {
            resp.sendRedirect("delete.jsp");
        } else {
            System.out.println("Empleado no encontrado: " + id);
            req.getSession().setAttribute("oper", "not_found");
            resp.sendRedirect("delete.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        String id = req.getParameter("id");
        boolean deleted = false;
        
        var iterator = employeeList.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId().equals(id)) {
                iterator.remove();
                deleted = true;
                break;
            }
        }

        /* PRUEBA CON PRINT */
        System.out.println("Contenido del ArrayList después de la eliminación:");
        for (Employee emp : employeeList) {
            System.out.println(emp);
        }

        if (deleted) {
            req.getSession().setAttribute("oper", "deleted");
        } else {
            req.getSession().setAttribute("oper", "error");
        }

        req.getSession().removeAttribute("employee");
        resp.sendRedirect("delete.jsp");
    }
}