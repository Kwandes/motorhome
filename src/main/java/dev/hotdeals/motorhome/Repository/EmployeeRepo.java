package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepo
{
    @Autowired
    JdbcTemplate template;

    // Returns an Employee with the provided ID
    public Employee fetchByID(int employeeID)
    {
        String query = "SELECT * FROM employee WHERE id = ?";
        RowMapper<Employee> rw = new BeanPropertyRowMapper<>(Employee.class);
        Employee employee;
        try
        {
            employee = template.queryForObject(query, rw, employeeID); // Attempt to retrieve the Employee from the DB
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the employee of ID : " + employeeID + "." );
            System.out.println(e);
            return null; // In case of failure, return null
        }
        return employee; // In case of success, return the Employee
    }

    // Returns a list of all Employees within the DB
    public List<Employee> fetchAll()
    {
        String query = "SELECT * FROM employee";
        RowMapper<Employee> rw = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employeeList;
        try
        {
            employeeList = template.query(query, rw); // Attempt to retrieve the Employee list
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the list of employees.");
            System.out.println(e);
            employeeList = new ArrayList<>(); // In case of failure, return an empty list
        }
        return employeeList;
    }

    // Returns a boolean ( status ) : true - if the Employee has been successfully added; false - if opposite
    public boolean addEmployee(Employee employee)
    {
        String query = "INSERT INTO employee (first_name, last_name, position, gender) VALUES ( ?, ?, ?, ? )";
        int rowsAffected = template.update(query, employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getGender());
        boolean status = rowsAffected > 0; // If higher than 0, it means that the Employee has been added
        return status;
    }

    // Returns a boolean ( status ) : true - if the Employee has been successfully update; false - if opposite
    public boolean updateEmployee (Employee employee)
    {
        String query = "UPDATE employee SET first_name = ?, last_name = ?, position = ?, gender = ? WHERE id = ?";
        int rowsAffected = template.update(query, employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getGender(), employee.getId());
        boolean status = rowsAffected > 0; // If higher than 0, it means that the Employee has been update
        return status;
    }

    // Returns a boolean ( status ) : true - if the Employee has been successfully update; false - is opposite
    public boolean deleteEmployee(int employeeID)
    {
        String query = "DELETE FROM employee WHERE id = ?";
        int rowsAffected = template.update(query,employeeID);
        boolean status = rowsAffected > 0; //  // If higher than 0, it means that the Employee has been deleted
        return status;
    }

    // Returns a list of all Employees of which Name contains the provided String
    public List<Employee> searchByName( String name )
    {
        String query = "SELECT * FROM employee WHERE CONCAT (first_name,\" \", last_name) LIKE CONCAT ('%', ? ,'%')";
        RowMapper<Employee> rw = new BeanPropertyRowMapper<>(Employee.class);
        List<Employee> employeeList;
        try
        {
            employeeList = template.query(query, rw, name); // Attempt to retrieve the Employee list
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the list of employees with a name containing :" + name + ".");
            System.out.println(e);
            employeeList = new ArrayList<>(); // In case of failure, return an empty list
        }
        return employeeList;
    }

    // Returns a list of all Employees of which Position contains the provided String
    public List<Employee> searchByPosition( String position )
    {
        String query = "SELECT * FROM employee WHERE position LIKE CONCAT ('%', ?, '%')";
        RowMapper rw = new BeanPropertyRowMapper(Employee.class);
        List<Employee> employeeList;
        try
        {
            employeeList = template.query(query, rw, position); // Attempt to retrieve the Employee list
        } catch ( EmptyResultDataAccessException e )
        {
            System.out.println("Failed to retrieve the list of employees with a position containing : " + position + ".");
            System.out.println(e);
            employeeList = new ArrayList<>(); // In case of failure, return an empty list
        }
        return employeeList;
    }
}
