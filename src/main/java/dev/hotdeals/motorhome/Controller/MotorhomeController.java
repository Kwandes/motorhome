/*
    Controller class for handling all Motorhome Use Case - related  web pages
 */

package dev.hotdeals.motorhome.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MotorhomeController
{
    // default mapping
    @GetMapping({"/motorhome/", "/motorhome/viewAll"})
    public String viewAll()
    {
        return "motorhome/viewAll";
    }
}
