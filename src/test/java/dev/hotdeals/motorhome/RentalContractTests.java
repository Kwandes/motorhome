package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.RentalContract;
import dev.hotdeals.motorhome.Repository.RentalContractRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RentalContractTests {

    private static boolean testCondition = false;

    @Autowired
    RentalContractRepo rentalContractRepo;

    @Test
    @DisplayName("Fetch all contracts objects from DB")
    public void rentalContractFetchAll() {

        List<RentalContract> rentalContractListTest = null;

        rentalContractListTest = rentalContractRepo.fetchAll();

        assertThat(rentalContractListTest).isNotNull();
    }

    @Test
    @DisplayName("Fetch contract objects from ID in DB")
    public void rentalContractFetchByID() {

        RentalContract rentalContractTest = null;

        int contractID = 4001;

        rentalContractTest = rentalContractRepo.fetchContractByID( contractID );

        assertThat( rentalContractTest ).isNotNull();
    }

}
