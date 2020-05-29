package dev.hotdeals.motorhome.Service;

import dev.hotdeals.motorhome.Model.User;
import dev.hotdeals.motorhome.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    // Returns the User with the provided ID.
    public User fetchByID ( int userID )
    {
        return userRepo.fetchByID( userID );
    }

    // Returns the User with the provided Username.
    public User fetchByUsername ( String username )
    {
        return userRepo.fetchByUsername( username );
    }

    // Returns the User with the provided Employee ID.
    public User fetchByEmployeeID ( int employeeID )
    {
        return userRepo.fetchByEmployeeID ( employeeID );
    }

    // Returns a list of all Users within the Database.
    public List<User> fetchAll ()
    {
        return userRepo.fetchAll();
    }

    // Returns a list of all Users of which Username contains the given String.
    public List<User> searchByUsername ( String username )
    {
        return userRepo.searchByUsername( username );
    }

    // Adds a given User to the Database.
    public boolean addUser ( User user )
    {
        return userRepo.addUser( user );
    }

    // Updates a specific User with the values provided in the user object.
    public boolean updateUser ( User user )
    {
        return userRepo.updateUser( user );
    }

    // Deletes the User with the provided ID.
    public boolean deleteUser ( int userID )
    {
        return userRepo.deleteUser( userID );
    }

}
