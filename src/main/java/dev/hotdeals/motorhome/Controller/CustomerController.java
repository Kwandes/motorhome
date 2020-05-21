package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.constraints.Null;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping({"/customer", "/customer/", "/customer/viewAll/"})
    public String redirectViewAll()
    {
        return "redirect:/customer/viewAll";
    }

    @GetMapping("/customer/viewAll")
    public String viewAll ( Model model )
    {
        List<Customer> customerList = customerService.fetchAll();
        return checkList(customerList, model);
    }

    @PostMapping("/customer/viewAll")
    public String searchAll ( Model model, WebRequest wr )
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<Customer> customerList;
        switch (searchType)
        {
            case "null":
                customerList = customerService.fetchAll();
                return checkList(customerList, model);
            case "name":
                customerList = customerService.searchByName(searchQuery);
                return checkList(customerList, model);
            case "cpr":
                customerList = customerService.searchByCpr(searchQuery);
                return checkList(customerList, model);
            default:
                return "redirect:/customer/errorParameters";
        }
    }

    @PostMapping("/customer/editCustomer")
    public String editCustomer ( Model model, WebRequest wr )
    {
        int id = checkID("id", wr, "/customer/editCustomer");
        if ( id == -1 )
        {
            return "redirect:/customer/errorParameters";
        }

        Customer customer = customerService.fetchByID( id );
        if (customer == null)
        {
            return "redirect:/customer/empty";
        }
        else
        {
            model.addAttribute("customer", customer);
            return "customer/editCustomer";
        }
    }

    @PostMapping("/customer/updateCustomer")
    public String updateCustomer ( @ModelAttribute Customer customer )
    {
        customerService.updateCustomer(customer);
        return "redirect:/customer/viewAll";
    }

    @GetMapping("/customer/createNewCustomer")
    public String createNewCustomer()
    {
        return "customer/createNewCustomer";
    }

    @PostMapping("/customer/submitNewCustomer")
    public String submitNewCustomer( @ModelAttribute Customer customer )
    {
        customerService.addCustomer(customer);
        return "redirect:/customer/viewAll";
    }

    @PostMapping("/customer/deleteCustomer")
    public String deleteCustomer ( WebRequest wr )
    {
        int id = checkID("id", wr, "/customer/deleteCustomer");
        if (id == -1)
        {
            return "redirect:/customer/errorParameters";
        }

        customerService.deleteCustomer( id );
        return "redirect:/customer/viewAll";
    }

    @GetMapping("/customer/errorParameters")
    public String errorParameters()
    {
        return "customer/errorParameters";
    }

    @GetMapping("/customer/empty")
    public String empty()
    {
        return "customer/empty";
    }

    public String checkList ( List<Customer> customerList, Model model )
    {
        if (customerList.isEmpty())
        {
            return "redirect:/customer/empty";
        }
        else
        {
            model.addAttribute("customerList", customerList);
            return "/customer/viewAll";
        }
    }

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
