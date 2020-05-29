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

    // Creates a Username based on the provided Employee
    public String createUsername ( Employee employee )
    {
        // Retrieves the First Name of the Employee and makes it lowercase.
        String username, employeeFirstName = employee.getFirstName().toLowerCase();
        // If the Name is longer or equal to 4 characters
        if (employeeFirstName.length() >= 4)
        {
            // The Username will contain the first 4 characters of the Employee's first name.
            username = employeeFirstName.substring(0,4);
        }
        else // If the Name is shorter than 4 characters
        {
            // The Username will contain the entire First Name and will be filled up with '#' until it reaches a length of 4.
            username = employeeFirstName;
            while (username.length() < 4)
            {
                username += "#";
            }
        }
        // The first 4 characters of the Employee's Job/Position within the Company, along with the last digit of their ID will be added to the Username.
        username += "_" + employee.getPosition().substring(0,4).toLowerCase() + employee.getId()%10;

        return username;
    }
}
