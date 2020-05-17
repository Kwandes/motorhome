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

import java.util.List;

@Controller
public class MotorhomeController
{
    @Autowired
    RVService rvService;

    // default mapping
    @GetMapping({"/rv", "/rv/", "/rv/viewAll"})
    public String viewAll(Model model)
    {
        List<RV> rvList = rvService.fetchAll();
        if (rvList.isEmpty()) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rvList", rvList);
            return "rv/viewAll";
        }
    }

    // Used for obtaining and displaying a specific RV
    @PostMapping("/rv/fetchById")
    public String viewSpecificRV(Model model, WebRequest wr)
    {
        // try to obtain an id from the model
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rv/fetchByID: " + e);
            return "redirect:/rv/errorParameters";
        }

        RV foundRV = rvService.fetchByID(id);
        if (foundRV == null) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rv", foundRV);
            return "redirect:/rv/viewSpecificRV";
        }
    }

    @GetMapping("/rv/searchByModel")
    public String searchByModel(Model model, WebRequest wr)
    {

        List<RV> rvList = rvService.searchByModel(wr.getParameter("model"));
        return checkList(rvList, model);
    }

    @PostMapping("/rv/searchByBrand")
    public String searchByBrand(Model model, WebRequest wr)
    {
        List<RV> rvList = rvService.searchByBrand(wr.getParameter("brand"));
        return checkList(rvList, model);
    }

    @PostMapping("/rv/searchByID")
    public String searchByID(Model model, WebRequest wr)
    {
        int id;
        try
        {
            id = Integer.parseInt(wr.getParameter("id"));
        } catch (NullPointerException | NumberFormatException e)
        {
            System.out.println("Failed to get parameter 'id' from the model in /rv/searchByID: " + e);
            return "redirect:/rv/errorParameters";
        }
        List<RV> rvList = rvService.searchByID(id);
        return checkList(rvList, model);
    }

    @GetMapping("/rv/sortByPrice")
    public String sortByPrice(Model model)
    {
        List<RV> rvList = rvService.sortByPrice();
        return checkList(rvList, model);
    }

    @GetMapping("/rv/sortByAvailable")
    public String sortByAvailable(Model model)
    {
        List<RV> rvList = rvService.fetchAvailable();
        return checkList(rvList, model);
    }

    @GetMapping("/rv/requiresCleaning")
    public String requiresCleaning(Model model)
    {
        List<RV> rvList = rvService.fetchRequiresCleaning();
        return checkList(rvList, model);
    }

    @GetMapping("/rv/requiresMaintenance")
    public String requiresMaintenance(Model model)
    {
        List<RV> rvList = rvService.fetchRequiresMaintenance();
        return checkList(rvList, model);
    }

    @GetMapping("/rv/createNew")
    public String createNew()
    {
        return "rv/createNew";
    }

    @PostMapping("/rv/addNewRV")
    public String addNewRV(Model model, @ModelAttribute RV rv)
    {
        rvService.addRV(rv);
        List<RV> rvList = rvService.fetchAll();

        if (rvList.isEmpty()) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rvList", rvList);
            return "redirect:/rv/viewAll";
        }
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
            System.out.println("Failed to get parameter 'id' from the model in /rv/fetchByID: " + e);
            return "redirect:/rv/errorParameters";
        }

        rvService.deleteRV(id);
        List<RV> rvList = rvService.fetchAll();

        if (rvList.isEmpty()) return "redirect:/rv/empty";
        else
        {
            model.addAttribute("rvList", rvList);
            return "redirect:/rv/viewAll";
        }
    }

    // Takes in an RV List and depending on its contents, returns either redirect to:
    // a page with "list is empty" message, a page for viewing a specific RV or viewAll page that displays the whole list
    // Takes in a model and updates it accordingly too
    public String checkList(List<RV> rvList, Model model)
    {
        if (rvList.isEmpty()) return "redirect:/rv/empty";
        else
        {
            if (rvList.size() == 1)
            {
                model.addAttribute("rv", rvList.get(0));
                return "redirect:/rv/viewSpecificRV";
            } else
            {
                model.addAttribute("rvList", rvList);
                return "redirect:/viewAll";
            }
        }
    }
}
