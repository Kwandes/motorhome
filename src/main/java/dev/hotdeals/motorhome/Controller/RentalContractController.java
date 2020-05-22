package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class RentalContractController
{
    @Autowired
    RentalContract rentalContract;

    // Redirects to the proper viewAll mapping ( in case of typos )
    @GetMapping({"/rentalContract", "/rentalContract/", "/rentalContract/viewAll/"})
    public String redirectViewAll()
    {
        return "redirect:/rentalContract/viewAll";
    }

    // Adds a list of all the contracts to the Model and reloads the page
    @GetMapping("/rentalContract/viewAll")
    public String viewAll( Model model)
    {
        List<RentalContract> rentalContractList = rentalContract.fetchAll();
        return checkList(rentalContractList, model);
    }
    // Adds a list of contracts to the model ( based on the queryType & querySearch ) and reloads the page
    @PostMapping("/rentalContract/viewAll")
    public String searchAll(Model model, WebRequest wr)
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<RentalContract> rentalContractList;
        switch(searchType)
        {
            case "null":
                rentalContractList = rentalContractService.fetchAll();
                return checkList(rentalContractList, model);
            //Sort
            case "dateSigned":
                rentalContractList = rentalContractService.sortByDateSigned();
                return checkList(rentalContractList, model);
            case "dateStart":
                rentalContractList = rentalContractService.sortByDateStart();
                return checkList(rentalContractList,model);
            case "dateEnd":
                rentalContractList = rentalContractService.sortByDateEnd();
                return checkList(rentalContractList,model);
            case "status":
                rentalContractList = rentalContractService.sortByStatus();
                return checkList(rentalContractList,model);

            //Search
            case "dropoffAddress":
                rentalContractList = rentalContractService.searchByDropoffAddress(searchQuery);
                return checkList(rentalContractList, model);
            case "pickupAddress":
                rentalContractList = rentalContractService.searchByPickupAddress(searchQuery);
                return checkList(rentalContractList, model);
            case "extras":
                rentalContractList = rentalContractService.searchByExtras(searchQuery);
                return checkList(rentalContractList, model);
            case "customerName":
                rentalContractList = rentalContractService.searchByCustomerName(searchQuery);
                return checkList(rentalContractList, model);
            case "employeeName":
                rentalContractList = rentalContractService.searchByEmployeeName(searchQuery);
                return checkList(rentalContractList, model);
            case "rvModel":
                rentalContractList = rentalContractService.searchByRvModel(searchQuery);
                return checkList(rentalContractList, model);
            default:
                return "redirect:/rentalContract/errorParameters";
        }
    }

    // Redirects to the editRentalContract page
    @PostMapping("/rentalContract/editRentalContract")
    public String editRentalContract(Model model, WebRequest wr)
    {
        int id = checkID("id", wr, "/rentalContract/editRentalContract");
        if (id == -1)
        {
            return "redirect:/rentalContract/errorParameters";
        }
        RentalContract foundContract = rentalContractService.fetchByID(id);
        if (foundContract == null)
        {
            return "redirect:/rentalContract/empty";
        } else
        {
            model.addAttribute("rentalContract", foundContract);
            return "rentalContract/editRentalContract";
        }
    }

    // Updates the rental contract information and redirects to viewAll page
    @PostMapping("/rentalContract/updateRentalContract")
    public String updateRentalContract(@ModelAttribute RentalContract rentalContract)
    {
        rentalContractService.updateRentalContract(rentalContract);
        return "redirect:/rentalContract/viewAll";
    }

    // Redirects to the createNewRentalContract page
    @GetMapping("/rentalContract/createNewRentalContract")
    public String createNew()
    {
        return "rentalContract/createNewRentalContract";
    }

    // Creates a new rental contract and redirects to the viewAll page
    @PostMapping("/rentalContract/submitNewRentalContract")
    public String submitNewRentalContract(@ModelAttribute RentalContract rentalContract)
    {
        rentalContractService.addRentalContract(rentalContract);
        return "redirect:/rentalContract/viewAll";
    }

    // Deletes the contract with the given ID and redirects to viewAll page
    @PostMapping("/rentalContract/deleteRentalContract")
    public String deleteRentalContract(WebRequest wr)
    {
        int id = checkID("id", wr, "/rentalContract/deleteRentalContract");
        if (id == -1)
        {
            return "redirect:/rentalContract/errorParameters";
        }
        rentalContractService.deleteRentalContract(id);
        return "redirect:/rentalContract/viewAll";
    }

    @GetMapping("/rentalContract/errorParameters")
    public String errorParameters()
    {
        return "rentalContract/errorParameters";
    }

    // Shows a page used when nothing was found during the search/sort
    @GetMapping("/rentalContract/empty")
    public String empty()
    {
        return "rentalContract/empty";
    }

    // Takes in a list of Employees, adds it to the Model and displays it to the viewAll page
    public String checkList (List<RentalContract> rentalContractList, Model model )
    {
        if (rentalContractList.isEmpty())
        {
            return "redirect:/rentalContract/empty";
        }
        else
        {
            model.addAttribute("rentalContractList", rentalContractList);
            return "/rentalContract/viewAll";
        }
    }
    /*
    Takes in the name of the ID, a the WebRequest of a specific page & a string of that mapping
     ( for displaying the origin in case of an error )
    Requests the ID from the page, parses it into an int and checks for exceptions
    In case of exceptions, returns -1 & prints an error to the console
    Otherwise, it will return an int with the value of the ID
    */
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
