package dev.hotdeals.motorhome;


import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Repository.CustomerRepo;
import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.engine.IterationStatusVar;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerUseCaseTests
{
    private static boolean testCondition = false;
    @Autowired
    CustomerRepo customerRepo;

    @Test
    @DisplayName("Fetch customer object by ID from DB")
    public void customerRepoFetchById()
    {
        //given - User Of ID 2000 exists
        Customer customerTestObject = null;
        int customerIdToRetrieve = 2000;
        //when
        customerTestObject = customerRepo.fetchByID(customerIdToRetrieve);
        //then
        assertThat(customerTestObject).isNotNull();
    }

    @Test
    @DisplayName("Fetch all customers from DB")
    public void customerRepoFetchAll()
    {
        // given - the DB is not empty
        List<Customer> customersTestList = null;
        // when
        customersTestList = customerRepo.fetchAll();
        // then
        assertThat(customersTestList).isNotNull();
    }

    @Test
    @Order(2)
    @DisplayName("Add a customer to the DB")
    public void customerRepoAddCustomer()
    {
        if (testCondition)
        {
            // given
            Customer customer = new Customer();
            customer.setFirstName("TestName");
            customer.setLastName("TestLastName");
            customer.setCpr("999999-9999");
            customer.setPhoneNumber("12345678");
            customer.setEmail("testemail@test.mail");
            customer.setBirthdate("1990-12-24");
            customer.setAddress("test of address name");
            customer.setAddress2("test of second address name");
            customer.setGender("Test");
            // when
            testCondition = customerRepo.addCustomer(customer);
        } else
        {
            System.out.println("Search test failed. Skipping Add test...");
        }
        // then
        assertThat(testCondition).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("Change a customer's information from the DB")
    public void customerRepoUpdateCustomer()
    {
        if (testCondition)
        {
            // given - the ID exist
            String originalFirstName = "TestName";
            String EditedFirstName = "NameTest";
            Customer testCustomer = customerRepo.searchByName(originalFirstName).get(0);
            testCustomer.setFirstName(EditedFirstName);
            // when
            testCondition = customerRepo.updateCustomer(testCustomer);
        } else
        {
            System.out.println("Search test failed. Skipping Update test...");
        }
        // then
        assertThat(testCondition).isTrue();
    }


    @Test
    @Order(4)
    @DisplayName("Delete customer from DB using Id")
    public void customerRepoDeleteCustomerTest()
    {
        if (testCondition)
        {
            // given - the Customer exists
            String customerNameToDelete = "NameTest"; //name used to search for the customer to delete
            Customer testCustomerDelete = customerRepo.searchByName(customerNameToDelete).get(0); //the Customer we want to delete
            int customerIdToDelete = testCustomerDelete.getId(); //getting the ID of the customer to delete
            // when
            testCondition = customerRepo.deleteCustomer(customerIdToDelete);
        } else
        {
            System.out.println("Search test failed. Skipping Update test...");
        }
        // then
        assertThat(testCondition).isTrue();
    }

    @Test
    @DisplayName("Search for a customer by CPR - partial input is valid")
    public void customerRepoSearchByCpr()
    {
        // given - Cpr 160428-1231 exists
        String cpr = "160428-1231";
        // when
        List<Customer> customerList = customerRepo.searchByCpr(cpr);
        // then
        assertThat(customerList).isNotEmpty();
    }

    @Test
    @Order(1)
    @DisplayName("Search for a customer by name")
    public void customerRepoSearchByNameTest()
    {
        // given - name exists
        String name = "Adele";
        // when
        List<Customer> customersReturnedFromSearch= null;
        customersReturnedFromSearch = customerRepo.searchByName(name);
        // then
        testCondition = !customersReturnedFromSearch.isEmpty();
        assertThat(customersReturnedFromSearch).isNotEmpty();
    }
}
