package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import dev.hotdeals.motorhome.Service.RentalContractService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

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
        public void rentalContractRepoLoads()
        {
            // validate if the Rv Repository layer has loaded
            assertThat(rentalContractRepo).isNotNull();
        }

        @Test
        @DisplayName("searchByAddressDropoff()")
        @Order(1)
        public void rentalContractRepoFetchAll() throws Exception
        {
            // searchByBrand is run as the first test as the add, update and delete tests depend on it
            System.out.println("RentalContract Repo ordered tests of adding, updating and deleting data");
            System.out.println("RentalContract Repo - Test 1 : searchByAddressDropoff");

            List<RentalContract> rentalContractList = rentalContractRepo.fetchAll();

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
            rentalContract.setDateStart("1234-01-02 12:34:56");
            rentalContract.setDateEnd("1234-03-04 12:34:56");
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
                assertThat("Rv Repo - Add RentalContract Test to be ").isEqualTo("Successful");
            }
            // If the Add has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            RentalContract foundRentalContract;

            RentalContract rentalContract = rentalContractRepo.searchByAddressDropoff("testAddressDropoff").get(0);

            rentalContract.setDateStart("8888-08-09 10:11:12");
            rentalContract.setDateEnd("9999-09-09 13:14:15");
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
        @DisplayName("searchByCustomerName()")
        public void rentalContractRepoSearchByCustomerNameTest() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByCustomerName("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByRvModel()")
        public void rentalContractRepoSearchByRvModel() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByRvModel("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByRvExtras()")
        public void rentalContractRepoSearchByExtras() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.searchByExtras("");
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByStatus()")
        public void rentalContractRepoSortByStatus() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByStatus();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateSigned()")
        public void rentalContractRepoSortByDateSigned() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateSigned();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateStart()")
        public void rentalContractRepoSortByDateStart() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateStart();
            assertThat(rentalContractList).isNotEmpty();
        }

        @Test
        @DisplayName("sortByDateEnd()")
        public void rentalContractRepoSortByDateEnd() throws Exception
        {
            List<RentalContract> rentalContractList = rentalContractRepo.sortByDateEnd();
            assertThat(rentalContractList).isNotEmpty();
        }
    }
    //endregion

    @Autowired
    private RentalContractService rentalContractService;

    @Nested
    public class RentalContractServiceTests
    {
        // In the rental contract use case, the Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void rentalContractServiceLoadsTest()
        {
            // validate if the Rv Service layer has loaded
            assertThat(rentalContractService).isNotNull();
        }
    }
}
