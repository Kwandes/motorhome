package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Controller.CustomerController;
import dev.hotdeals.motorhome.Controller.EmployeeController;
import dev.hotdeals.motorhome.Controller.HomeController;
import dev.hotdeals.motorhome.Controller.RvController;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MotorhomeApplicationTests
{

    @Autowired
    private HomeController homeController;

    @Test
    void contextLoads() throws Exception
    {
        assertThat(homeController).isNotNull();
    }

    @Autowired
    private RvController rvController;

    @Test
    @DisplayName("Rv Controller Loads")
    void rvContextLoads() throws Exception
    {
        assertThat(rvController).isNotNull();
    }

    @Autowired
    private CustomerController customerController;

    @Test
    @DisplayName("Customer Controller Loads")
    void customerContextLoads() throws Exception
    {
        assertThat(customerController).isNotNull();
    }

    @Autowired
    private EmployeeController employeeController;

    @Test
    @DisplayName("Employee Controller Loads")
    void employeeContextLoads() throws Exception
    {
        assertThat(employeeController).isNotNull();
    }
}
