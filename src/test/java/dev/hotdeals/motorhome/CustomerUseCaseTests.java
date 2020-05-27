package dev.hotdeals.motorhome;


import dev.hotdeals.motorhome.Model.Customer;
import dev.hotdeals.motorhome.Repository.CustomerRepo;
import dev.hotdeals.motorhome.Service.CustomerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerUseCaseTests{
    @Autowired
    CustomerRepo customerRepo;

    public static boolean testVerification; // holds status of the previous test in the series

    //region Customer Repo
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class CustomerRepoTests
    {
        @Test
        public void customerRepoLoads()
        {
            // validate if the Customer Repository layer has loaded
            assertThat(customerRepo).isNotNull();
        }

        @Test
        @Order(1)
        @DisplayName("searchByName()")
        public void customerRepoSearchByNameTest() throws Exception
        {
            System.out.println("Customer Repo ordered tests of adding, updating and deleting data");
            System.out.println("Customer Repo - Test 1 : Search");
            List<Customer> customerList = customerRepo.searchByName("");

            testVerification = false; // if the list is empty, testVerification will be false ( test has failed ) else, it will be true

            assertThat(customerList).isNotEmpty();
            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(2)
        @DisplayName("addCustomer()")
        public void customerRepoAddCustomerTest() throws Exception
        {
            System.out.println("Customer Repo - Test 2 : Add Customer Test");

            if (!testVerification)
            {
                System.out.println("Search test failed. Skipping the Add Customer test...");
                // throw a more descriptive exception message
                assertThat("Customer Repo - Search Customer Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Add
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            Customer foundCustomer;

            Customer customer = new Customer();
            // only the required parameters are set
            customer.setFirstName("testFirstName");
            customer.setLastName("testLastName");
            customer.setCpr("999999-9999");
            customer.setPhoneNumber("1234567890");
            customer.setEmail("test@junit.minecraft.net");
            customer.setBirthdate("1234-05-06");
            customer.setAddress("testAddress1");
            customer.setAddress2("testAddress2");
            customer.setGender("testGender");

            // when
            queryResult = customerRepo.addCustomer(customer);
            foundCustomer = customerRepo.searchByName(customer.getFirstName()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added Customer is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(customer.toString().replaceFirst("(\\d+)", "")).
                    isEqualTo(foundCustomer.toString().replaceFirst("(\\d+)", ""));

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(3)
        @DisplayName("updateCustomer()")
        public void customerRepoUpdateCustomerTest() throws Exception
        {
            System.out.println("Customer Repo - Test 3 : Update Customer Test");

            if (!testVerification)
            {
                System.out.println("Add Customer test failed. Skipping the Update Customer test...");
                // throw a more descriptive exception message
                assertThat("Customer Repo - Add Customer Test to be ").isEqualTo("Successful");
            }
            // If the Add has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            Customer foundCustomer;

            Customer customer = customerRepo.searchByName("testFirstName").get(0);
            // update all attributes except for the ID
            customer.setFirstName("testFirstNameUpdated");
            customer.setLastName("testLastNameUpdated");
            customer.setCpr("989898-9898");
            customer.setPhoneNumber("12345678901");
            customer.setEmail("test.updated@junit.minecraft.net");
            customer.setBirthdate("1234-07-08");
            customer.setAddress("testAddress1Updated");
            customer.setAddress2("testAddress2Updated");
            customer.setGender("testGondor"); // limited options as the column is a VARCHAR of 10

            // when
            queryResult = customerRepo.updateCustomer(customer);

            foundCustomer = customerRepo.searchByName(customer.getFirstName()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added Customer is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(customer.toString()).isEqualTo(foundCustomer.toString());

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(4)
        @DisplayName("deleteCustomer()")
        public void customerRepoDeleteCustomerTest() throws Exception
        {
            System.out.println("Customer Repo - Test 4 : Delete Customer Test");

            if (!testVerification)
            {
                System.out.println("Update Customer test failed. Skipping the Delete Customer test...");
                // throw a more descriptive exception message
                assertThat("Customer Repo - Update Customer Test to be ").isEqualTo("Successful");
            }

            // If the Update has passed, we can test the Delete
            // given
            Customer customer = customerRepo.searchByName("testFirstNameUpdated").get(0); // Searching for an updated value
            List<Customer> foundCustomerList;

            // when
            boolean customerDeleted = customerRepo.deleteCustomer(customer.getId());
            foundCustomerList = customerRepo.searchByName("testFirstNameUpdated"); // checking if the entry still exists in the DB

            // then
            assertThat(customerDeleted).isTrue();
            assertThat(foundCustomerList).isEmpty();
        }

        @Test
        @DisplayName("fetchAll()")
        public void customerRepoFetchAllTest() throws Exception
        {
            List<Customer> customerList = customerRepo.fetchAll();
            assertThat(customerList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchByID()")
        public void customerRepoFetchByIDTest() throws Exception
        {
            Customer customer = customerRepo.fetchByID(2000);
            assertThat(customer).isNotNull();
        }

        @Test
        @DisplayName("searchByCpr")
        public void customerReposearchByCprTest() throws Exception
        {
            List<Customer> customerList = customerRepo.searchByCpr("");
            assertThat(customerList).isNotEmpty();
        }
    }
    //endregion

    //region Customer Service
    @Autowired
    private CustomerService customerService;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class CustomerServiceTests
    {
        // In the Customer Use Case, the Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void customerServiceLoads()
        {
            // validate if the Customer Service layer has loaded
            assertThat(customerService).isNotNull();
        }
    }
    //endregion
}