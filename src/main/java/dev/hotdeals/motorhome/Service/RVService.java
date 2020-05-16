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

    public List<RV> fetchAvailable ()
    {
        return rvRepo.fetchAvailable();
    }

    public List<RV> fetchRented ()
    {
        return rvRepo.fetchRented();
    }

    public List<RV> fetchRequiresCleaning ()
    {
        return rvRepo.fetchRequiresCleaning();
    }

    public List<RV> fetchRequiresMaintenance ()
    {
        return rvRepo.fetchRequiresMaintenance();
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

    public List<RV> sortByPrice ()
    {
        return rvRepo.sortByPrice();
    }

    public List<RV> searchByModel ( String model )
    {
        return rvRepo.searchByModel( model );
    }

    public List<RV> searchByBrand ( String brand )
    {
        return rvRepo.searchByBrand( brand );
    }

    public List<RV> searchByID ( int recreationalVehicleID )
    {
        return rvRepo.searchByID( recreationalVehicleID );
    }
}
