package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.beans.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

@Repository
public class RVRepo
{
    @Autowired
    JdbcTemplate template;

    public RVRepo() {}

    public RV fetchByID(int recreationalVehicleID)
    {
        String query = "SELECT * FROM rv WHERE id = ?;";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        RV rvToReturn= template.queryForObject(query, rvRowMapper, recreationalVehicleID);
        return rvToReturn;
    }

    public List<RV> fetchAll()
    {
        String query = "SELECT * FROM rv;";
        RowMapper<RV> rvRowmapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowmapper);
        return listToReturn;
    }

    public boolean addRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    public boolean updateRV(RV recreationalVehicle)
    {
        boolean status = false;
        return status;
    }

    public boolean deleteRV(int recreationalVehicleID)
    {
        boolean status = false;
        return status;
    }
}
