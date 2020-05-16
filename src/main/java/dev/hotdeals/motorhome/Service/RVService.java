package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Repository.RVRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RVService {

    @Autowired
    RVRepo rvRepo;

    public List<RV> fetchAll ()
    {
        return rvRepo.fetchAll();
    }

    public RV fetchByID ( int recreationalVehicleID )
    {
        return rvRepo.fetchByID( recreationalVehicleID );
    }

    public boolean addRV ( RV recreationalVehicle )
    {
        return rvRepo.addRV( recreationalVehicle );
    }

    public boolean updateRV ( RV recreationalVehicle )
    {
        return rvRepo.updateRV( recreationalVehicle );
    }

    public boolean deleteRV ( int recreationalVehicleID )
    {
        return rvRepo.deleteRV( recreationalVehicleID );
    }
}
