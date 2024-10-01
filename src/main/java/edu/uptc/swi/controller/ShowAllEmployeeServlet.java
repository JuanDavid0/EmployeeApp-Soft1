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

@WebServlet("/ShowAllEmployee")
public class ShowAllEmployeeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();

        ArrayList<Employee> employeeList = (ArrayList<Employee>) context.getAttribute("employeeList");

        if (employeeList == null) {
            employeeList = new ArrayList<>();
            context.setAttribute("employeeList", employeeList);
        }

        req.getSession().setAttribute("employeeList", employeeList);
        
        resp.sendRedirect("showAll.jsp");
    }
}
