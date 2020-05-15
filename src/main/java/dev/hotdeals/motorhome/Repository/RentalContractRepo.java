package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalContractRepo
{

    @Autowired
    JdbcTemplate template;

    private static RentalContract fetchContractByID(int contractID)
    {
        RentalContract contractToReturn = new RentalContract();
        return contractToReturn;
    }

    private static List<RentalContract> fetchAll()
    {
        List<RentalContract> listToReturn = null;
        return listToReturn;
    }

    private static boolean addContract(RentalContract rentalContract)
    {
        boolean status = false;
        return status;
    }

    private static boolean updateContract(RentalContract rentalContract)
    {
        boolean status = false;
        return status;
    }

    private static boolean deleteContract(int contractID)
    {
        boolean status = false;
        return status;
    }
}
