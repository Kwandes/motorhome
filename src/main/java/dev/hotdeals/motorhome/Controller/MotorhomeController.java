/*
    Controller class for handling all Motorhome Use Case - related  web pages
    Handles both RV and RVExtra
 */

package dev.hotdeals.motorhome.Controller;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Service.RVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@Controller
public class MotorhomeController
{
    @Autowired
    RVService rvService;

    // default mapping, redirects to actual viewAll file
    @GetMapping({"/rv", "/rv/", "/rv/viewAll/"})
    public String redirectViewAll()
    {
        return "redirect:/rv/viewAll";
    }

    @GetMapping("/rv/viewAll")
    public String viewAll(Model model)
    {
        List<RV> rvList = rvService.fetchAll();
        return checkList(rvList, model);
    }

    @PostMapping("/rv/viewAll")
    public String searchAll(Model model, WebRequest wr)
    {
        String searchQuery = wr.getParameter("searchQuery");
        String searchType = wr.getParameter("searchType");
        List<RV> rvList;
        switch(searchType)
        {
            case "null":
                rvList = rvService.fetchAll();
                return checkList(rvList, model);
            case "brand":
                rvList = rvService.searchByBrand(searchQuery);
                return checkList(rvList, model);
            case "model":
                rvList = rvService.searchByModel(searchQuery);
                return checkList(rvList, model);
            case "price":
                rvList = rvService.sortByPrice();
                return checkList(rvList, model);
            case "available":
                rvList = rvService.fetchAvailable();
                return checkList(rvList, model);
            case "requiresCleaning":
                rvList = rvService.fetchRequiresCleaning();
                return checkList(rvList, model);
            case "requiresMaintenance":
                rvList = rvService.fetchRequiresMaintenance();
                return checkList(rvList, model);
            default:
                return "redirect:/rv/errorParameters";
        }
    }

    // Used for obtaining and displaying a specific RV
    @PostMapping("/rv/editRV")
    public String editRv(Model model, WebRequest wr)
    {
        // try to obtain an id from the model
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rv/editRV: " + e);
            return "redirect:/rv/errorParameters";
        }

        RV foundRV = rvService.fetchByID(id);
        if (foundRV == null) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rv", foundRV);
            return "rv/editRV";
        }
    }

    @PostMapping("/rv/updateRV")
    public String updateRV(Model model, @ModelAttribute RV rv)
    {
        rvService.updateRV(rv);
        List<RV> rvList = rvService.fetchAll();
        return "redirect:/rv/viewAll";
    }

    @GetMapping("/rv/createNewRV")
    public String createNew()
    {
        return "rv/createNewRV";
    }

    @PostMapping("/rv/submitNewRV")
    public String submitNewRV(Model pageModel, @ModelAttribute RV rv) // Used to be :  WebRequest wr
    {
        rvService.addRV(rv);
        return "redirect:/rv/viewAll";
    }

    @PostMapping("/rv/deleteRV")
    public String deleteRV(Model model, WebRequest wr)
    {
        // try to obtain an id from the model
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rv/deleteRV: " + e);
            return "redirect:/rv/errorParameters";
        }

        rvService.deleteRV(id);
        return "redirect:/rv/viewAll";
    }

    @GetMapping("/rv/errorParameters")
    public String errorParameters()
    {
        return "rv/errorParameters";
    }

    @GetMapping("/rv/empty")
    public String empty()
    {
        return "rv/empty";
    }

    // Takes in an RV List and depending on its contents, returns either redirect to:
    // a page with "list is empty" message, a page for viewing a specific RV or viewAll page that displays the whole list
    // Takes in a model and updates it accordingly too
    public String checkList(List<RV> rvList, Model model)
    {
        if (rvList.isEmpty()) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rvList", rvList);
            return "rv/viewAll";
        }
    }
}
