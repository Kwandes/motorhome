package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class ReportController
{
    @GetMapping({"/report", "/report/"})
    public String defaultMapping()
    {
        return "redirect:/report/viewReport";
    }

    @Autowired
    ReportService reportService;

    @GetMapping("/report/viewReport")
    public String viewReport(Model model)
    {
        model.addAttribute("contractStats", reportService.fetchContractStats());
        model.addAttribute("employeeStats", reportService.fetchEmployeeStats());
        model.addAttribute("customerStats", reportService.fetchCustomerStats());
        model.addAttribute("rvStats", reportService.fetchRvStats());
        return "report/viewReport";
    }

    @PostMapping("/report/viewReport")
    public String viewReport(Model model, WebRequest wr)
    {
        model.addAttribute("contractStats", reportService.fetchContractStats());
        model.addAttribute("employeeStats", reportService.fetchEmployeeStats());
        model.addAttribute("customerStats", reportService.fetchCustomerStats());
        model.addAttribute("rvStats", reportService.fetchRvStats());
        return "report/viewReport";
    }

    //region Information pages

    @Autowired
    RentalContractService rentalContractService;
    @Autowired
    CustomerService customerService;
    @Autowired
    RVService rvService;
    @Autowired
    EmployeeService employeeService;


    // Adds a list of contracts to the model ( based on the queryType & querySearch ) and reloads the page
    @PostMapping("/report/viewContract")
    public String searchAllContract(Model model, WebRequest wr)
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");

        List<RentalContract> rentalContractList;
        switch(searchType)
        {
            case "null":
                rentalContractList = rentalContractService.fetchAll();
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            //Sort
            case "dateSigned":
                rentalContractList = rentalContractService.sortByDateSigned();
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "dateStart":
                rentalContractList = rentalContractService.sortByDateStart();
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList,model);
            case "dateEnd":
                rentalContractList = rentalContractService.sortByDateEnd();
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList,model);
            case "status":
                rentalContractList = rentalContractService.sortByStatus();
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList,model);

            //Search
            case "dropoffAddress":
                rentalContractList = rentalContractService.searchByAddressDropoff(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "pickupAddress":
                rentalContractList = rentalContractService.searchByAddressPickup(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "extras":
                rentalContractList = rentalContractService.searchByExtras(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "customerName":
                rentalContractList = rentalContractService.searchByCustomerName(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "employeeName":
                rentalContractList = rentalContractService.searchByEmployeeName(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            case "rvModel":
                rentalContractList = rentalContractService.searchByRvModel(searchQuery);
                setupRentalContractLists(model, rentalContractList);
                return checkListContract(rentalContractList, model);
            default:
                return "redirect:/report/errorParameters";
        }
    }

    // Adds a list of Employees to the model ( based on the queryType & querySearch ) and reloads the page
    @PostMapping ("/report/viewEmployee")
    public String searchAllEmployee ( Model model, WebRequest wr )
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<Employee> employeeList;
        switch (searchType)
        {
            case "null":
                employeeList = employeeService.fetchAll();
                return checkListEmployee(employeeList, model);
            case "name":
                employeeList = employeeService.searchByName( searchQuery );
                return checkListEmployee(employeeList, model);
            case "position":
                employeeList = employeeService.searchByPosition( searchQuery );
                return checkListEmployee(employeeList, model);
            default:
                return "redirect:/report/errorParameters";
        }
    }

    @PostMapping("/report/viewCustomer")
    public String searchAllCustomer ( Model model, WebRequest wr )
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<Customer> customerList;
        switch (searchType)
        {
            case "null":
                customerList = customerService.fetchAll();
                return checkListCustomer(customerList, model);
            case "name":
                customerList = customerService.searchByName(searchQuery);
                return checkListCustomer(customerList, model);
            case "cpr":
                customerList = customerService.searchByCpr(searchQuery);
                return checkListCustomer(customerList, model);
            default:
                return "redirect:/report/errorParameters";
        }
    }

    @PostMapping("/report/viewRv")
    public String searchAllRv(Model model, WebRequest wr)
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<RV> rvList;
        switch(searchType)
        {
            case "null":
                rvList = rvService.fetchAll();
                return checkListRv(rvList, model);
            case "brand":
                rvList = rvService.searchByBrand(searchQuery);
                return checkListRv(rvList, model);
            case "model":
                rvList = rvService.searchByModel(searchQuery);
                return checkListRv(rvList, model);
            case "price":
                rvList = rvService.sortByPrice();
                return checkListRv(rvList, model);
            case "available":
                rvList = rvService.fetchAvailable();
                return checkListRv(rvList, model);
            case "requiresCleaning":
                rvList = rvService.fetchRequiresCleaning();
                return checkListRv(rvList, model);
            case "requiresMaintenance":
                rvList = rvService.fetchRequiresMaintenance();
                return checkListRv(rvList, model);
            default:
                return "redirect:/report/errorParameters";
        }
    }

    //endregion

    //region Navigation
    // Adds a list of all the contracts to the Model and reloads the page
    @GetMapping("report/viewContract")
    public String viewContract(Model model)
    {
        List<RentalContract> rentalContractList = rentalContractService.fetchAll();

        setupRentalContractLists(model, rentalContractList);

        return checkListContract(rentalContractList, model);
    }

    // Adds a list of all the Employees to the Model and reloads the page
    @GetMapping ("/report/viewEmployee")
    public String viewEmployee ( Model model )
    {
        List<Employee> employeeList = employeeService.fetchAll();
        return  checkListEmployee(employeeList, model);
    }

    @GetMapping("/report/viewCustomer")
    public String viewCustomer ( Model model )
    {
        List<Customer> customerList = customerService.fetchAll();
        return checkListCustomer(customerList, model);
    }

    @GetMapping("/report/viewRv")
    public String viewRv(Model model)
    {
        List<RV> rvList = rvService.fetchAll();
        return checkListRv(rvList, model);
    }
    //endregion

    //region Controller methods

    @GetMapping("/report/errorParameters")
    public String errorParameters()
    {
        return "report/errorParameters";
    }

    // Shows a page used when nothing was found during the search/sort
    @GetMapping("/report/empty")
    public String empty()
    {
        return "report/empty";
    }

    //region List checking methods
    // Takes in a list of Rental Contracts, adds it to the Model and displays it to the viewContract page
    public String checkListContract(List<RentalContract> list, Model model )
    {
        if (list.isEmpty())
        {
            return "redirect:/report/empty";
        }
        else
        {
            model.addAttribute("rentalContractList", list);
            return "report/viewContract";
        }
    }
    // Takes in a list of Employees, adds it to the Model and displays it to the viewEmployee page
    public String checkListEmployee(List<Employee> list, Model model )
    {
        if (list.isEmpty())
        {
            return "redirect:/report/empty";
        }
        else
        {
            model.addAttribute("employeeList", list);
            return "report/viewEmployee";
        }
    }

    // Takes in a list of Customers, adds it to the Model and displays it to the viewCustomer page
    public String checkListCustomer(List<Customer> list, Model model )
    {
        if (list.isEmpty())
        {
            return "redirect:/report/empty";
        }
        else
        {
            model.addAttribute("customerList", list);
            return "report/viewCustomer";
        }
    }

    // Takes in a list of RVs, adds it to the Model and displays it to the viewRV page
    public String checkListRv(List<RV> list, Model model )
    {
        if (list.isEmpty())
        {
            return "redirect:/report/empty";
        }
        else
        {
            model.addAttribute("rvList", list);
            return "report/viewRv";
        }
    }

    //endregion

    // adds the Customer, Employee and RV lists to the model based off the IDs found in the Rental Contract
    public void setupRentalContractLists(Model model, List<RentalContract> rentalContractList)
    {
        model.addAttribute("customerList", rentalContractService.fetchCustomersInRC(rentalContractList));
        model.addAttribute("employeeList", rentalContractService.fetchEmployeesInRC(rentalContractList));
        model.addAttribute("rvList", rentalContractService.fetchRVsInRC(rentalContractList));
    }
    //endregion
}
