package edu.uptc.swi.controller;

import java.io.IOException;
import java.util.List;

import edu.uptc.swi.model.Employee;
import edu.uptc.swi.service.EmployeeDAOImpl;
import edu.uptc.swi.service.IEmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowAllEmployee")
public class ShowAllEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IEmployeeDAO employeeDAO;

    @Override
    public void init() {
        employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employeeList = employeeDAO.findAll();

        req.getSession().setAttribute("employeeList", employeeList);

        resp.sendRedirect("showAll.jsp");
    }
}
