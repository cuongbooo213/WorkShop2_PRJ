package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Dependents;
import model.Departments;
import model.Employee;
import model.Jobs;
import model.Locations;

public class EmployeeDao {

    private static EmployeeDao instance;
    private Connection con;

    private EmployeeDao() {
        con = new DBContext().c;
    }

    public static EmployeeDao getInstance() {
        if (instance == null) {
            instance = new EmployeeDao();
        }
        return instance;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.phone_number, "
                + "e.hire_date, j.job_id, j.job_title, d.department_id, d.department_name, "
                + "l.location_id, l.street_address, l.postal_code, l.city, l.state_province, l.country_id, "
                + "e.salary, e.manager_id "
                + "FROM employees e "
                + "JOIN jobs j ON e.job_id = j.job_id "
                + "LEFT JOIN departments d ON e.department_id = d.department_id "
                + "LEFT JOIN locations l ON d.location_id = l.location_id";

        try (PreparedStatement st = con.prepareStatement(sql); ResultSet resultSet = st.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setPhoneNumber(resultSet.getString("phone_number"));
                employee.setHireDate(resultSet.getDate("hire_date"));
                employee.setSalary(resultSet.getBigDecimal("salary"));

                int managerId = resultSet.getInt("manager_id");
                if (resultSet.wasNull()) {
                    employee.setManagerId(null);
                } else {
                    employee.setManagerId(managerId);
                }

                Jobs job = new Jobs();
                job.setJob_id(resultSet.getInt("job_id"));
                job.setJob_title(resultSet.getString("job_title"));

                Departments department = new Departments();
                department.setDepartment_id(resultSet.getInt("department_id"));
                department.setDepartment_name(resultSet.getString("department_name"));

                Locations location = new Locations();
                location.setLocation_id(resultSet.getInt("location_id"));
                location.setStreet_address(resultSet.getString("street_address"));
                location.setPostal_code(resultSet.getString("postal_code"));
                location.setCity(resultSet.getString("city"));
                location.setState_province(resultSet.getString("state_province"));
                location.setCountry_id(resultSet.getString("country_id"));

                department.setLocation_id(location);
                employee.setJob(job);
                employee.setDepartment(department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<String> getAllDepartmentNames() {
        List<String> departmentNames = new ArrayList<>();
        String sql = "SELECT department_name FROM departments WHERE department_name IS NOT NULL";
        try (PreparedStatement st = con.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                departmentNames.add(rs.getString("department_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentNames;
    }

    public List<Dependents> getDependents(int employeeId) {
        List<Dependents> dependents = new ArrayList<>();
        String sql = "SELECT dependent_id, first_name, last_name, relationship, employee_id FROM dependents WHERE employee_id = ?";
        try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, employeeId);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Dependents dependent = new Dependents();
                    dependent.setDependentId(rs.getInt("dependent_id"));
                    dependent.setFirstName(rs.getString("first_name"));
                    dependent.setLastName(rs.getString("last_name"));
                    dependent.setRelationship(rs.getString("relationship"));
                    dependent.setEmployeeId(rs.getInt("employee_id"));
                    dependents.add(dependent);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dependents;
    }

    public static void main(String[] args) {
        EmployeeDao employeeDao = EmployeeDao.getInstance();
        List<Employee> employees = employeeDao.getAllEmployees();
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeId());
            System.out.println("First Name: " + employee.getFirstName());
        }
    }
}