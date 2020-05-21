package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    // Returns a list of all Customers in the DB
    public List<Customer> fetchAll ()
    {
        return customerRepo.fetchAll();
    }

    // Returns a Customer with the provided ID
    public Customer fetchByID ( int customerID )
    {
        return customerRepo.fetchByID( customerID );
    }

    // Returns a list of all Customers of which CPR contains the String provided
    public List<Customer> searchByCpr( String cpr )
    {
        return customerRepo.searchByCpr( cpr );
    }

    // Returns a list of all Customers of which Name( First Name & Last Name )
    // contains the String provided
    public List<Customer> searchByName( String name )
    {
        return customerRepo.searchByName ( name );
    }

    // Adds a Customers to the DB
    public boolean addCustomer ( Customer customer )
    {
        return customerRepo.addCustomer( customer );
    }

    // Updates the row of a specified Customer with the information from the given Customer Object
    public boolean updateCustomer ( Customer customer )
    {
        return customerRepo.updateCustomer( customer );
    }

    // Deletes the Customer with the given ID from the DataBase
    public boolean deleteCustomer ( int customerID )
    {
        return customerRepo.deleteCustomer( customerID );
    }
}
