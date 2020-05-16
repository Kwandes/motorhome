package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.RV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo
{
    @Autowired
    JdbcTemplate template;

    private Customer fetchById(int customerID)
    {
        Customer customerToReturn = null;
        return customerToReturn;
    }

    private List<Customer> fetchAll()
    {
        List<Customer> customersListToReturn = null;
        return customersListToReturn;
    }

    private boolean addCustomer(Customer customer)
    {
        boolean status = false;
        return status;
    }

    private boolean updateCustomer (Customer customer)
    {
        boolean status = false;
        return status;
    }

    private boolean deleteCustomer(int customerID)
    {
        boolean status = false;
        return status;
    }
}
