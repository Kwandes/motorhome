/*
    Service Layer for RV Use-Case
*/
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

    // Returns a list of all RVs in the DB
    public List<RV> fetchAll ()
    {
        return rvRepo.fetchAll();
    }

    // Returns the RV with the specified ID
    public RV fetchByID ( int recreationalVehicleID )
    {
        return rvRepo.fetchByID( recreationalVehicleID );
    }

    // Returns a list of all RVs that are available ( not rented, don't require cleaning, maintenance or further service )
    public List<RV> fetchAvailable ()
    {
        return rvRepo.fetchAvailable();
    }

    // Returns a list of all RVs that are currently rented
    public List<RV> fetchRented ()
    {
        return rvRepo.fetchRented();
    }

    // Returns a list of all RVs that require cleaning
    public List<RV> fetchRequiresCleaning ()
    {
        return rvRepo.fetchRequiresCleaning();
    }

    // Returns a list of all RVs that require maintenance
    public List<RV> fetchRequiresMaintenance ()
    {
        return rvRepo.fetchRequiresMaintenance();
    }

    // Adds the given RV Object into the DataBase
    public boolean addRV ( RV recreationalVehicle )
    {
        return rvRepo.addRV( recreationalVehicle );
    }

    // Updates the row of a specified RV with the information from the given RV Object
    public boolean updateRV ( RV recreationalVehicle )
    {
        return rvRepo.updateRV( recreationalVehicle );
    }

    // Deletes the RV with the given ID from the DataBase
    public boolean deleteRV ( int recreationalVehicleID )
    {
        return rvRepo.deleteRV( recreationalVehicleID );
    }

    // Returns a list of all RVs in the DB, sorted by price ( Descended )
    public List<RV> sortByPrice ()
    {
        return rvRepo.sortByPrice();
    }

    // Returns a list of all RVs of which model contains the given String
    public List<RV> searchByModel ( String model )
    {
        return rvRepo.searchByModel( model );
    }

    // Returns a list of all RVs of which brand contains the given String
    public List<RV> searchByBrand ( String brand )
    {
        return rvRepo.searchByBrand( brand );
    }

    // Returns a list of all RVs of which ID contains the given integer
    public List<RV> searchByID ( int recreationalVehicleID )
    {
        return rvRepo.searchByID( recreationalVehicleID );
    }
}
