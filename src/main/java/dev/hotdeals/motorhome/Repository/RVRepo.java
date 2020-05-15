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

    private List<RV> fetchAll()
    {
        List<RV> listToReturn = null;
        return listToReturn;
    }

    private boolean addRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    private boolean updateRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    private boolean deleteRV(int recreationalVehicleID)
    {
        boolean status = false;
        return status;
    }
}
