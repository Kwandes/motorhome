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

    public Customer fetchById(int customerID)
    {
        Customer customerToReturn = null;
        return customerToReturn;
    }

    public List<Customer> fetchAll()
    {
        List<Customer> customersListToReturn = null;
        return customersListToReturn;
    }

    public boolean addCustomer(Customer customer)
    {
        boolean status = false;
        return status;
    }

    public boolean updateCustomer (Customer customer)
    {
        boolean status = false;
        return status;
    }

    public boolean deleteCustomer(int customerID)
    {
        boolean status = false;
        return status;
    }
}
