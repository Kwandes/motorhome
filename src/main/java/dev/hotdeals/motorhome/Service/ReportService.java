package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService
{
    @Autowired
    RentalContractService rcService;

    // return a list with a number of different types of a contract (open, closed, canceled)
    public Map<String, Integer> fetchContractStats()
    {
        Map<String, Integer> statList = new HashMap<String, Integer>();
        int openContracts = 0;
        int closedContracts = 0;
        int canceledContracts = 0;
        for (RentalContract rc : rcService.fetchAll())
        {
            switch (rc.getStatus())
            {
                case "closed":
                    closedContracts++;
                    break;
                case "open":
                    openContracts++;
                    break;
                case "canceled":
                    canceledContracts++;
                    break;
            }
        }
        statList.put("open", openContracts);
        statList.put("closed", closedContracts);
        statList.put("canceled", canceledContracts);

        return statList;
    }

    @Autowired
    EmployeeService employeeService;

    // return a list with a number of different types of a contract (open, closed, canceled)
    public Map<String, Integer> fetchEmployeeStats()
    {
        Map<String, Integer> statList = new HashMap<String, Integer>();
        int owners = 0;
        int bookkepers = 0;
        int salesAssistants = 0;
        int mechanics = 0;
        int cleaners = 0;
        for (Employee emp : employeeService.fetchAll())
        {
            switch (emp.getPosition())
            {
                case "Owner":
                    owners++;
                    break;
                case "Bookkeeper":
                    bookkepers++;
                    break;
                case "Sales Assistant":
                    salesAssistants++;
                    break;
                case "Auto-mechanic":
                    mechanics++;
                    break;
                case "Cleaning Staff":
                    cleaners++;
                    break;
            }
        }
        statList.put("owners", owners);
        statList.put("bookkeepers", bookkepers);
        statList.put("salesAssistants", salesAssistants);
        statList.put("mechanics", mechanics);
        statList.put("cleaners", cleaners);

        return statList;
    }

    @Autowired
    CustomerService customerService;

    // return a list with a number of different types of a contract (open, closed, canceled)
    public Map<String, Integer> fetchCustomerStats()
    {
        Map<String, Integer> statList = new HashMap<String, Integer>();
        int activeCustomers = 0;
        int inactiveCustomers = 0;

        List<RentalContract> rcList = new ArrayList<RentalContract>();

        // iterate through the rental contract list and extract all active (open) contracts
        for (RentalContract rc : rcService.fetchAll())
        {
            if (rc.getStatus().equals("open")) rcList.add(rc);
        }

        Set<String> customerSet = new HashSet<String>();
        List<Customer> customerList = customerService.fetchAll();
        // iterate through extracted contracts and count how many costumers appear (count is per unique customer)
        // When a customer is found, their unique ID is added to a set.
        // We don't need to see how many contracts a given customer had so we only count unique customer
        for (RentalContract rc : rcList)
        {
            for (Customer customer : customerList)
            {
                if (customer.getId() == rc.getCustomer_id())
                {
                    customerSet.add(String.valueOf(customer.getId()));
                }
            }
        }
        activeCustomers = customerSet.size();
        inactiveCustomers = customerService.fetchAll().size() - activeCustomers;
        statList.put("active", activeCustomers);
        statList.put("inactive", inactiveCustomers);

        return statList;
    }

    @Autowired
    RVService rvService;

    // return a list with a number of different types of a contract (open, closed, canceled)
    public Map<String, Integer> fetchRvStats()
    {
        Map<String, Integer> statList = new HashMap<String, Integer>();
        // initialize to 0 in case the fetch fails completely
        int rented = 0;
        int requireAttention = 0;
        int available = 0;

        //
        rented = rvService.fetchRented().size();
        available = rvService.fetchAvailable().size();
        requireAttention = rvService.fetchRequiresCleaning().size() + rvService.fetchRequiresMaintenance().size();

        statList.put("rented", rented);
        statList.put("requireAttention", requireAttention);
        statList.put("available", available);

        return statList;
    }
}
