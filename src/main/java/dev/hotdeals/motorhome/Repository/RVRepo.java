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
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public boolean addRV(RV recreationalVehicle)
    {
        String query = "INSERT INTO rv ( brand, model, color, rv_type, price ) VALUES ( ?, ?, ?, ?, ? )";
        int rowsAffected = template.update(query, recreationalVehicle.getBrand(), recreationalVehicle.getModel(), recreationalVehicle.getColor(),
                           recreationalVehicle.getRvType(), recreationalVehicle.getPrice());
        boolean status = rowsAffected > 0;
        return status;
    }

    public boolean updateRV(RV recreationalVehicle)
    {
        String query = "UPDATE rv SET brand = ?, model = ?, color = ?, fuel_status = ?, km_driven = ?, rv_type = ?," +
                       " price = ?, requires_cleaning = ?, requires_maintenance = ?, requires_further_service = ?," +
                       " is_rented = ? WHERE id = ?;";
        int rowsAffected = template.update(query, recreationalVehicle.getBrand(), recreationalVehicle.getModel(), recreationalVehicle.getColor(),
                recreationalVehicle.getFuelStatus(), recreationalVehicle.getKmDriven(), recreationalVehicle.getRvType(), recreationalVehicle.getPrice(),
                recreationalVehicle.getRequiresCleaning(), recreationalVehicle.getRequiresMaintenance(), recreationalVehicle.getRequiresFurtherService(),
                recreationalVehicle.getIsRented());
        boolean status = rowsAffected > 0;
        return status;
    }

    public boolean deleteRV(int recreationalVehicleID)
    {
        String query = "DELETE FROM rv WHERE id = ?";
        int rowsAffected = template.update(query, recreationalVehicleID);
        boolean status = rowsAffected > 0;
        return status;
    }

    public List<RV> fetchAvailable()
    {
        String query = "SELECT * FROM rv WHERE is_rented = 0 AND requires_cleaning = 0 AND requires_maintenance = 0 AND requires_further_service = 0";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public List<RV> fetchRented()
    {
        String query = "SELECT * FROM rv WHERE is_rented = 1";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public List<RV> fetchRequiresCleaning()
    {
        String query = "SELECT * FROM rv WHERE requires_cleaning = 1";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public List<RV> fetchRequiresMaintenance()
    {
        String query = "SELECT * FROM rv WHERE requires_maintenance = 1 OR requires_further_service = 1";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public List<RV> sortByPrice()
    {
        String query = "SELECT * FROM rv ORDER BY price DESC";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper);
        return listToReturn;
    }

    public List<RV> searchByModel(String model)
    {
        String query = "SELECT * FROM rv WHERE model LIKE CONCAT('%', ?, '%')";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper, model);
        return listToReturn;
    }

    public List<RV> searchByBrand(String brand)
    {
        String query = "SELECT * FROM rv WHERE brand LIKE CONCAT('%', ?, '%')";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper, brand);
        return listToReturn;
    }

    public List<RV> searchByID(int recreationalVehicleID)
    {
        String query = "SELECT * FROM rv WHERE id LIKE CONCAT('%', ?, '%')";
        RowMapper<RV> rvRowMapper = new BeanPropertyRowMapper<>(RV.class);
        List<RV> listToReturn = template.query(query, rvRowMapper, recreationalVehicleID);
        return listToReturn;
    }

}
