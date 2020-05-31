package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.User;
import dev.hotdeals.motorhome.Service.EmployeeService;
import dev.hotdeals.motorhome.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController
{

    @Autowired
    UserService userService;
    @Autowired
    EmployeeService employeeService;
    /*
    LOGIN DIRECTORY (HOME)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @GetMapping({"/", "/index"})
    public String index()
    {
        return "home/index";
    }

    @PostMapping("/home/login")
    public String login(WebRequest wr, Model model)
    {
        // Attempts to retrieve the User with the provided Username.
        User user = userService.fetchByUsername(wr.getParameter("username"));
        int error;

        // If the User could not be found or the User's Password did not match the provided Password
        // the Login page is being reloaded and the error is being displayed.
        if (user == null || !user.getPassword().equals(wr.getParameter("password")))
        {
            error = 1; // Wrong Username or Password. Try Again !
            model.addAttribute("error", error);
            return "/home/index";
        }
        // If the Username and Password matched the User's inputs
        // the user will be redirected to the corresponding webpage
        switch (employeeService.fetchByID(user.getEmployee_id()).getPosition())
        {
            case "Bookkeeper":
                System.out.println("User : " + user.getUsername() + " has logged in.");
                return "redirect:/report";
            case "Auto-mechanic":
                System.out.println("User : " + user.getUsername() + " has logged in.");
                return "redirect:/rv/maintenance";
            case "Owner":
                System.out.println("User : " + user.getUsername() + " has logged in.");
                return "redirect:/employee/viewAll";
            case "Sales Assistant":
                System.out.println("User : " + user.getUsername() + " has logged in.");
                return "redirect:/rv/viewAll";
            default:
                return "redirect:/";
        }
    }
}
