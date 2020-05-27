package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Repository.EmployeeRepo;
import dev.hotdeals.motorhome.Service.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeUseCaseTests
{
    @Autowired
    EmployeeRepo employeeRepo;

    public static boolean testVerification; // holds status of the previous test in the series

    //region Employee Repo
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class EmployeeRepoTests
    {
        @Test
        public void employeeRepoLoads()
        {
            // validate if the Employee Repository layer has loaded
            assertThat(employeeRepo).isNotNull();
        }

        @Test
        @Order(1)
        @DisplayName("searchByName()")
        public void employeeRepoSearchByNameTest() throws Exception
        {
            System.out.println("Employee Repo ordered tests of adding, updating and deleting data");
            System.out.println("Employee Repo - Test 1 : Search");
            List<Employee> employeeList = employeeRepo.searchByName("");

            testVerification = false; // if the list is empty, testVerification will be false ( test has failed ) else, it will be true

            assertThat(employeeList).isNotEmpty();
            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(2)
        @DisplayName("addEmployee()")
        public void employeeRepoAddEmployeeTest() throws Exception
        {
            System.out.println("Employee Repo - Test 2 : Add Employee Test");

            if (!testVerification)
            {
                System.out.println("Search test failed. Skipping the Add Employee test...");
                // throw a more descriptive exception message
                assertThat("Employee Repo - Search Employee Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Add
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            Employee foundEmployee;

            Employee employee = new Employee();
            // only the required parameters are set
            employee.setFirstName("testFirstName");
            employee.setLastName("testLastName");
            employee.setPosition("testPosition");
            employee.setGender("testGender");

            // when
            queryResult = employeeRepo.addEmployee(employee);
            foundEmployee = employeeRepo.searchByName(employee.getFirstName()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added Employee is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(employee.toString().replaceFirst("(\\d+)", "")).
                    isEqualTo(foundEmployee.toString().replaceFirst("(\\d+)", ""));

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(3)
        @DisplayName("updateEmployee()")
        public void employeeRepoUpdateEmployeeTest() throws Exception
        {
            System.out.println("Employee Repo - Test 3 : Update Employee Test");

            if (!testVerification)
            {
                System.out.println("Add Employee test failed. Skipping the Update Employee test...");
                // throw a more descriptive exception message
                assertThat("Employee Repo - Add Employee Test to be ").isEqualTo("Successful");
            }
            // If the Add has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            Employee foundEmployee;

            Employee employee = employeeRepo.searchByName("testFirstName").get(0);
            // update all attributes except for the ID
            employee.setFirstName("testFirstNameUpdated");
            employee.setLastName("testLastNameUpdated");
            employee.setPosition("testPosUpdated"); // limited options as the column is a VARCHAR of 15
            employee.setGender("testGondor"); // limited options as the column is a VARCHAR of 10

            // when
            queryResult = employeeRepo.updateEmployee(employee);

            foundEmployee = employeeRepo.searchByName(employee.getFirstName()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added Employee is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(employee.toString()).isEqualTo(foundEmployee.toString());

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @Order(4)
        @DisplayName("deleteEmployee()")
        public void employeeRepoDeleteEmployeeTest() throws Exception
        {
            System.out.println("Employee Repo - Test 4 : Delete Employee Test");

            if (!testVerification)
            {
                System.out.println("Update Employee test failed. Skipping the Delete Employee test...");
                // throw a more descriptive exception message
                assertThat("Employee Repo - Update Employee Test to be ").isEqualTo("Successful");
            }

            // If the Update has passed, we can test the Delete
            // given
            Employee employee = employeeRepo.searchByName("testFirstNameUpdated").get(0); // Searching for an updated value
            List<Employee> foundEmployeeList;

            // when
            boolean employeeDeleted = employeeRepo.deleteEmployee(employee.getId());
            foundEmployeeList = employeeRepo.searchByName("testFirstNameUpdated"); // checking if the entry still exists in the DB

            // then
            assertThat(employeeDeleted).isTrue();
            assertThat(foundEmployeeList).isEmpty();
        }

        @Test
        @DisplayName("fetchAll()")
        public void employeeRepoFetchAllTest() throws Exception
        {
            List<Employee> employeeList = employeeRepo.fetchAll();
            assertThat(employeeList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchByID()")
        public void employeeRepoFetchByIDTest() throws Exception
        {
            Employee employee = employeeRepo.fetchByID(1000);
            assertThat(employee).isNotNull();
        }

        @Test
        @DisplayName("searchByPosition")
        public void employeeRepoSearchByPositionTest() throws Exception
        {
            List<Employee> employeeList = employeeRepo.searchByPosition("");
            assertThat(employeeList).isNotEmpty();
        }
    }
    //endregion

    //region Employee Service
    @Autowired
    private EmployeeService employeeService;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class EmployeeServiceTests
    {
        // In the Employee Use Case, the Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void employeeServiceLoads()
        {
            // validate if the Employee Service layer has loaded
            assertThat(employeeService).isNotNull();
        }
    }
    //endregion
}