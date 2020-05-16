package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RVRepo
{
    @Autowired
    JdbcTemplate template;

    public RVRepo() {}

    public RV fetchByID(int recreationalVehicleID)
    {
        RV rvToReturn= null;
        return rvToReturn;
    }

    public List<RV> fetchAll()
    {
        List<RV> listToReturn = null;
        return listToReturn;
    }

    public boolean addRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    public boolean updateRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    public boolean deleteRV(int recreationalVehicleID)
    {
        boolean status = false;
        return status;
    }
}
