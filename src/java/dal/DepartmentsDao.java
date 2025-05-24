package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Departments;
import model.Locations;

public class DepartmentsDao {

    private static DepartmentsDao instance;
    private Connection con;

    // Private constructor to ensure only one instance of DepartmentsDao
    private DepartmentsDao() {
        con = new DBContext().c; // Initialize connection from DBContext
    }

    public static DepartmentsDao getInstance() {
        if (instance == null) {
            instance = new DepartmentsDao();
        }
        return instance;
    }

    public List<Departments> getAllDepartments() {
        List<Departments> list = new ArrayList<>();
        String query = "select d.department_id, d.department_name, l.location_id, l.street_address, l.postal_code, l.city, l.state_province, l.country_id "
                     + "from departments d "
                     + "join locations l on d.location_id = l.location_id";
        try (PreparedStatement st = con.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Departments depart = new Departments();
                depart.setDepartment_id(rs.getInt("department_id"));
                depart.setDepartment_name(rs.getString("department_name"));
                
                Locations loca = new Locations();
                loca.setLocation_id(rs.getInt("location_id"));
                loca.setStreet_address(rs.getString("street_address"));
                loca.setPostal_code(rs.getString("postal_code"));
                loca.setCity(rs.getString("city"));
                loca.setState_province(rs.getString("state_province"));
                loca.setCountry_id(rs.getString("country_id")); // Corrected line

                depart.setLocation_id(loca);
                list.add(depart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        DepartmentsDao ad = DepartmentsDao.getInstance();

        // Get the list of all departments
        List<Departments> departmentList = ad.getAllDepartments();

        // Print department details
        for (Departments dept : departmentList) {
            System.out.println("Department ID: " + dept.getDepartment_id() + ", Department Name: " + dept.getDepartment_name());
        }
    }
}
