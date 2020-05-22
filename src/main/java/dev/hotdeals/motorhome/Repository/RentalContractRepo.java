package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalContractRepo
{

    @Autowired
    JdbcTemplate template;

    public RentalContract fetchContractByID(int contractID)
    {
        String query = "SELECT * FROM rental_contract;";
        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        RentalContract rentalContract;
        try
        {
            rentalContract = template.queryForObject(query, rw, contractID);
        }
        catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract from given ID : " + contractID + ".");
            System.out.println(e);
            return null;
        }
        return rentalContract;
    }

    public List<RentalContract> fetchAll()
    {
        String query = "SELECT * FROM rental_contract;";

        RowMapper<RentalContract> rw = new BeanPropertyRowMapper<>(RentalContract.class);
        List<RentalContract> rentalContractList;
        try
        {
            rentalContractList = template.query(query, rw);
        }
        catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the contract list.");
            System.out.println(e);
            return null;
        }
        return rentalContractList;
    }

    public boolean addContract(RentalContract rentalContract)
    {
        boolean status = false;
        return status;
    }

    public boolean updateContract(RentalContract rentalContract)
    {
        boolean status = false;
        return status;
    }

    public boolean deleteContract(int contractID)
    {
        boolean status = false;
        return status;
    }
}
