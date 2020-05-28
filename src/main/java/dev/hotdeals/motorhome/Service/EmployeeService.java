package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    // Returns an Employee with the provided ID
    public Employee fetchByID ( int employeeID )
    {
       return employeeRepo.fetchByID( employeeID );
    }

    // Returns a list of all the Employees in the DB
    public List<Employee> fetchAll ()
    {
        return employeeRepo.fetchAll();
    }

    // Returns a list of all Employees of which Name contains the String provided
    public List<Employee> searchByName ( String name )
    {
        return employeeRepo.searchByName( name );
    }

    // Returns a list of all Employees of which Position contains the String provided
    public List<Employee> searchByPosition ( String position )
    {
        return employeeRepo.searchByPosition( position );
    }

    // Adds an Employee to the DB
    public boolean addEmployee ( Employee employee )
    {
        return employeeRepo.addEmployee( employee );
    }

    // Updates the row of a specified Employee with the information from the given Employee Object
    public boolean updateEmployee ( Employee employee )
    {
        return employeeRepo.updateEmployee( employee );
    }

    // Deletes the Employee with the given ID from the DataBase
    public boolean deleteEmployee ( int employeeID )
    {
        return employeeRepo.deleteEmployee( employeeID );
    }

    public String createUsername ( Employee employee )
    {
        String username, employeeFirstName = employee.getFirstName().toLowerCase();
        if (employeeFirstName.length() >= 4)
        {
            username = employeeFirstName.substring(0,4);
        }
        else
        {
            username = employeeFirstName;
            while (username.length() < 4)
            {
                username += "#";
            }
        }
        username += "_" + employee.getPosition().substring(0,4).toLowerCase() + employee.getId()%10;

        return username;
    }
}
