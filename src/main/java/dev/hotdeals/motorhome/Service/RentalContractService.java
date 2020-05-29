package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class RentalContractService
{

    @Autowired
    CustomerService customerService;

    // search through the provided Rental Contract list and return a list of Customers according to the list's customer_id
    public List<Customer> fetchCustomersInRC(List<RentalContract> rentalContractList)
    {
        List<Customer> customerList = customerService.fetchAll();
        List<Customer> matchingList = new ArrayList<Customer>();

        for (RentalContract rc : rentalContractList)
        {
            // iterate through the customerList and add matching Customer objects
            for (Customer customer : customerList)
            {
                if (customer.getId() == rc.getCustomer_id()) matchingList.add(customer);
            }
        }
        return matchingList;
    }

    @Autowired
    EmployeeService employeeService;

    // search through the provided Rental Contract list and return a list of Employees according to the list's employee_id
    public List<Employee> fetchEmployeesInRC(List<RentalContract> rentalContractList)
    {
        List<Employee> employeeList = employeeService.fetchAll();
        List<Employee> matchingList = new ArrayList<Employee>();

        for (RentalContract rc : rentalContractList)
        {
            // iterate through the employeeList and add matching Employee objects
            for (Employee employee : employeeList)
            {
                if (employee.getId() == rc.getEmployee_id()) matchingList.add(employee);
            }
        }
        return matchingList;
    }

    @Autowired
    RVService rvService;

    // search through the provided Rental Contract list and return a list of RVs according to the list's rv_id
    public List<RV> fetchRVsInRC(List<RentalContract> rentalContractList)
    {
        List<RV> rvList = rvService.fetchAll();
        List<RV> matchingList = new ArrayList<RV>();

        for (RentalContract rc : rentalContractList)
        {

            // iterate through the rvList and add matching RV objects
            for (RV rv : rvList)
            {
                if (rv.getId() == rc.getRv_id()) matchingList.add(rv);
            }
        }
        return matchingList;
    }

    // takes in a webrequest, reads the parameters for extras and returns them as one string
    public String extractExtrasFromModel(WebRequest wr)
    {
        String extras = "";
        try
        {
            if (wr.getParameter("extrasChildSeat").equals("on"))
                extras += "child seat,";
        } catch (NullPointerException e)
        {
            // Null Pointer Exception is expected in case of a non-checked extra
        }
        try
        {
            if (wr.getParameter("extrasBedLinen").equals("on"))
                extras += "bed linen,";
        } catch (NullPointerException e)
        {
            // Null Pointer Exception is expected in case of a non-checked extra
        }
        try
        {
            if (wr.getParameter("extrasChairs").equals("on"))
                extras += "chairs,";
        } catch (NullPointerException e)
        {
            // Null Pointer Exception is expected in case of a non-checked extra
        }
        try
        {
            if (wr.getParameter("extrasPicnicTable").equals("on"))
                extras += "picnic table,";
        } catch (NullPointerException e)
        {
            // Null Pointer Exception is expected in case of a non-checked extra
        }
        // remove an extra ',' at the end
        if (extras.length() > 0) extras = extras.substring(0, extras.length() - 1);
        return extras;
    }

    @Autowired
    RentalContractRepo rentalContractRepo;

    public RentalContract fetchByID(int contractID)
    {
        return rentalContractRepo.fetchContractByID(contractID);
    }

    public List<RentalContract> fetchAll()
    {
        return rentalContractRepo.fetchAll();
    }

    public List<RentalContract> searchByCustomerName(String customerName)
    {
        return rentalContractRepo.searchByCustomerName(customerName);
    }

    public List<RentalContract> searchByEmployeeName(String employeeName)
    {
        return rentalContractRepo.searchByEmployeeName(employeeName);
    }

    public List<RentalContract> searchByExtras(String extras)
    {
        return rentalContractRepo.searchByExtras(extras);
    }

    public List<RentalContract> searchByAddressDropoff(String dropoff)
    {
        return rentalContractRepo.searchByAddressDropoff(dropoff);
    }

    public List<RentalContract> searchByAddressPickup(String pickup)
    {
        return rentalContractRepo.searchByAddressPickup(pickup);
    }

    public List<RentalContract> sortByStatus()
    {
        return rentalContractRepo.sortByStatus();
    }

    public List<RentalContract> searchByRvModel(String rvModel)
    {
        return rentalContractRepo.searchByRvModel(rvModel);
    }

    public List<RentalContract> sortByDateStart()
    {
        return rentalContractRepo.sortByDateStart();
    }

    public List<RentalContract> sortByDateEnd()
    {
        return rentalContractRepo.sortByDateEnd();
    }

    public List<RentalContract> sortByDateSigned()
    {
        return rentalContractRepo.sortByDateSigned();
    }

    // due to complexity, this method is not tested in the Tests (extra call to the rvService)
    public boolean updateRentalContract(RentalContract rentalContract)
    {
        // recalculate the base price in case the dates have changed
        int rvPrice = rvService.fetchByID(rentalContract.getRv_id()).getPrice();
        int basePrice = calculateBasePrice(rentalContract, rvPrice);
        rentalContract.setBasePrice(basePrice);
        if (rentalContract.getStatus().equals("closed"))
        {
            float rvFuelStatus;
            try
            {
                rvFuelStatus = rvService.fetchByID(rentalContract.getRv_id()).getFuelStatus();
            } catch (NullPointerException e)
            {
                System.out.println("Failed to get an rvPrice. Setting to 0");
                rvFuelStatus = 1.0f;
            }
            int finalPrice = calculateFinalPrice(rentalContract, rvFuelStatus);
            rentalContract.setFinalPrice(finalPrice);
        } else if (rentalContract.getStatus().equals("canceled"))
        {
            int finalPrice = calculateCharge(rentalContract);
            rentalContract.setFinalPrice(finalPrice);
        }

        return rentalContractRepo.updateRentalContract(rentalContract);
    }

    public boolean deleteRentalContract(int contractID)
    {
        return rentalContractRepo.deleteRentalContract(contractID);
    }

    // due to complexity, this method is not tested in the Tests (extra call to the rvService)
    public boolean addRentalContract(RentalContract rentalContract)
    {
        int rvPrice;
        try
        {
            rvPrice = rvService.fetchByID(rentalContract.getRv_id()).getPrice();
        } catch (NullPointerException e)
        {
            System.out.println("Failed to get an rvPrice. Setting to 0");
            rvPrice = 0;
        }
        int basePrice = calculateBasePrice(rentalContract, rvPrice);

        rentalContract.setBasePrice(basePrice);

        return rentalContractRepo.addRentalContract(rentalContract);
    }

    //region Calculate Final Price
    // combines all the different calculations and returns a final price of a contract
    public int calculateFinalPrice(RentalContract rentalContract, float rvFuelStatus)
    {
        int finalPrice = 0;

        finalPrice += rentalContract.getBasePrice();
        finalPrice += calculateKmDrivenPrice(rentalContract);
        finalPrice += calculateFuelPrice(rvFuelStatus);

        return finalPrice;
    }

    // Calculates the charges that a customer has to pay when he requests to cancel the contract
    public int calculateCharge (RentalContract rentalContract)
    {
        int charge;
        int basePrice = rentalContract.getBasePrice();
        LocalDate startDate = LocalDate.parse(rentalContract.getDateStart());
        LocalDate currentDate = LocalDate.now();
        int daysDifference = (int)DAYS.between(currentDate, startDate);

        if (daysDifference >= 50)
        {
            charge = (int)(0.2 * basePrice);
            if (charge < 200) charge = 200;
        } else if (daysDifference >= 15 && daysDifference < 50)
        {
            charge = (int)(0.5 * basePrice);
        } else if (daysDifference >= 1 && daysDifference < 15)
        {
            charge = (int)(0.8 * basePrice);
        } else if (daysDifference == 0)
        {
            charge = (int)(0.95 * basePrice);
        } else
        {
            charge = -1;
        }

        return charge;
    }

    // takes in a rental contract object and returns price for fuel used. Used in final price calculation
    public int calculateFuelPrice(float fuelStatus)
    {
        int fuelPenaltyPrice = 70; //TODO - replace with a value obtained from config/DB

        if (fuelStatus < 0.5)
            return fuelPenaltyPrice;
        else
            return 0;
    }

    // returns a penalty fee for KM driven over the threshold
    public int calculateKmDrivenPrice(RentalContract rentalContract)
    {
        int averageKmDriven = rentalContract.getKmDriven() / getDateDifferenceInDays(rentalContract);
        int extraKmPenaltyPrice = 1; //TODO - obtain from database/config

        int kmDrivenPrice;
        if (averageKmDriven > 400)
        {
            kmDrivenPrice = ((averageKmDriven - 400) * getDateDifferenceInDays(rentalContract)) * extraKmPenaltyPrice;
        } else kmDrivenPrice = 0;

        return kmDrivenPrice;
    }
    //endregion

    //region Calculate Base Price
    // calculates the base price based off the rv price, extras price and the contract duration
    public int calculateBasePrice(RentalContract rentalContract, int rvPrice)
    {
        double seasonMultiplier = calculateSeasonMultiplier(rentalContract.getDateStart());

        int contractDuration = getDateDifferenceInDays(rentalContract);

        int extrasPrice = calculateExtrasPrice(rentalContract.getExtras());

        int deliveryPrice = calculateDeliveryPrice(rentalContract);

        int basePrice = (contractDuration * ( (int) (rvPrice * seasonMultiplier) + extrasPrice) ) + deliveryPrice;

        return basePrice;
    }

    // takes in a String with a list of extras and returns a flat price for them (doesn't take contract duration into account)
    public int calculateExtrasPrice(String extras)
    {
        int fixedExtrasPrice = 15; //TODO - replace with a value obtained from config/DB
        int extrasPrice = extras.split(",").length * fixedExtrasPrice;
        return extrasPrice;
    }

    // Takes in a Rental Contract and returns the Price Increase depending on the Season.
    // The Price Increase will be based only on the Start Date of the Contract
    // and will not change if the Contract streches across multiple Seasons ( with diffrent Prices ).
    // Low Season - Months : 12, 1, 2; Middle Season : 3, 4, 5, 9, 10, 11; Peak Season : 6, 7, 8
    public double calculateSeasonMultiplier(String dateStart)
    {
        int month = LocalDate.parse(dateStart).getMonthValue();

        if ( (month >= 9 && month <= 11)  || (month >= 3 && month <= 5) )
        {
            return 1.3;
        }
        else if ( month >= 6 && month <= 8 )
        {
            return 1.6;
        }
        return 1;
    }

    // takes in a contract and converts the addresses into distance, which is then compared against the delivery Fee
    public int calculateDeliveryPrice(RentalContract rentalContract)
    {
        int deliveryPrice;
        double perKmPrice = 0.7; //TODO - replace with a value obtained from config/DB

        // get distance per address
        int distanceDropoff = calculateDeliveryDistance(rentalContract.getAddressDropoff());
        int distancePickup = calculateDeliveryDistance(rentalContract.getAddressPickup());
        // calculate actual fee for the delivery distance
        deliveryPrice = (int) ((distanceDropoff + distancePickup) * perKmPrice);

        return deliveryPrice;
    }

    // takes in an address string, hashes it (in order to obtain an int value). Returns a stripped down distance value
    public int calculateDeliveryDistance(String address)
    {
        String storeAddress = "Store Address";
        if (address.equals(storeAddress)) return 0; // if the address matches the store address the distance is 0

        int distance = address.hashCode(); // obtain a replicable int from the string
        if (distance < 0) distance *= -1; // negate the value
        distance %= 1000; // limit to last 3 digits

        return distance;
    }

    // takes in a rental contract and returns the amount of days between the start and end of the contract
    public int getDateDifferenceInDays(RentalContract rentalContract)
    {
        LocalDate startDate = LocalDate.parse(rentalContract.getDateStart());
        LocalDate endDate = LocalDate.parse(rentalContract.getDateEnd());
        return (int) DAYS.between(startDate, endDate);
    }
    //endregion
}
