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
public class LoginTests {

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
        @DisplayName("fetchByID()")
        public void userRepoFetchByIDTest()
        {
            User user = userRepo.fetchByID(5000);

            assertThat(user).isNotNull();
        }

        @Test
        @DisplayName("fetchByEmployeeID()")
        public void userRepoFetchByEmployeeIDTest()
        {
            User user = userRepo.fetchByEmployeeID(1000);

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
        @DisplayName("searchByUsername()")
        @Order(1)
        public void userRepoSearchByUsernameTest()
        {
            List<User> userList = userRepo.searchByUsername("");

            testVerification = false;
            assertThat(userList).isNotEmpty();
            testVerification = true;
        }

        @Test
        @DisplayName("addUser()")
        @Order(2)
        public void userRepoAddUserTest()
        {
            if (!testVerification)
            {
                assertThat("Search User by Username - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            // given
            User user = new User();
            user.setUsername("testUsername");
            user.setPassword("testPassword");
            user.setEmployee_id(1000);

            // when
            boolean queryResult = userRepo.addUser(user);

            // then
            assertThat(queryResult).isTrue();

            testVerification = true;
        }

        @Test
        @DisplayName("fetchByUsername()")
        @Order(3)
        public void userRepoFetchByUsername()
        {
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
            if (!testVerification)
            {
                assertThat("Fetch User by ID - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            // given
            User user = userRepo.fetchByUsername("testUsername");
            user.setUsername("testUpdatedUsername");

            // when
            boolean queryReult = userRepo.updateUser(user);

            // then
            assertThat(queryReult).isTrue();

            testVerification = true;
        }

        @Test
        @DisplayName("deleteUser()")
        @Order(5)
        public void userRepoDeleteUser()
        {
            if (!testVerification)
            {
                assertThat("Update User - Unsuccessful").isEqualTo("Successful");
            }

            // given
            int id = userRepo.fetchByUsername("testUpdatedUsername").getId();

            // when
            boolean queryResult = userRepo.deleteUser(id);

            // then
            assertThat(queryResult).isTrue();
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class UserServiceTests
    {
        @Test
        @DisplayName("fetchByID()")
        public void userServiceFetchByIDTest()
        {
            User user = userService.fetchByID(5000);

            assertThat(user).isNotNull();
        }

        @Test
        @DisplayName("fetchByEmployeeID()")
        public void userServiceFetchByEmployeeIDTest()
        {
            User user = userService.fetchByEmployeeID(1000);

            assertThat(user).isNotNull();
        }

        @Test
        @DisplayName("fetchAll()")
        public void userServiceFetchAllTest()
        {
            List<User> userList = userService.fetchAll();

            assertThat(userList).isNotEmpty();
        }

        @Test
        @DisplayName("searchByUsername()")
        @Order(1)
        public void userServiceSearchByUsernameTest()
        {
            List<User> userList = userService.searchByUsername("");

            testVerification = false;
            assertThat(userList).isNotEmpty();
            testVerification = true;
        }

        @Test
        @DisplayName("addUser()")
        @Order(2)
        public void userServiceAddUserTest()
        {
            if (!testVerification)
            {
                assertThat("Search User by Username - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            // given
            User user = new User();
            user.setUsername("testUsername");
            user.setPassword("testPassword");
            user.setEmployee_id(1000);

            // when
            boolean queryResult = userService.addUser(user);

            // then
            assertThat(queryResult).isTrue();

            testVerification = true;
        }

        @Test
        @DisplayName("fetchByUsername()")
        @Order(3)
        public void userServiceFetchByUsername()
        {
            if (!testVerification)
            {
                assertThat("Add User - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            User user = userService.fetchByUsername("testUsername");

            assertThat(user).isNotNull();

            testVerification = true;
        }

        @Test
        @DisplayName("updateUser()")
        @Order(4)
        public void userServiceUpdateUser()
        {
            if (!testVerification)
            {
                assertThat("Fetch User by ID - Unsuccessful").isEqualTo("Successful");
            }
            testVerification = false;

            // given
            User user = userService.fetchByUsername("testUsername");
            user.setUsername("testUpdatedUsername");

            // when
            boolean queryReult = userService.updateUser(user);

            // then
            assertThat(queryReult).isTrue();

            testVerification = true;
        }

        @Test
        @DisplayName("deleteUser()")
        @Order(5)
        public void userServiceDeleteUser()
        {
            if (!testVerification)
            {
                assertThat("Update User - Unsuccessful").isEqualTo("Successful");
            }

            // given
            int id = userService.fetchByUsername("testUpdatedUsername").getId();

            // when
            boolean queryResult = userRepo.deleteUser(id);

            // then
            assertThat(queryResult).isTrue();
        }
    }
}
