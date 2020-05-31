package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentalContractRepo
{

    @Autowired
    JdbcTemplate template;

    //region CRUD methods

    //Return one contract with specified ID
    public RentalContract fetchContractByID(int contractID)
    {
        String query = "SELECT * FROM rental_contract WHERE id = ?";
        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        RentalContract rentalContract;
        try
        {
            rentalContract = template.queryForObject(query, rw, contractID); //Read contract object
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract from given ID : " + contractID + ".");
            System.out.println(e);
            rentalContract = new RentalContract();
        }
        return rentalContract; //return contract object
    }

    //Return a list of all contracts in the system
    public List<RentalContract> fetchAll()
    {
        //order by active contracts
        String query = "SELECT * FROM rental_contract";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    public boolean addRentalContract(RentalContract rentalContract)
    {
        String query = "INSERT INTO rental_contract (date_start, date_end, address_dropoff, address_pickup," +
                "base_price, extras, customer_id, rv_id, employee_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rowsAffected = template.update(query, rentalContract.getDateStart(), rentalContract.getDateEnd(),
                rentalContract.getAddressDropoff(), rentalContract.getAddressPickup(), rentalContract.getBasePrice(),
                rentalContract.getExtras(), rentalContract.getCustomer_id(), rentalContract.getRv_id(), rentalContract.getEmployee_id());
        boolean status = rowsAffected > 0;
        return status;
    }

    public boolean updateRentalContract(RentalContract rentalContract)
    {
        String query = "UPDATE rental_contract SET date_signed = ?, date_start = ?, date_end = ?, " +
                "address_dropoff = ?, address_pickup = ?, base_price = ?, " +
                "final_price = ?, km_driven = ?, status = ?, extras = ?, " +
                "customer_id = ?, rv_id = ?, employee_id = ? WHERE id = ?";
        int rowsAffected = template.update(query, rentalContract.getDateSigned(), rentalContract.getDateStart(), rentalContract.getDateEnd(),
                rentalContract.getAddressDropoff(), rentalContract.getAddressPickup(), rentalContract.getBasePrice(), rentalContract.getFinalPrice(),
                rentalContract.getKmDriven(), rentalContract.getStatus(), rentalContract.getExtras(), rentalContract.getCustomer_id(),
                rentalContract.getRv_id(), rentalContract.getEmployee_id(), rentalContract.getId());
        boolean status = rowsAffected > 0;
        return status;
    }

    public boolean deleteRentalContract(int contractID)
    {
        String query = "DELETE FROM rental_contract WHERE id = ?";
        int rowsAffected = template.update(query, contractID);
        boolean status = rowsAffected > 0;
        return status;
    }
    //endregion

    //region search by

    //search by Dropoff address.
    public List<RentalContract> searchByAddressDropoff(String dropoff)
    {
        String query = "SELECT * FROM rental_contract " +
                "WHERE rental_contract.address_dropoff LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, dropoff);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //search by pickup address.
    public List<RentalContract> searchByAddressPickup(String pickup)
    {
        String query = "SELECT * FROM rental_contract " +
                "WHERE rental_contract.address_pickup LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, pickup);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //search by customer name. Joins customer and rental_contract matching Customer_id with customer.Id. No order.
    public List<RentalContract> searchByCustomerName(String customerName)
    {
        String query = "SELECT rental_contract.* FROM rental_contract " +
                "JOIN customer ON rental_contract.customer_id = customer.id " +
                "WHERE CONCAT( customer.first_name, \" \", customer.last_name ) LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, customerName);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //search by employee name. Joins customer and rental_contract matching employee_id with employee.Id. No order.
    public List<RentalContract> searchByEmployeeName(String employeeName)
    {
        String query = "SELECT rental_contract.* FROM rental_contract " +
                "JOIN employee ON rental_contract.employee_id = employee.id " +
                "WHERE CONCAT( employee.first_name, \" \", employee.last_name ) LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, employeeName);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //order by rv model
    public List<RentalContract> searchByRvModel(String rvModel)
    {
        //Using a join between rental_contract and rv
        //Rv_id and rv.id connects, then order rental_contracts by the model of the rv
        String query = "SELECT rental_contract.* FROM rental_contract " +
                "JOIN rv ON rental_contract.rv_id = rv.id " +
                "WHERE rv.model LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, rvModel);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //search by extras.
    public List<RentalContract> searchByExtras(String extras)
    {
        String query = "SELECT * FROM rental_contract " +
                "WHERE 'rental_contract.extras' LIKE CONCAT ( '%' , ? , '%' )";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw, extras);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //endregion search by

    //region order by

    //order by status so Open comes first, then Cancelled and Closed
    public List<RentalContract> sortByStatus()
    {
        //Ordering by status will show cancelled - closed - open, so im using DESC to reverse that.
        String query = "SELECT * FROM rental_contract ORDER BY status DESC";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //order by date signed
    public List<RentalContract> sortByDateSigned()
    {
        String query = "SELECT * FROM rental_contract " +
                "ORDER BY date_signed";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //order by start date
    public List<RentalContract> sortByDateStart()
    {
        String query = "SELECT * FROM rental_contract ORDER BY date_start";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //order by end date
    public List<RentalContract> sortByDateEnd()
    {
        String query = "SELECT * FROM rental_contract " +
                "ORDER BY date_end";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            rentalContractList = new ArrayList<>();
        }
        return rentalContractList;
    }

    //endregion order by
}
