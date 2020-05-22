package dev.hotdeals.motorhome.Controller;

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

    /*
    DEFAULT: View All

    CREATE NEW: newRentalContract
    --> contract info : (-dateSigned-, dateStart, dateEnd, addressDropoff, addressPickup,
    status(-open-, -closed-, -cancelled-), extras(WIP), cutomerID(via select(customerId)) rvID(via select(id),
    employeeID(via select(employeeId)))

    --> clicks create button

    SEARCH: search type 	(dropoffAddress, searchPickupAddress, extras, customerName, employeeName, rvModel)
            sort type	(dateSigned, dateStart, dateEnd, status)
    --> search query 	(???)
    --> displays view all page with rental contract data filtered in correct order/type

    ERROR:	display "EMPTY" page
     */

    // default mapping, redirects to actual viewAll file
    @GetMapping({"/rentalContract", "/rentalContract/", "/rentalContract/viewAll/"})
    public String redirectViewAll()
    {
        return "redirect:/rentalContract/viewAll";
    }

    @GetMapping("/rentalContract/viewAll")
    public String viewAll(Model model)
    {
        List<RentalContract> rentalContractList = rentalContract.fetchAll();
        return checkList(rentalContractList, model);
    }

    @PostMapping("/rentalContract/viewAll")  // TODO - Mostly Done
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

    // Used for obtaining and displaying a specific RentalContract
    @PostMapping("/rentalContract/editRentalContract")
    public String editRentalContract(Model model, WebRequest wr)
    {
        // try to obtain an id from the model
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rentalContract/editRentalContract: " + e);
            return "redirect:/rentalContract/errorParameters";
        }

        RentalContract foundContract = rentalContractService.fetchByID(id);
        if (foundContract == null) return "redirect:/rentalContract/empty";
        else
        {
            model.addAttribute("contract", foundContract);
            return "rentalContract/editContract";
        }
    }

    @PostMapping("/rentalContract/updateRentalContractV")
    public String updateRentalContract(Model model, @ModelAttribute RentalContract rentalContract)
    {
        rentalContractService.updateRentalContract(rentalContract);
        List<RentalContract> rentalContractvList = rentalContractService.fetchAll();
        return "redirect:/rentalContract/viewAll";
    }

    @GetMapping("/rentalContract/createNewRentalContract")
    public String createNew()
    {
        return "rentalContract/createNewRentalContract";
    }

    @PostMapping("/rentalContract/submitNewRentalContract")
    public String submitNewRentalContract(Model pageModel, @ModelAttribute RentalContract rentalContract) // Used to be :  WebRequest wr
    {
        rentalContractService.addRentalContract(rentalContract);
        return "redirect:/rentalContract/viewAll";
    }

    @PostMapping("/rentalContract/deleteRentalContract")
    public String deleteRentalContract(Model model, WebRequest wr)
    {
        // try to obtain an id from the model
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rentalContract/deleteRentalContract: " + e);
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

    @GetMapping("/rentalContract/empty")
    public String empty()
    {
        return "rentalContract/empty";
    }

    // Takes in an RentalContract List and depending on its contents, returns either redirect to:
    // a page with "list is empty" message, a page for viewing a specific RentalContract or viewAll page that displays the whole list
    // Takes in a model and updates it accordingly too
    public String checkList(List<RentalContract> rentalContractList, Model model)
    {
        if (rentalContractList.isEmpty()) return "redirect:/rentalContract/empty";
        else
        {
            model.addAttribute("rentalContractList", rentalContractList);
            return "rentalContract/viewAll";
        }
    }
}
