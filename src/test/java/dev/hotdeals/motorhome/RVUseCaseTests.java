/*
    Testing of the RV Use Case - related code
 */

package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Repository.RVRepo;
import dev.hotdeals.motorhome.Service.RVService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@SpringBootTest
public class RVUseCaseTests
{

    //region RV Repo
    @SpringBootTest
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Have to look into so I can explain it to CAY
    public static class RVRepoTests
    {
        @Autowired
        RVRepo rvRepo;

        @Test
        public void rvRepoFetchAllTest() throws Exception // Why we throw Exception ?
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
            List<RV>  rvList = rvRepo.fetchAvailable();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvRepoFetchRentedTest() throws Exception
        {
            List<RV> rvList = rvRepo.fetchAvailable();
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

        @Test
        @Order(1)
        public void rvRepoSearchByModelTest() throws Exception
        {
            System.out.println("Test 1 : Search");
            List<RV> rvList = rvRepo.searchByModel("");
            assertThat(rvList).isNotNull();
        }

        @Test
        @Order(2)
        public void rvRepoAddRVTest() throws Exception
        {
            System.out.println("Test 2 : Add");
            // given
            RV rv = new RV();
            rv.setBrand("testBrand");
            rv.setModel("testModel");
            rv.setColor("testColor");
            rv.setRvType("testType");
            rv.setPrice(0);

            // when
            boolean rvAdded = rvRepo.addRV(rv);

            // then
            assertThat(rvAdded).isTrue();
        }

        @Test
        @Order(3)
        public void rvRepoUpdateRVTest() throws Exception
        {
            System.out.println("Test 3 : Update");
            // given
            RV rv = rvRepo.searchByModel("testModel").get(0);
            System.out.println(rv);
            rv.setRvType("updatedType");
            rv.setPrice(10);
            rv.setBrand("updatedBrand");

            // when
            boolean rvUpdated = rvRepo.updateRV(rv);

            // then
            assertThat(rvUpdated).isTrue();
        }

        @Test
        @Order(4)
        public void rvRepoDeleteRVTest() throws Exception
        {
            System.out.println("Test 4 : Delete");
            // given
            RV rv = rvRepo.searchByModel("testModel").get(0);

            // when
            boolean rvDeleted = rvRepo.deleteRV(rv.getId());

            // then
            assertThat(rvDeleted).isTrue();
        }
    }
    //endregion

    //region RV Service
    @SpringBootTest
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public static class RVServiceTests
    {
        @Autowired
        RVService rvService;

        @Test
        public void rvServiceFetchAllTest() throws Exception
        {
            List<RV> rvList = rvService.fetchAll();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceFetchByIDTest() throws Exception
        {
            RV rv = rvService.fetchByID(3000);
            assertThat(rv).isNotNull();
        }

        @Test
        public void rvServiceFetchAvailableTest() throws Exception
        {
            List<RV>  rvList = rvService.fetchAvailable();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceFetchRentedTest() throws Exception
        {
            List<RV> rvList = rvService.fetchAvailable();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceFetchRequiresCleaningTest() throws Exception
        {
            List<RV> rvList = rvService.fetchRequiresCleaning();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceFetchRequiresMaintenanceTest() throws Exception
        {
            List<RV> rvList = rvService.fetchRequiresMaintenance();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceSortByPriceTest() throws Exception
        {
            List<RV> rvList = rvService.sortByPrice();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceSearchByBrandTest() throws Exception
        {
            // Search for an empty string -> the query will end up as :
            List<RV> rvList = rvService.searchByBrand("");
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceSearchByIDTest() throws Exception
        {
            // Search for cars that will contain '3' in their ID ( by convention all car ids will start with 3, so the
            // search will always return something as long as the table has cars in it)
            List<RV> rvList = rvService.searchByID(3);
            assertThat(rvList).isNotEmpty();
        }

        @Test
        @Order(1)
        public void rvServiceSearchByModelTest() throws Exception
        {
            System.out.println("Test 1 : Search");
            List<RV> rvList = rvService.searchByModel("");
            assertThat(rvList).isNotNull();
        }

        @Test
        @Order(2)
        public void rvServiceAddRVTest() throws Exception
        {
            System.out.println("Test 2 : Add");
            // given
            RV rv = new RV();
            rv.setBrand("testBrand");
            rv.setModel("testModel");
            rv.setColor("testColor");
            rv.setRvType("testType");
            rv.setPrice(0);

            // when
            boolean rvAdded = rvService.addRV(rv);

            // then
            assertThat(rvAdded).isTrue();
        }

        @Test
        @Order(3)
        public void rvServiceUpdateRVTest() throws Exception
        {
            System.out.println("Test 3 : Update");
            // given
            RV rv = rvService.searchByModel("testModel").get(0);
            System.out.println(rv);
            rv.setRvType("updatedType");
            rv.setPrice(10);
            rv.setBrand("updatedBrand");

            // when
            boolean rvUpdated = rvService.updateRV(rv);

            // then
            assertThat(rvUpdated).isTrue();
        }

        @Test
        @Order(4)
        public void rvServiceDeleteRVTest() throws Exception
        {
            System.out.println("Test 4 : Delete");
            // given
            RV rv = rvService.searchByModel("testModel").get(0);

            // when
            boolean rvDeleted = rvService.deleteRV(rv.getId());

            // then
            assertThat(rvDeleted).isTrue();
        }
    }
    //endregion

}
