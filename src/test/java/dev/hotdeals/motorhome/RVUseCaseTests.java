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
    public void rvRepoFetchAllTest() throws Exception
    {
        List<RV> rvList = rvRepo.fetchAll();
        assertThat(rvList).isNotNull();
    }

    @Autowired
    RVService rvService;

    @Test
    public void rvService() throws Exception
    {
        List<RV> rvList = rvService.fetchAll();
        assertThat(rvList).isNotNull();
    }
}
