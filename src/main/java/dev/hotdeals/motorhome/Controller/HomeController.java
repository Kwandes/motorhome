package dev.hotdeals.motorhome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    /*
    LOGIN DIRECTORY (HOME)
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @GetMapping({"/", "/index"})
    public String index()
    {
        return "home/index";
    }

    @GetMapping({"/salesmanMainPage"})
    public String salesmanMainPage()
    {
        return "home/salesmanMainPage";
    }
    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * END LOGIN PAGE
     */

    /*
    SALESMAN PAGE
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    //Placeholder html page (Should be viewAll)
    @GetMapping({"/salesmanMainPage/customers"})
    public String salesmanMainPageCustomers()
    {
        return "salesman/customers/customers";
    }

    //Placeholder html page (Should be viewAll)
    @GetMapping({"/salesmanMainPage/rentals"})
    public String salesmanMainPageRentals()
    {
        return "salesman/rentals/rentals";
    }

    //Placeholder html page (Should be viewAll)
    @GetMapping({"/salesmanMainPage/rvExtras"})
    public String salesmanMainPageRvExtras()
    {
        return "salesman/rvExtras/rvExtras";
    }
    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * END SALESMAN PAGE
     */

    //should just be moved to motorhomeController / rvController
    /*JUST FOR TESTING ###########################*/
    @GetMapping({"/salesmanMainPage/rv/viewAll"})
    public String rvViewAll()
    {
        return "salesman/rv/viewAll";
    }

    @GetMapping({"/salesmanMainPage/rv/createNew"})
    public String rvCreateNew()
    {
        return "salesman/rv/createNew";
    }

    @GetMapping({"/salesmanMainPage/rv/searchByModel"})
    public String rvSearchByModel()
    {
        return "salesman/rv/searchByModel";
    }

    @GetMapping({"/salesmanMainPage/rv/searchById"})
    public String rvSearchById()
    {
        return "salesman/rv/searchById";
    }

    @GetMapping({"/salesmanMainPage/rv/searchByType"})
    public String rvSearchByType()
    {
        return "salesman/rv/searchByType";
    }

    @GetMapping({"/salesmanMainPage/rv/sortByPrice"})
    public String rvSortByPrice()
    {
        return "salesman/rv/sortByPrice";
    }

    @GetMapping({"/salesmanMainPage/rv/sortByAvailable"})
    public String rvSortByAvailable()
    {
        return "salesman/rv/sortByAvailable";
    }

    @GetMapping({"/salesmanMainPage/rv/requiresCleaning"})
    public String rvRequiresCleaning()
    {
        return "salesman/rv/requiresCleaning";
    }

    @GetMapping({"/salesmanMainPage/rv/requiresMaintenance"})
    public String rvRequiresMaintenance()
    {
        return "salesman/rv/requiresMaintenance";
    }
    /*JUST FOR TESTING ###########################*/
}
