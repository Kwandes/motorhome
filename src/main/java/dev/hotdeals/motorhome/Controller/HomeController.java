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
}
