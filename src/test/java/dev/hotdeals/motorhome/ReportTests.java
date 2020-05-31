package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Service.RVService;
import dev.hotdeals.motorhome.Service.ReportService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportTests
{
    //region Report Service
    @Autowired
    private ReportService reportService;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class ReportServiceTests
    {
        @Test
        public void reportServiceLoads()
        {
            // validate if the Rv Service layer has loaded
            assertThat(reportService).isNotNull();
        }

        @Test
        @DisplayName("fetchContractStats()")
        public void ReportServiceFetchContractStatsTest() throws Exception
        {
            System.out.println(reportService.fetchContractStats());
            assertThat(reportService.fetchContractStats()).isNotEmpty();
        }

        @Test
        @DisplayName("fetchEmployeeStats()")
        public void ReportServiceFetchEmployeeStatsTest() throws Exception
        {
            System.out.println(reportService.fetchEmployeeStats());
            assertThat(reportService.fetchEmployeeStats()).isNotEmpty();
        }

        @Test
        @DisplayName("fetchCustomerStats()")
        public void ReportServiceFetchCustomerStatsTest() throws Exception
        {
            System.out.println(reportService.fetchCustomerStats());
            assertThat(reportService.fetchCustomerStats()).isNotEmpty();
        }

        @Test
        @DisplayName("fetchRvStats()")
        public void ReportServiceFetchRvStatsTest() throws Exception
        {
            System.out.println(reportService.fetchRvStats());
            assertThat(reportService.fetchRvStats()).isNotEmpty();
        }
    }
    //endregion
}
