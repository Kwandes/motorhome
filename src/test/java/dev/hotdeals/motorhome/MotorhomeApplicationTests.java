package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Controller.HomeController;
import dev.hotdeals.motorhome.Controller.MotorhomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MotorhomeApplicationTests
{

    @Autowired
    private HomeController controller;

    @Test
    void contextLoads() throws Exception
    {
        assertThat(controller).isNotNull();
    }


    @Autowired
    private MotorhomeController motorhomeController;

    @Test
    void motorhomeContextLoads() throws Exception
    {
        assertThat(motorhomeController).isNotNull();
    }


}
