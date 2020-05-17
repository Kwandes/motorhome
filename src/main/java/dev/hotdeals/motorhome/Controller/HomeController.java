package dev.hotdeals.motorhome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping({"/", "/index"})
    public String index()
    {
        return "home/index";
    }

    /*
    SALESMAN PAGE
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    */
    @GetMapping({"/salesmanMainPage"})
    public String salesmanMainPage()
    {
        return "motorhome/salesmanMainPage";
    }

    @GetMapping({"/salesmanMainPage/customers"})
    public String salesmanMainPageCustomers()
    {
        return "motorhome/customers";
    }

    @GetMapping({"/salesmanMainPage/rentals"})
    public String salesmanMainPageRentals()
    {
        return "motorhome/rentals";
    }

    @GetMapping({"/salesmanMainPage/rv"})
    public String salesmanMainPageRv()
    {
        return "motorhome/rv";
    }

    @GetMapping({"/salesmanMainPage/rvExtras"})
    public String salesmanMainPageRvExtras()
    {
        return "motorhome/rvExtras";
    }

    /*JUST FOR TESTING ###########################*/
    @GetMapping({"/salesmanMainPage/rv/viewAll"})
    public String salesmanMainPageRvViewAll()
    {
        return "motorhome/rv/viewAll";
    }
    /*JUST FOR TESTING ###########################*/

    /*
    * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
    * END SALESMAN PAGE
    */
}
