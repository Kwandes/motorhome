package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import dev.hotdeals.motorhome.Service.CustomerService;
import dev.hotdeals.motorhome.Service.EmployeeService;
import dev.hotdeals.motorhome.Service.RVService;
import dev.hotdeals.motorhome.Service.RentalContractService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RentalContractTests
{

    @Autowired
    RentalContractRepo rentalContractRepo;

    public static boolean testVerification; // holds status of the previous test in the series

    //region RentalContract Repo
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class RentalContractRepoTests
    {
        @Test
        @DisplayName("Repository Loading Validation")
        public void rentalContractRepoLoadsTest()
        {
            // validate if the Rv Repository layer has loaded
            assertThat(rentalContractRepo).isNotNull();
        }

        @Test
        @DisplayName("searchByAddressDropoff()")
        @Order(1)
        public void rentalContractRepoSearchByAddressDropoffTest() throws Exception
        {
            // searchByBrand is run as the first test as the add, update and delete tests depend on it
            System.out.println("RentalContract Repo ordered tests of adding, updating and deleting data");
            System.out.println("RentalContract Repo - Test 1 : searchByAddressDropoff");

            List<RentalContract> rentalContractList = rentalContractRepo.searchByAddressDropoff("");

            testVerification = false; // if the list is empty, testVerification will be false ( test has failed ) else, it will be true

            assertThat(rentalContractList).isNotEmpty();
            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("addRentalContract()")
        @Order(2)
        public void rentalContractRepoAddRentalContractTest() throws Exception
        {
            System.out.println("RentalContract Repo - Test 2 : Add RentalContract Test");

            if (!testVerification)
            {
                System.out.println("Search test failed. Skipping the Add RentalContract test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Search RentalContract Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Add
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            RentalContract foundRentalContract;

            RentalContract rentalContract = new RentalContract();
            // only the required parameters are set
            rentalContract.setDateStart("1234-01-02");
            rentalContract.setDateEnd("1234-03-04");
            rentalContract.setAddressDropoff("testAddressDropoff");
            rentalContract.setAddressPickup("testAddressPickup");
            rentalContract.setBasePrice(999);
            rentalContract.setExtras("testChild, testSeat");
            rentalContract.setEmployee_id(null);
            rentalContract.setCustomer_id(null);
            rentalContract.setRv_id(null);

            // set date Signed just for testing purposes, it is not necessary for the actual addition
            rentalContract.setDateSigned("0000-00-00 00:00:00");
            rentalContract.setStatus("open");

            // when
            queryResult = rentalContractRepo.addRentalContract(rentalContract);
            foundRentalContract = rentalContractRepo.searchByAddressDropoff(rentalContract.getAddressDropoff()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added RentalContract is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(rentalContract.toString().replaceFirst("(\\d+\\s\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+)", "")).
                    isEqualTo(foundRentalContract.toString().replaceFirst("(\\d+\\s\\d+-\\d+-\\d+\\s\\d+:\\d+:\\d+)", ""));

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("updateRentalContract()")
        @Order(3)
        public void rentalContractRepoUpdateRentalContractTest() throws Exception
        {
            System.out.println("RentalContract Repo - Test 3 : Update Rental Contract Test");

            if (!testVerification)
            {
                System.out.println("Add RentalContract test failed. Skipping the Update RentalContract test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Add RentalContract Test to be ").isEqualTo("Successful");            }
            // If the Add has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            RentalContract foundRentalContract;

            RentalContract rentalContract = rentalContractRepo.searchByAddressDropoff("testAddressDropoff").get(0);

            rentalContract.setDateStart("8888-08-09");
            rentalContract.setDateEnd("9999-09-09");
            rentalContract.setAddressDropoff("testAddressDropoffUpdated");
            rentalContract.setAddressPickup("testAddressPickupUpdated");
            rentalContract.setKmDriven(1234);
            rentalContract.setStatus("cancelled");

            // when
            queryResult = rentalContractRepo.updateRentalContract(rentalContract);

            foundRentalContract = rentalContractRepo.searchByAddressDropoff(rentalContract.getAddressDropoff()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added RentalContract is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(rentalContract.toString()).isEqualTo(foundRentalContract.toString());

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("deleteRentalContract()")
        @Order(4)
        public void rentalContractRepoDeleteRentalContractTest() throws Exception
        {
            System.out.println("RentalContract Repo - Test 4 : Delete RentalContract Test");

            if (!testVerification)
            {
                System.out.println("Update RentalContract test failed. Skipping the Delete RentalContract test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Update RentalContract Test to be ").isEqualTo("Successful");
            }

            // If the Update has passed, we can test the Delete
            // given
            RentalContract rentalContract = rentalContractRepo.searchByAddressDropoff("testAddressDropoffUpdated").get(0); // Searching for an updated value
            List<RentalContract> foundRentalContractList;

            // when
            boolean rentalContractDeleted = rentalContractRepo.deleteRentalContract(rentalContract.getId());
            foundRentalContractList = rentalContractRepo.searchByAddressDropoff("testAddressDropoffUpdated"); // checking if the entry still exists in the DB

            // then
            assertThat(rentalContractDeleted).isTrue();
            assertThat(foundRentalContractList).isEmpty();
        }

        @Test
        @DisplayName("fetchAll()")
        public void rentalContractRepoFetchAllTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.fetchAll();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchById()")
        public void rentalContractRepoFetchByIDTest() throws Exception
        {
            RentalContract rentalContract = rentalContractRepo.fetchContractByID(4000);
            assertThat(rentalContract).isNotNull();
        }

        @Test
        @DisplayName("searchByAddressPickup()")
        public void rentalContractRepoSearchByAddressPickupTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByAddressPickup("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByCustomerName()")
        public void rentalContractRepoSearchByCustomerNameTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByCustomerName("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByEmployeeName()")
        public void rentalContractRepoSearchByEmployeeNameTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByEmployeeName("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByRvModel()")
        public void rentalContractRepoSearchByRvModelTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByRvModel("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByExtras()")
        public void rentalContractRepoSearchByExtrasTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByExtras("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByStatus()")
        public void rentalContractRepoSortByStatusTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByStatus();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateSigned()")
        public void rentalContractRepoSortByDateSignedTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateSigned();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateStart()")
        public void rentalContractRepoSortByDateStartTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateStart();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateEnd()")
        public void rentalContractRepoSortByDateEndTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateEnd();
            assertThat(rentalContractList).isNotEmpty();
        }
    }
    //endregion

    //region RV Service
    @Autowired
    private RentalContractService rcService;

    // All of the services used in the Service Layer have to be autowired here
    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RVService rvService;

    @Nested
    @DisplayName("Service Layer Loading Validation")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class RentalContractServiceTests
    {
        // In the rental contract use case, the Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void rentalContractServiceLoadsTest()
        {
            // validate if the Rv Service layer has loaded
            assertThat(rcService).isNotNull();
        }

        @Test
        @DisplayName("fetchCustomersInRC()")
        public void RentalContractServiceFetchCustomersInRCTest()
        {
            // Depends on multiple fetchAll methods working and the database containing valid data
            List<RentalContract> rcList = rcService.fetchAll();

            List<Customer> customerList = rcService.fetchCustomersInRC(rcList);

            assertThat(customerList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchEmployeesInRC()")
        public void RentalContractServiceFetchEmployeesInRCTest()
        {
            // Depends on multiple fetchAll methods working and the database containing valid data
            List<RentalContract> rcList = rcService.fetchAll();

            List<Employee> employeeList = rcService.fetchEmployeesInRC(rcList);

            assertThat(employeeList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchRVsInRC()")
        public void RentalContractServiceFetchRVsInRCTest()
        {
            // Depends on multiple fetchAll methods working and the database containing valid data
            List<RentalContract> rcList = rcService.fetchAll();

            List<RV> rvList = rcService.fetchRVsInRC(rcList);

            assertThat(rvList).isNotEmpty();
        }

        //region Base Price Calculation
        @Test
        @DisplayName("getDateDifferenceInDays()")
        public void RentalContractServiceGetDateDifferenceInDaysTest()
        {
            RentalContract rentalContract = new RentalContract();
            // days difference should be 105 days
            rentalContract.setDateStart("2020-01-03");
            rentalContract.setDateEnd("2020-04-17");

            assertThat(rcService.getDateDifferenceInDays(rentalContract)).isEqualTo(105);
        }

        @Test
        @DisplayName("calculateDeliveryDistance()")
        public void RentalContractServiceCalculateDeliveryDistanceTest()
        {
            RentalContract rentalContract = new RentalContract();
            rentalContract.setAddressDropoff("Store Address");
            rentalContract.setAddressPickup("Test Pickup address");

            // Dropoff address matches the Store Address and therefore equals 0
            // Pickup has a hash of -1864390754, which results in 754
            assertThat(rcService.calculateDeliveryDistance(rentalContract.getAddressDropoff())).isEqualTo(0);
            assertThat(rcService.calculateDeliveryDistance(rentalContract.getAddressPickup())).isEqualTo(754);
        }

        @Test
        @DisplayName("calculateExtrasPrice()")
        public void RentalContractServiceCalculateExtrasPriceTest()
        {
            String extras = "child";
            assertThat(rcService.calculateExtrasPrice(extras)).isEqualTo(15);

            extras = "child, Seat";
            assertThat(rcService.calculateExtrasPrice(extras)).isEqualTo(30);

            extras = "child, Seat, bed linen, picnic table";
            assertThat(rcService.calculateExtrasPrice(extras)).isEqualTo(60);
        }

        @Test
        @DisplayName("calculateSeasonMultiplier()")
        public void rentalContractServiceCalculateSeasonMultiplierTest()
        {
            // Month 1 - Low Season
            String dateStart = "1470-01-10";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1);

            // Month 2 - Low Season
            dateStart = "1360-02-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1);

            // Month 3 - Middle Season
            dateStart = "1360-03-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 4 - Middle Season
            dateStart = "1360-04-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 5 - Middle Season
            dateStart = "1360-05-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 6 - Peak Season
            dateStart = "1360-06-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.6);

            // Month 7 - Peak Season
            dateStart = "1360-07-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.6);

            // Month 8 - Peak Season
            dateStart = "1360-08-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.6);

            // Month 9 - Middle Season
            dateStart = "1360-09-12";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 10 - Middle Season
            dateStart = "1667-10-24";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 11 - Middle Season
            dateStart = "1667-11-24";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1.3);

            // Month 12 - Low Season
            dateStart = "1667-12-24";
            assertThat(rcService.calculateSeasonMultiplier(dateStart)).isEqualTo(1);

            // Month 0 - INVALID
            dateStart = "1667-00-24";
            try {
                rcService.calculateSeasonMultiplier(dateStart);
                assertThat("Test for Month 0 Failed").isEqualTo("Dis statement was not supposed to be reached");
            } catch (DateTimeParseException e)
            {}

            // Month 13 - INVALID
            dateStart = "1667-13-24";
            try {
                rcService.calculateSeasonMultiplier(dateStart);
                assertThat("Test for Month 13 Failed").isEqualTo("Dis statement was not supposed to be reached");
            } catch (DateTimeParseException e)
            {}
        }

        @Test
        @DisplayName("calculateDeliveryPrice()")
        public void RentalContractServiceCalculateDeliveryPriceTest()
        {
            RentalContract rentalContract = new RentalContract();
            rentalContract.setAddressDropoff("Test Dropoff address");
            rentalContract.setAddressPickup("Test Pickup address");

            // Dropoff address has a hash of 487720038, which results in 38, which after * 0.7 = 26
            // Pickup has a hash of -1864390754, which results in 754, which after * 0.7 = 527
            assertThat(rcService.calculateDeliveryPrice(rentalContract)).isEqualTo(554);
        }

        @Test
        @DisplayName("calculateBasePrice()")
        public void RentalContractServiceCalculateBasePriceTest()
        {
            RentalContract rentalContract = new RentalContract();
            // days difference should be 105 days
            rentalContract.setDateStart("2020-01-03");
            rentalContract.setDateEnd("2020-04-17");
            // expected price - 4 x fixedPrice (atm, 15), total 60
            rentalContract.setExtras("extra1, extra2, extra Three, extra4");
            rentalContract.setAddressDropoff("Test Dropoff address"); // Dropoff address has a hash of 487720038, which results in 38, which after * 0.7 = 26
            rentalContract.setAddressPickup("Test Pickup address");   // Pickup has a hash of -1864390754, which results in 754, which after * 0.7 = 527

            int rvPrice = 100;
            int extrasPrice = 60;
            int expectedTotalBasePrice = 105 * (rvPrice + extrasPrice); // days * (rvPrice + extrasPrice)
            expectedTotalBasePrice += 554; // price for delivery

            assertThat(rcService.calculateBasePrice(rentalContract, rvPrice)).isEqualTo(expectedTotalBasePrice);
        }
        //endregion

        //region Final Price calculation
        @Test
        @DisplayName("calculateKmDrivenPrice() - Under Threshold")
        public void RentalContractServiceCalculateKmDrivenPriceUnderThresholdTest()
        {
            RentalContract rentalContract = new RentalContract();
            // days difference should be 105 days
            rentalContract.setDateStart("2020-01-03");
            rentalContract.setDateEnd("2020-04-17");
            rentalContract.setKmDriven(41000); // 105 days * 400 km = 42000 km. 41000 is under that

            assertThat(rcService.calculateKmDrivenPrice(rentalContract)).isEqualTo(0);
        }

        @Test
        @DisplayName("calculateKmDrivenPrice() - Over Threshold")
        public void RentalContractServiceCalculateKmDrivenPriceOverThresholdTest()
        {
            RentalContract rentalContract = new RentalContract();
            // days difference should be 105 days
            rentalContract.setDateStart("2020-01-03");
            rentalContract.setDateEnd("2020-04-17");
            rentalContract.setKmDriven(44100); // 105 days * 400 km = 42000 km. 44100 is over that

            assertThat(rcService.calculateKmDrivenPrice(rentalContract)).isEqualTo(2100);
        }

        @Test
        @DisplayName("calculateCharge() - All edge cases")
        public void RentalContractServiceCalculateChargeTest()
        {
            RentalContract rentalContract = new RentalContract();
            rentalContract.setBasePrice(1000);
            String currentDate = LocalDate.now().plusDays(75).toString(); // 75 days ahead of current date
            rentalContract.setDateStart(currentDate);

            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(200); // X > 50, expected charge = 200

            currentDate = LocalDate.now().plusDays(51).toString(); // 51 days ahead of current date
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(200); // X > 50, expected charge = 200

            currentDate = LocalDate.now().plusDays(50).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(200); // 50 == X, expected charge = 200

            currentDate = LocalDate.now().plusDays(49).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(500); // 50 > X > 15, expected charge 1000 * 0,5

            currentDate = LocalDate.now().plusDays(16).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(500); // 50 > X > 15, expected charge 1000 * 0,5

            currentDate = LocalDate.now().plusDays(15).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(500); // 50 > X == 15, expected charge 1000 * 0,5

            currentDate = LocalDate.now().plusDays(14).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(800); // 15 > X > 1, expected charge 1000 * 0,8

            currentDate = LocalDate.now().plusDays(2).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(800); // 15 > X > 1, expected charge 1000 * 0,8

            currentDate = LocalDate.now().plusDays(1).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(800); // 15 > X == 1, expected charge 1000 * 0,8

            currentDate = LocalDate.now().plusDays(0).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(950); // X == 0, expected charge 1000 * 0,95

            currentDate = LocalDate.now().plusDays(-1).toString();
            rentalContract.setDateStart(currentDate);
            assertThat(rcService.calculateCharge(rentalContract)).isEqualTo(-1); // 0 > X, else case, expected charge -1
        }

        @Test
        @DisplayName("calculateFuelPrice() - Different Values")
        public void RentalContractServiceCalculateFuelPriceTest()
        {
            // Over the threshold
            float fuelStatus = (float) 0.7;
            assertThat(rcService.calculateFuelPrice(fuelStatus)).isEqualTo(0);

            // right on the threshold
            fuelStatus = (float) 0.5;
            assertThat(rcService.calculateFuelPrice(fuelStatus)).isEqualTo(0);

            // under the threshold
            fuelStatus = (float) 0.3;
            assertThat(rcService.calculateFuelPrice(fuelStatus)).isNotEqualTo(0);
        }

        @Test
        @DisplayName("calculateFinalPrice()")
        public void RentalContractServiceCalculateFinalPriceTest()
        {
            RentalContract rentalContract = new RentalContract();
            rentalContract.setDateStart("2020-01-03");
            rentalContract.setDateEnd("2020-04-17");
            rentalContract.setKmDriven(44100); // 105 days * 400 km = 42000 km. 44100 is over that. Extra price = 2100
            rentalContract.setBasePrice(17327); // resulting base price from the calculateBasePrice() test.
            float fuelStatus = (float) 0.2; // over the threshold of 0.5. Extra price = 70

            assertThat(rcService.calculateFinalPrice(rentalContract, fuelStatus)).isEqualTo(19497);
        }
        //endregion
    }
    //endregion
}
