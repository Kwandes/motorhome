/*
    Testing of the RV Use Case - related code
 */

package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Repository.RVRepo;
import dev.hotdeals.motorhome.Service.RVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestExecution;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@SpringBootTest
public class RVUseCaseTests
{
    @SpringBootTest
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
        public void rvRepoSearchByModelTest() throws Exception
        {
            List<RV> rvList = rvRepo.searchByModel("");
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

    //region RV Service
    @SpringBootTest
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

//    @Test -- Currently not implemented due to lack of knowledge of proper implementation.
//    public void rvServiceAddRVTest() throws Exception
//    {
//        RV rv = new RV();
//        rv.setBrand("testBrand");
//        rv.setModel("testModel");
//        rv.setColor("testColor");
//        rv.setRvType("testType");
//        rv.setPrice(0);
//        boolean rvAdded = rvService.addRV(rv);
//        assertThat(rvAdded).isTrue();
//    }

        @Test
        public void rvServiceSortByPriceTest() throws Exception
        {
            List<RV> rvList = rvService.sortByPrice();
            assertThat(rvList).isNotEmpty();
        }

        @Test
        public void rvServiceSearchByModelTest() throws Exception
        {
            List<RV> rvList = rvService.searchByModel("");
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
    }
    //endregion

}
