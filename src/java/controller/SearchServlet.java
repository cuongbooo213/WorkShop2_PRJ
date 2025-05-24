package controller;

import dal.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        EmployeeDao dao = EmployeeDao.getInstance();
        List<Employee> employees = dao.getAllEmployees();
        List<Employee> searchResults = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            for (Employee emp : employees) {
                if ((emp.getFirstName() != null && emp.getFirstName().toLowerCase().contains(keyword.toLowerCase())) ||
                    (emp.getLastName() != null && emp.getLastName().toLowerCase().contains(keyword.toLowerCase()))) {
                    searchResults.add(emp);
                }
            }
        }

        request.setAttribute("employeeList", searchResults);
        RequestDispatcher rd = request.getRequestDispatcher("/search.jsp");
        rd.forward(request, response);
    }
}