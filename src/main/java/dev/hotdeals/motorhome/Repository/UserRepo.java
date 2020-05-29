package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    JdbcTemplate template;

    public UserRepo() {
    }

    // Returns the User with the provided ID.
    public User fetchByID ( int userID )
    {
        String query = "SELECT * FROM user WHERE id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user;

        try
        {
            user = template.queryForObject(query, rowMapper, userID);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve User of ID " + userID + ".");
            System.out.println(e);
            user = null;
        }

        return user;
    }

    // Returns the User with the provided Username.
    public User fetchByUsername ( String username )
    {
        String query = "SELECT * FROM user WHERE username = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user;

        try
        {
            user = template.queryForObject(query, rowMapper, username);
        } catch (EmptyResultDataAccessException e)
        {
            System.out.println("Failed to retrieve the User with the Username of : " + username );
            System.out.println(e);
            user = null;
        }

        return user;
    }

    // Returns the User with the provided Employee ID.
    public User fetchByEmployeeID ( int employeeID )
    {
        String query = "SELECT * FROM user WHERE employee_id = ?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        User user;

        try
        {
            user = template.queryForObject(query, rowMapper, employeeID);
        } catch ( EmptyResultDataAccessException e )
        {
            System.out.println("Failed to retrieve the User with Employee_ID of : " + employeeID);
            System.out.println(e);
            user = null;
        }

        return user;
    }

    // Returns a list of all Users within the Database.
    public List<User> fetchAll ()
    {
        String query = "SELECT * FROM user";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> userList;

        try
        {
            userList = template.query(query, rowMapper);
        } catch ( EmptyResultDataAccessException e )
        {
            System.out.println("Failed to retrieve the User List from the DB");
            System.out.println(e);
            userList = new ArrayList<>();
        }

        return userList;
    }

    // Returns a list of all Users of which Username contains the given String.
    public List<User> searchByUsername ( String username )
    {
        String query = "SELECT * FROM user WHERE username LIKE CONCAT ('%', ?, '%')";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        List<User> userList;

        try
        {
            userList = template.query(query, rowMapper, username);
        } catch ( EmptyResultDataAccessException e )
        {
            System.out.println("Failed to retrieve the User List from the Database.");
            System.out.println(e);
            userList = new ArrayList<>();
        }

        return userList;
    }

    // Adds a given User to the Database.
    public boolean addUser( User user )
    {
        String query = "INSERT INTO user( username, password, employee_id ) VALUES ( ?, ?, ? )";

        int rowsAffected = template.update(query, user.getUsername(), user.getPassword(), user.getEmployee_id());
        boolean status = rowsAffected > 0;

        return status;
    }

    // Updates a specific User with the values provided in the user object.
    public boolean updateUser( User user )
    {
        String query = "UPDATE user SET username = ?, password = ?, employee_id = ? WHERE id = ?";

        int rowsAffected = template.update(query, user.getUsername(), user.getPassword(), user.getEmployee_id(), user.getId());
        boolean status = rowsAffected > 0;

        return status;
    }

    // Deletes the User with the provided ID.
    public boolean deleteUser( int userID )
    {
        String query = "DELETE FROM user WHERE id = ?";

        int rowsAffected = template.update(query, userID);
        boolean status = rowsAffected > 0;

        return status;
    }
}
