package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalContractService
{

    @Autowired
    CustomerService customerService;
    // search through the provided Rental Contract list and return a list of Customers according to the list's customer_id
    public List<Customer> fetchCustomersInRC (List<RentalContract> rentalContractList)
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
    public List<Employee> fetchEmployeesInRC (List<RentalContract> rentalContractList)
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
    public List<RV> fetchRVsInRC (List<RentalContract> rentalContractList)
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

    public boolean updateRentalContract(RentalContract rentalContract)
    {
        return rentalContractRepo.updateRentalContract(rentalContract);
    }

    public boolean deleteRentalContract(int contractID)
    {
        return rentalContractRepo.deleteRentalContract(contractID);
    }

    public boolean addRentalContract(RentalContract rentalContract)
    {
        return rentalContractRepo.addRentalContract(rentalContract);
    }
}
