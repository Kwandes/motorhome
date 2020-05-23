package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalContractService {

    @Autowired
    RentalContractRepo rentalContractRepo;

    public RentalContract fetchByID ( int contractID ) { return rentalContractRepo.fetchContractByID( contractID ); }

    public List<RentalContract> fetchAll () { return rentalContractRepo.fetchAll(); }

    public List<RentalContract> searchByCustomerName ( String customerName ) { return rentalContractRepo.searchByCustomerName( customerName ); }

    public List<RentalContract> searchByEmployeeName ( String employeeName ) { return rentalContractRepo.searchByEmployeeName( employeeName ); }

    public List<RentalContract> searchByExtras ( String extras ) { return rentalContractRepo.searchByExtras( extras ); }

    public List<RentalContract> searchByAddressDropoff ( String dropoff ) { return rentalContractRepo.searchByAddressDropoff( dropoff ); }

    public List<RentalContract> searchByAddressPickup ( String pickup ) { return rentalContractRepo.searchByAddressPickup( pickup ); }

    public List<RentalContract> sortByStatus () { return rentalContractRepo.sortByStatus(); }

    public List<RentalContract> searchByRvModel ( String rvModel ) { return rentalContractRepo.searchByRvModel( rvModel ); }

    public List<RentalContract> sortByDateStart () { return rentalContractRepo.sortByDateStart(); }

    public List<RentalContract> sortByDateEnd () { return rentalContractRepo.sortByDateEnd(); }

    public List<RentalContract> sortByDateSigned () { return rentalContractRepo.sortByDateSigned(); }

    public boolean updateRentalContract ( RentalContract rentalContract ) { return rentalContractRepo.updateRentalContract( rentalContract ); }

    public boolean deleteRentalContract ( int contractID ) { return rentalContractRepo.deleteRentalContract( contractID ); }

    public boolean addRentalContract ( RentalContract rentalContract ) { return rentalContractRepo.addRentalContract( rentalContract ); }
}
