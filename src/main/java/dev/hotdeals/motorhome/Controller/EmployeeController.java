package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Redirects to the proper viewAll mapping ( in case of typo )
    @GetMapping({"/employee", "/employee/", "/employee/viewAll/"})
    public String redirectViewAll ()
    {
        return "redirect:/employee/viewAll";
    }

    // Adds a list of all the Employees to the Model and reloads the page
    @GetMapping ("/employee/viewAll")
    public String viewAll ( Model model )
    {
        List<Employee> employeeList = employeeService.fetchAll();
        return  checkList(employeeList, model);
    }

    // Adds a list of Employees to the model ( based on the queryType & querySearch ) and reloads the page
    @PostMapping ("/employee/viewAll")
    public String searchAll ( Model model, WebRequest wr )
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<Employee> employeeList;
        switch (searchType)
        {
            case "null":
                employeeList = employeeService.fetchAll();
                return checkList(employeeList, model);
            case "name":
                employeeList = employeeService.searchByName( searchQuery );
                return checkList(employeeList, model);
            case "position":
                employeeList = employeeService.searchByPosition( searchQuery );
                return checkList(employeeList, model);
            default:
                return "redirect:/employee/errorParameters";
        }
    }

    // Redirects to the editEmployee page
    @PostMapping ("/employee/editEmployee")
    public String editEmployee ( Model model, WebRequest wr )
    {
        int id = checkID("id", wr, "/employee/editEmployee");
        if (id == -1)
        {
            return "redirect:/employee/errorParameters";
        }

        Employee employee = employeeService.fetchByID( id );
        if (employee == null)
        {
            return "redirect:/employee/empty";
        }
        else
        {
            model.addAttribute("employee", employee);
            return "employee/editEmployee";
        }
    }

    // Updates the Employee information and redirects to viewAll page
    @PostMapping ("/employee/updateEmployee")
    public String updateEmployee (@ModelAttribute Employee employee)
    {
        employeeService.updateEmployee(employee);
        return "redirect:/employee/viewAll";
    }

    // Redirects to the createNewEmployee page
    @GetMapping ("/employee/createNewEmployee")
    public String createNewEmployee ()
    {
        return "employee/createNewEmployee";
    }

    // Creates a new Employee and redirects to the viewAll page
    @PostMapping ("employee/submitNewEmployee")
    public String submitNewEmployee (@ModelAttribute Employee employee)
    {
        employeeService.addEmployee(employee);
        return "redirect:/employee/viewAll";
    }

    // Deletes the Employee with the given ID and redirects to viewAll page
    @PostMapping ("employee/deleteEmployee")
    public String deleteEmployee (WebRequest wr)
    {
        int id = checkID("id", wr, "/employee/deleteEmployee");
        if (id == -1)
        {
            return "redirect:/employee/errorParameters";
        }

        employeeService.deleteEmployee(id);
        return "redirect:/employee/viewAll";
    }

    @GetMapping("/employee/errorParameters")
    public String errorParameters()
    {
        return "employee/errorParameters";
    }

    @GetMapping("/employee/empty")
    public String empty()
    {
        return "employee/empty";
    }

    // Takes in a list of Employees, adds it to the Model and displays it to the viewAll page
    public String checkList (List<Employee> employeeList, Model model )
    {
        if (employeeList.isEmpty())
        {
            return "redirect:/employee/empty";
        }
        else
        {
            model.addAttribute("employeeList", employeeList);
            return "/employee/viewAll";
        }
    }

    // Takes in the name of the ID, a the WebRequest of a specific page & a string of that mapping ( for displaying the
    // origin in case of an error )
    // Requests the ID from the page, parses it into an int and checks for exceptions
    // In case of exceptions, returns -1 & prints an error to the console
    // Otherwise, it will return an int with the value of the ID
    public int checkID (String varName, WebRequest wr, String mapping)
    {
        int x;
        try
        {
            x = Integer.parseInt(wr.getParameter(varName));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter '" + varName + "' from the model in " + mapping + " : " + e);
            return -1;
        }
        return x;
    }
}
