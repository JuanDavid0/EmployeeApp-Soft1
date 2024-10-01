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

@WebServlet("/ModifyEmployee")
public class ModifyEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        String id = req.getParameter("searchId");

        String errorMessage = validateId(id);
        if (!errorMessage.isEmpty()) {
            req.getSession().setAttribute("error", errorMessage);
            resp.sendRedirect("error.jsp");
            return;
        }

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
            resp.sendRedirect("modify.jsp");
        } else {
            System.out.println("Empleado no encontrado: " + id);
            req.getSession().setAttribute("oper", "not_found");
            resp.sendRedirect("modify.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        String errorMessage = validateEmployeeData(id, name, email, phone);
        if (!errorMessage.isEmpty()) {
            req.getSession().setAttribute("error", errorMessage);
            resp.sendRedirect("error.jsp");
            return;
        }

        boolean updated = false;
        for (Employee employee : employeeList) {
            if (employee.getId().equals(id)) {
                employee.setName(name);
                employee.setEmail(email);
                employee.setPhone(phone);
                updated = true;
                break;
            }
        }

        if (updated) {
            req.getSession().setAttribute("oper", "success");
        } else {
            req.getSession().setAttribute("oper", "error");
        }

        req.getSession().removeAttribute("employee");
        resp.sendRedirect("modify.jsp");
    }

    private String validateEmployeeData(String id, String name, String email, String phone) {
        StringBuilder errors = new StringBuilder();

        if (id == null || !id.matches("\\d+")) {
            errors.append("ID debe ser numérico y no puede estar vacío.<br>");
        }

        if (name == null || name.trim().isEmpty()) {
            errors.append("Nombre no puede estar vacío.<br>");
        } else if (name.length() < 3) {
            errors.append("Nombre debe tener al menos 3 caracteres.<br>");
        }

        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            errors.append("Correo electrónico no válido.<br>");
        }

        if (phone == null || !phone.matches("\\d{7,10}")) {
            errors.append("Teléfono debe contener entre 7 y 10 dígitos.<br>");
        }

        return errors.toString();
    }

    private String validateId(String id) {
        if (id == null || !id.matches("\\d+")) {
            return "ID debe ser numérico y no puede estar vacío.<br>";
        }
        return "";
    }
}
