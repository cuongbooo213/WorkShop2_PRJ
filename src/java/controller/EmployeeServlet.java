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
import model.Dependents;
import model.Employee;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        EmployeeDao dao = EmployeeDao.getInstance();
//        String departmentFilter = request.getParameter("department");
//        String showDependents = request.getParameter("showDependents");
//
//        // Lấy danh sách nhân viên và danh sách phòng ban
//        List<Employee> employeeList = dao.getAllEmployees();
//        List<String> departmentList = dao.getAllDepartmentNames();
//
//        // Áp dụng bộ lọc nếu có
//        if (departmentFilter != null && !departmentFilter.isEmpty()) {
//            List<Employee> filteredEmployees = new ArrayList<>();
//            for (Employee emp : employeeList) {
//                if (emp.getDepartment() != null && emp.getDepartment().getDepartment_name() != null &&
//                    emp.getDepartment().getDepartment_name().equals(departmentFilter)) {
//                    filteredEmployees.add(emp);
//                }
//            }
//            employeeList = filteredEmployees;
//        }
//
//        // Lấy danh sách người phụ thuộc nếu có yêu cầu
//        if (showDependents != null && !showDependents.isEmpty()) {
//            int employeeId = Integer.parseInt(showDependents);
//            List<Dependents> dependents = dao.getDependents(employeeId);
//            request.setAttribute("dependents", dependents);
//        }
//
//        // Đặt các thuộc tính để hiển thị trên JSP
//        request.setAttribute("employeeList", employeeList);
//        request.setAttribute("departmentList", departmentList);
//
//        // Chuyển tiếp đến employee.jsp
//        RequestDispatcher rd = request.getRequestDispatcher("/employee.jsp");
//        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmployeeDao dao = EmployeeDao.getInstance();
        String departmentFilter = request.getParameter("department");
        String showDependents = request.getParameter("showDependents");

        // Lấy danh sách nhân viên và danh sách phòng ban
        List<Employee> employeeList = dao.getAllEmployees();
        List<String> departmentList = dao.getAllDepartmentNames();

        // Áp dụng bộ lọc nếu có
        if (departmentFilter != null && !departmentFilter.isEmpty()) {
            List<Employee> filteredEmployees = new ArrayList<>();
            for (Employee emp : employeeList) {
                if (emp.getDepartment() != null && emp.getDepartment().getDepartment_name() != null &&
                    emp.getDepartment().getDepartment_name().equals(departmentFilter)) {
                    filteredEmployees.add(emp);
                }
            }
            employeeList = filteredEmployees;
        }

        // Lấy danh sách người phụ thuộc nếu có yêu cầu
        if (showDependents != null && !showDependents.isEmpty()) {
            int employeeId = Integer.parseInt(showDependents);
            List<Dependents> dependents = dao.getDependents(employeeId);
            request.setAttribute("dependents", dependents);
        }

        // Đặt các thuộc tính để hiển thị trên JSP
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("departmentList", departmentList);

        // Chuyển tiếp đến employee.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/employee.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}