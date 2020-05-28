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

    public User fetchByID ( int userID )
    {
        return userRepo.fetchByID( userID );
    }

    public User fetchByUsername ( String username )
    {
        return userRepo.fetchByUsername( username );
    }

    public User fetchByEmployeeID ( int employeeID )
    {
        return userRepo.fetchByEmployeeID ( employeeID );
    }

    public List<User> fetchAll ()
    {
        return userRepo.fetchAll();
    }

    public List<User> searchByUsername ( String username )
    {
        return userRepo.searchByUsername( username );
    }

    public boolean addUser ( User user )
    {
        return userRepo.addUser( user );
    }

    public boolean updateUser ( User user )
    {
        return userRepo.updateUser( user );
    }

    public boolean deleteUser ( int userID )
    {
        return userRepo.deleteUser( userID );
    }

}
