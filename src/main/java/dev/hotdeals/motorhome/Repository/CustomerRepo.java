package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.RV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepo
{
    @Autowired
    JdbcTemplate template;

    public Customer fetchById(int customerID)
    {
        String query = "SELECT * FROM customer WHERE ID = ?;";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        Customer customerToReturn;
        try
        {
            customerToReturn = template.queryForObject(query, rowMapper, customerID);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve customer of ID " + customerID + ".");
            System.out.println(e);
            customerToReturn = null;
        }
        return customerToReturn;
    }

    public List<Customer> fetchAll()
    {
        String query = "SELECT * FROM customer;";
        RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);
        List<Customer> customerListToReturn;
        try
        {
            customerListToReturn = template.query(query, customerRowMapper);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("No customers have been retrieved from the database");
            System.out.println(e);
            customerListToReturn = new ArrayList<Customer>();
        }
        return customerListToReturn;
    }

    public boolean addCustomer(Customer customer)
    {
        String query = "INSERT INTO customer (first_name, last_name, cpr, phone_number, email, birthdate, address, address2, gender)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        boolean status = false;
        status = template.update(query, customer.getFirstName(),customer.getLastName(),
                customer.getCpr(),customer.getPhoneNumber(),customer.getEmail(),customer.getBirthdate(),
                customer.getAddress(),customer.getAddress2(),customer.getGender()) > 0;
        return status; //TODO - NOT IS WORKING
    }

    public boolean updateCustomer (Customer customer)
    {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, cpr = ?, phone_number = ?," +
                " email = ?, birthdate = ?, address = ?, address2 = ?, gender = ? WHERE id = ?;";
        boolean status = template.update(query, customer.getFirstName(),customer.getLastName(),
                customer.getCpr(),customer.getPhoneNumber(),customer.getEmail(),customer.getBirthdate(),
                customer.getAddress(),customer.getAddress2(),customer.getGender(), customer.getId()) > 0;
        return status;
    }

    public boolean deleteCustomer(int customerID)
    {
        String query = "DELETE FROM customer WHERE id = ?;";
        boolean customerDeleteStatus = template.update(query, customerID) > 0;
        return customerDeleteStatus;
    }


    public List<Customer> searchByCpr(String cpr)
    {
        String query = "SELECT * FROM customer WHERE cpr LIKE CONCAT ('%', ? ,'%')";
        RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);
        List<Customer> customerList;
        try
        {
            customerList = template.query(query, customerRowMapper, cpr);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("No customer has been retrieved from the database with ID " + cpr + ".");
            System.out.println(e);
            customerList =new ArrayList<Customer>();
        }
        return customerList;
    }

    public List<Customer> searchByName(String name)
    {
        String query = "SELECT * FROM customer WHERE CONCAT (first_name,\" \", last_name) LIKE CONCAT ('%', ? ,'%');";
        RowMapper<Customer> customerRowMapper = new BeanPropertyRowMapper<>(Customer.class);
        List<Customer> customerListToReturn;
        try
        {
            customerListToReturn = template.query(query, customerRowMapper, name);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("No customers have been retrieved from the database");
            System.out.println(e);
            customerListToReturn = new ArrayList<Customer>();
        }
        return customerListToReturn;
    }
}
