package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.Employee;
import dev.hotdeals.motorhome.Repository.EmployeeRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeUseCaseTests
{
    private static boolean testCondition = false;

    @Autowired
    EmployeeRepo employeeRepo;

    @Test
    @DisplayName("Fetch employee object by ID from DB")
    public void employeeRepoFetchById()
    {
        //given - User Of ID 1000 exists
        Employee employeeTestObject = null;
        int employeeIdToRetrieve = 1000;
        //when
        employeeTestObject = employeeRepo.fetchByID(employeeIdToRetrieve);
        //then
        assertThat(employeeTestObject).isNotNull();
    }

    @Test
    @DisplayName("Fetch all employees from DB")
    public void employeeRepoFetchAll()
    {
        // given - the DB is not empty
        List<Employee> employeesTestList = null;
        // when
        employeesTestList = employeeRepo.fetchAll();
        // then
        assertThat(employeesTestList).isNotNull();
    }

    @Test
    @Order(1)
    @DisplayName("Search for a employee by name")
    public void employeeRepoSearchByNameTest()
    {
        // given - name exists
        String name = "";
        // when
        List<Employee> employeesReturnedFromSearch = null;
        employeesReturnedFromSearch = employeeRepo.searchByName(name);
        // then
        testCondition = !employeesReturnedFromSearch.isEmpty();
        assertThat(employeesReturnedFromSearch).isNotEmpty();
    }

    @Test
    @Order(2)
    @DisplayName("Add a employee to the DB")
    public void employeeRepoAddEmployee()
    {
        // check the previous test result
        if (!testCondition)
        {
            System.out.println("Search test failed. Skipping the Add Employee test...");
            assertThat(testCondition).isTrue();
        }

        Employee employee = new Employee();
        employee.setFirstName("TestName");
        employee.setLastName("TestLastName");
        employee.setPosition("Tester");
        employee.setGender("TestGender");

        testCondition = employeeRepo.addEmployee(employee);

        assertThat(testCondition).isTrue();
    }

    @Test
    @Order(3)
    @DisplayName("Change a employee's information from the DB")
    public void employeeRepoUpdateEmployee()
    {
        // check previous test result
        if (!testCondition)
        {
            System.out.println("Search test failed. Skipping Update test...");
            assertThat(testCondition).isTrue();
        }
        // given - the ID exist
        String originalFirstName = "TestName";
        String editedFirstName = "NameTest";
        Employee testEmployee = employeeRepo.searchByName(originalFirstName).get(0);
        testEmployee.setFirstName(editedFirstName);

        testCondition = employeeRepo.updateEmployee(testEmployee);
        Employee updatedEmployee = employeeRepo.searchByName(editedFirstName).get(0);

        assertThat(testCondition).isTrue();
        assertThat(testEmployee.toString()).isEqualTo(updatedEmployee.toString());
    }


    @Test
    @Order(4)
    @DisplayName("Delete employee from DB using Id")
    public void employeeRepoDeleteEmployeeTest()
    {
        // check previous test result
        if (!testCondition)
        {
            System.out.println("Search test failed. Skipping the Delete test...");
            assertThat(testCondition).isTrue();
        }
        // given - the Employee exists
        String employeeNameToDelete = "NameTest"; //name used to search for the employee to delete
        Employee testEmployeeDelete = employeeRepo.searchByName(employeeNameToDelete).get(0); //the Employee we want to delete
        int employeeIdToDelete = testEmployeeDelete.getId(); //getting the ID of the employee to delete

        testCondition = employeeRepo.deleteEmployee(employeeIdToDelete);

        assertThat(testCondition).isTrue();
    }
}
