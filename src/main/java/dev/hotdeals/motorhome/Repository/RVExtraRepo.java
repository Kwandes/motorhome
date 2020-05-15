package dev.hotdeals.motorhome.Repository;

import dev.hotdeals.motorhome.Model.RV;
import dev.hotdeals.motorhome.Model.RVExtra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RVExtraRepo
{
    @Autowired
    JdbcTemplate template;

    public RV fetchByID(int extraID)
    {
        RV rvExtraToReturn= null;
        return rvExtraToReturn;
    }

    private List<RVExtra> fetchAll()
    {
        List<RVExtra> listToReturn = null;
        return listToReturn;
    }

    private boolean addExtra(RVExtra extra)
    {
        boolean status = false;
        return status;
    }

    private boolean updateExtra(RVExtra extra)
    {
        boolean status = false;
        return status;
    }

    private boolean deleteExtra(int extraID)
    {
        boolean status = false;
        return status;
    }
}
