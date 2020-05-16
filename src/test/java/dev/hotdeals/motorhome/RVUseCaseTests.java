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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
public class RVUseCaseTests
{

    @Autowired
    RVRepo rvRepo;

    @Test
    public void rvRepoFetchAllTest() throws Exception // Why we throw Exception ?
    {
        List<RV> rvList = rvRepo.fetchAll();
        assertThat(rvList).isNotNull();
    }

    @Autowired
    RVService rvService;
    //region RV Service
    @Test
    public void rvServiceFetchAllTest() throws Exception
    {
        List<RV> rvList = rvService.fetchAll();
        assertThat(rvList).isNotNull();
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
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceFetchRentedTest() throws Exception
    {
        List<RV> rvList = rvService.fetchAvailable();
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceFetchRequiresCleaningTest() throws Exception
    {
        List<RV> rvList = rvService.fetchRequiresCleaning();
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceFetchRequiresMaintenanceTest() throws Exception
    {
        List<RV> rvList = rvService.fetchRequiresMaintenance();
        assertThat(rvList).isNotNull();
    }

//    @Test
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
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceSearchByModelTest() throws Exception
    {
        List<RV> rvList = rvService.searchByModel("");
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceSearchByBrandTest() throws Exception
    {
        // Search for an empty string -> the query will end up as :
        List<RV> rvList = rvService.searchByBrand("");
        assertThat(rvList).isNotNull();
    }

    @Test
    public void rvServiceSearchByIDTest() throws Exception
    {
        // Search for cars that will contain '3' in their ID ( by convention all car ids will start with 3, so the
        // search will always return something as long as the table has cars in it)
        List<RV> rvList = rvService.searchByID(3);
        assertThat(rvList).isNotNull();
    }
    //endregion
}
