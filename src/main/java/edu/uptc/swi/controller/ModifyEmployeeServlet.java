package edu.uptc.swi.controller;

import java.io.IOException;

import edu.uptc.swi.model.Employee;
import edu.uptc.swi.service.EmployeeDAOImpl;
import edu.uptc.swi.service.IEmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ModifyEmployee")
public class ModifyEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IEmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAOImpl();
        System.out.println("ModifyEmployeeServlet initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("searchId");

        String errorMessage = validateId(id);
        if (!errorMessage.isEmpty()) {
            req.getSession().setAttribute("error", errorMessage);
            resp.sendRedirect("error.jsp");
            return;
        }

        Employee employee = employeeDAO.findById(id);
        if (employee != null) {
            req.getSession().setAttribute("employee", employee);
            resp.sendRedirect("modify.jsp");
        } else {
            System.out.println("Empleado no encontrado: " + id);
            req.getSession().setAttribute("oper", "not_found");
            resp.sendRedirect("modify.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setPhone(phone);

        boolean updated = employeeDAO.save(employee);

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
