/*
    Testing of the RV Use Case Service and Repository
 */

package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Repository.RVRepo;
import dev.hotdeals.motorhome.Service.RVService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RVUseCaseTests
{
    @Autowired
    RVRepo rvRepo;

    public static boolean testVerification; // holds status of the previous test in the series

    //region RV Repo
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class RVRepoTests
    {
        @Test
        public void rvRepoLoads()
        {
            // validate if the Rv Repository layer has loaded
            assertThat(rvRepo).isNotNull();
        }

        @Test
        @Order(1)
        public void rvRepoSearchByModelTest() throws Exception
        {
            System.out.println("RV Repo ordered tests of adding, updating and deleting data");
            System.out.println("RV Repo - Test 1 : Search");
            List<RV> rvList = rvRepo.searchByModel("");

            testVerification = false; // if the list is empty, testVerification will be false ( test has failed ) else, it will be true

            assertThat(rvList).isNotEmpty();
            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(2)
        public void rvRepoAddRVTest() throws Exception
        {
            System.out.println("RV Repo - Test 2 : Add RV Test");

            if (!testVerification)
            {
                System.out.println("Search test failed. Skipping the Add RV test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Search RV Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Add
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            RV foundRV;

            RV rv = new RV();
            // only the required parameters are set
            rv.setBrand("testBrand");
            rv.setModel("testModel");
            rv.setColor("testColor");
            rv.setRvType("testType");
            rv.setPrice(0);

            // set fuel status to 1 as the database default is 1
            rv.setFuelStatus((float)1.0);

            // when
            queryResult = rvRepo.addRV(rv);
            foundRV = rvRepo.searchByBrand(rv.getBrand()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added RV is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(rv.toString().replaceFirst("(\\d+)", "")).
                    isEqualTo(foundRV.toString().replaceFirst("(\\d+)", ""));

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(3)
        public void rvRepoUpdateRVTest() throws Exception
        {
            System.out.println("RV Repo - Test 3 : Update RV Test");

            if (!testVerification)
            {
                System.out.println("Add RV test failed. Skipping the Update RV test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Add RV Test to be ").isEqualTo("Successful");
            }
            // If the Add has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            RV foundRV;

            RV rv = rvRepo.searchByBrand("testBrand").get(0);
            // update all attributes except for the ID
            rv.setBrand("testBrandUpdated");
            rv.setModel("testModelUpdated");
            rv.setColor("testColorUpdated");
            rv.setRvType("testTypeUpdated");
            rv.setPrice(100);
            rv.setKmDriven(200);
            rv.setFuelStatus((float)0.9);
            rv.setIsRented(true);
            rv.setRequiresCleaning(true);
            rv.setRequiresMaintenance(true);
            rv.setRequiresFurtherService(true);

            // when
            queryResult = rvRepo.updateRV(rv);

            foundRV = rvRepo.searchByBrand(rv.getBrand()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added RV is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(rv.toString()).isEqualTo(foundRV.toString());

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(4)
        public void rvRepoDeleteRVTest() throws Exception
        {
            System.out.println("RV Repo - Test 4 : Delete RV Test");

            if (!testVerification)
            {
                System.out.println("Update RV test failed. Skipping the Delete RV test...");
                // throw a more descriptive exception message
                assertThat("Rv Repo - Update RV Test to be ").isEqualTo("Successful");
            }

            // If the Update has passed, we can test the Delete
            // given
            RV rv = rvRepo.searchByBrand("testBrandUpdated").get(0); // Searching for an updated value
            List<RV> foundRVList;

            // when
            boolean rvDeleted = rvRepo.deleteRV(rv.getId());
            foundRVList = rvRepo.searchByBrand("testBrandUpdated"); // checking if the entry still exists in the DB

            // then
            assertThat(rvDeleted).isTrue();
            assertThat(foundRVList).isEmpty();
        }

        @Test
        public void rvRepoFetchAllTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchAll();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoFetchByIDTest() throws Exception
        {
            RV rv = rvRepo.fetchByID(3000);
            assertThat(rv).isNotNull();
        }

        @Test
        public void rvRepoFetchAvailableTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchAvailable();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoFetchRentedTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchRented();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoFetchRequiresCleaningTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchRequiresCleaning();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoFetchRequiresMaintenanceTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchRequiresMaintenance();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoSortByPriceTest() throws Exception
        {
            List<RV> rvList = rvRepo.sortByPrice();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoSearchByBrandTest() throws Exception
        {
            // Search for an empty string -> the query will end up as :
            List<RV> rvList = rvRepo.searchByBrand("");
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoSearchByIDTest() throws Exception
        {
            // Search for cars that will contain '3' in their ID ( by convention all car ids will start with 3, so the
            // search will always return something as long as the table has cars in it)
            List<RV> rvList = rvRepo.searchByID(3);
            assertThat(rvList).isNotEmpty();
        }
    }
    //endregion

    //region RV Service
    @Autowired
    private RVService rvService;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class RVServiceTests
    {
        // In the RV Use Case, the Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void rvServiceLoads()
        {
            // validate if the Rv Service layer has loaded
            assertThat(rvService).isNotNull();
        }
    }
    //endregion

}