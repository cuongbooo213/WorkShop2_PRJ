package controller;

import dal.EmployeeDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Employee;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private String userID;
    private String password;

    @Override
    public void init() throws ServletException {
        userID = getServletContext().getInitParameter("adminUsername");
        password = getServletContext().getInitParameter("adminPassword");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("employees");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        if (userID != null && password != null && userID.equals(user) && password.equals(pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30 * 60);

            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);
            response.addCookie(userName);

            EmployeeDao dao = EmployeeDao.getInstance();
            List<Employee> employeeList = dao.getAllEmployees();
            session.setAttribute("employeeList", employeeList);

            response.sendRedirect("employees");
        } else {
            request.setAttribute("error", "Tên người dùng hoặc mật khẩu không đúng. Vui lòng thử lại!");
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }
}