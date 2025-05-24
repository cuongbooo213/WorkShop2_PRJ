/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author Cuong
 */
public class Jobs {

    int job_id;
    String job_title;
    BigDecimal min_salary;
    BigDecimal max_salary;

    public Jobs() {
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public BigDecimal getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(BigDecimal min_salary) {
        this.min_salary = min_salary;
    }

    public BigDecimal getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(BigDecimal max_salary) {
        this.max_salary = max_salary;
    }

    public Jobs(int job_id, String job_title, BigDecimal min_salary, BigDecimal max_salary) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }
    
    
    

    
    
    
}
