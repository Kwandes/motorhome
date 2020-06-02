package dev.hotdeals.motorhome;

import dev.hotdeals.motorhome.Model.User;
import dev.hotdeals.motorhome.Repository.UserRepo;
import dev.hotdeals.motorhome.Service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTests
{

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    public static boolean testVerification;

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class UserRepoTests
    {
        @Test
        public void userRepoLoads()
        {
            // validate if the User Repository layer has loaded
            assertThat(userRepo).isNotNull();
        }

        @Test
        @DisplayName("searchByUsername()")
        @Order(1)
        public void userRepoSearchByUsernameTest()
        {
            System.out.println("User Repo ordered tests of adding, updating and deleting data");
            System.out.println("User Repo - Test 1 : Search");
            List<User> userList = userRepo.searchByUsername("");

            testVerification = false; // if the list is empty, testVerification will be false ( test has failed ) else, it will be true

            assertThat(userList).isNotEmpty();
            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("addUser()")
        @Order(2)
        public void userRepoAddUserTest()
        {
            System.out.println("user Repo - Test 2 : Add User Test");

            if (!testVerification)
            {
                System.out.println("Search By Username test failed. Skipping the Add User test...");
                // throw a more descriptive exception message
                assertThat("User Repo - Search By Username Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Add
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            User foundUser;

            User user = new User();
            user.setUsername("testUsername");
            user.setPassword("testPassword");
            user.setEmployee_id(1000);

            // when
            queryResult = userRepo.addUser(user);
            foundUser = userRepo.searchByUsername(user.getUsername()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added User is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(user.toString().replaceFirst("(\\d+)", "")).
                    isEqualTo(foundUser.toString().replaceFirst("(\\d+)", ""));

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("fetchByUsername()")
        @Order(3)
        public void userRepoFetchByUsername()
        {
            // has to be tested after adding a test as it requires an exact username
            if (!testVerification)
            {
                assertThat("Add User - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            User user = userRepo.fetchByUsername("testUsername");

            assertThat(user).isNotNull();

            testVerification = true;
        }

        @Test
        @DisplayName("updateUser()")
        @Order(4)
        public void userRepoUpdateUser()
        {
            System.out.println("user Repo - Test 3 : Update User Test");

            if (!testVerification)
            {
                System.out.println("Add User test failed. Skipping the Update User test...");
                // throw a more descriptive exception message
                assertThat("User Repo - Add User Test to be ").isEqualTo("Successful");
            }

            // If the Search has passed, we can test the Update
            // given
            testVerification = false; // reset the variable to false
            boolean queryResult;
            User foundUser;

            User user = userRepo.searchByUsername("testUsername").get(0);
            user.setUsername("testUsernameUpdated");
            user.setPassword("testPasswordUpdated");
            user.setEmployee_id(1001); // will cause a fail if the employee doesn't exist

            // when
            queryResult = userRepo.updateUser(user);

            foundUser = userRepo.searchByUsername(user.getUsername()).get(0);

            // then
            assertThat(queryResult).isTrue(); // check if the query was successful

            // check if the added User is the same as the original
            // IDs needs to be removed from the toString() as the original doesn't have an ID
            assertThat(user.toString()).isEqualTo(user.toString());

            testVerification = true; // this will only be reached if the assert is successful
        }

        @Test
        @DisplayName("deleteUser()")
        @Order(5)
        public void userRepoDeleteUser()
        {
            System.out.println("RV Repo - Test 4 : Delete User Test");

            if (!testVerification)
            {
                System.out.println("Update User test failed. Skipping the Delete User test...");
                // throw a more descriptive exception message
                assertThat("User Repo - Update User Test to be ").isEqualTo("Successful");
            }

            // If the Update has passed, we can test the Delete
            // given
            User rv = userRepo.searchByUsername("testUsernameUpdated").get(0); // Searching for an updated value
            List<User> foundUserList;

            // when
            boolean userDeleted = userRepo.deleteUser(rv.getId());
            foundUserList = userRepo.searchByUsername("testUsernameUpdated"); // checking if the entry still exists in the DB

            // then
            assertThat(userDeleted).isTrue();
            assertThat(foundUserList).isEmpty();
        }

        @Test
        @DisplayName("fetchByID()")
        public void userRepoFetchByIDTest()
        {
            User user = userRepo.fetchByID(5000);

            assertThat(user).isNotNull();
        }

        @Test
        @DisplayName("fetchAll()")
        public void userRepoFetchAllTest()
        {
            List<User> userList = userRepo.fetchAll();

            assertThat(userList).isNotEmpty();
        }

        @Test
        @DisplayName("fetchByEmployeeID()")
        public void userRepoFetchByEmployeeIDTest()
        {
            User user = userRepo.fetchByEmployeeID(1000);

            assertThat(user).isNotNull();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class UserServiceTests
    {
        // In the Login Use Case, the User Service logic only contains Repository calls
        // Therefore, not all Service layer methods are tested

        @Test
        public void userServiceLoads()
        {
            // validate if the User Service layer has loaded
            assertThat(userService).isNotNull();
        }
    }
}
