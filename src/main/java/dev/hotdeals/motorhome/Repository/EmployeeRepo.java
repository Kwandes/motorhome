package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepo
{
    @Autowired
    JdbcTemplate template;

    private Employee fetchById(int employeeID)
    {
        Employee employeeToReturn = null;
        return employeeToReturn;
    }

    private List<Employee> fetchAll()
    {
        List<Employee> employeesListToReturn = null;
        return employeesListToReturn;
    }

    private boolean addEmployee(Employee employee)
    {
        boolean status = false;
        return status;
    }

    private boolean updateEmployee (Employee employee)
    {
        boolean status = false;
        return status;
    }

    private boolean deleteEmployee(int employeeID)
    {
        boolean status = false;
        return status;
    }
}
