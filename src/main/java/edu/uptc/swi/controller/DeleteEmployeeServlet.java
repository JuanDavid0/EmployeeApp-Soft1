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

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private IEmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAOImpl();
        System.out.println("DeleteEmployeeServlet initialized.");
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
            req.getSession().setAttribute("oper", "found");
        } else {
            req.getSession().setAttribute("oper", "not_found");
        }

        resp.sendRedirect("delete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        String errorMessage = validateId(id);
        if (!errorMessage.isEmpty()) {
            req.getSession().setAttribute("error", errorMessage);
            resp.sendRedirect("error.jsp");
            return;
        }

        boolean deleted = employeeDAO.deleteEmployeeById(id);

        if (deleted) {
            req.getSession().setAttribute("oper", "deleted");
        } else {
            req.getSession().setAttribute("oper", "error");
        }

        req.getSession().removeAttribute("employee");
        resp.sendRedirect("delete.jsp");
    }

    private String validateId(String id) {
        if (id == null || !id.matches("\\d+")) {
            return "ID debe ser numérico y no puede estar vacío.<br>";
        }
        return "";
    }

}
