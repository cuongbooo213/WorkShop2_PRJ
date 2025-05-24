/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Cuong
 */
public class Departments {

    int department_id;
    String department_name;
    Locations location_id;

    public Departments() {
    }

    public Departments(int department_id, String department_name, Locations location_id) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.location_id = location_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Locations getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Locations location_id) {
        this.location_id = location_id;
    }

}
