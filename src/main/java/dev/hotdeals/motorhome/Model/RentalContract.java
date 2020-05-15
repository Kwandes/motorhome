package dev.hotdeals.motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.StringJoiner;

@Entity
public class RentalContract
{
    @Id
    private int id;
    private int customer_id;
    private int rv_id;
    private String dateSigned;
    private String dateStart;
    private String dateEnd;
    private String addressDropoff;
    private String addressPickup;
    private int basePrice;
    private int finalPrice;
    private int kmDriven;
    private String status;
    private int employee_id;

    public RentalContract()
    {
    }

    public String toString()
    {
        return id + " " + customer_id + " " + rv_id + " " + dateSigned + " " + dateStart +
                " " + dateEnd + " " + addressDropoff + " " + addressPickup + " " + basePrice +
                " " + finalPrice + " " + kmDriven + " " + status + " " + employee_id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }

    public int getRv_id()
    {
        return rv_id;
    }

    public void setRv_id(int rv_id)
    {
        this.rv_id = rv_id;
    }

    public String getDateSigned()
    {
        return dateSigned;
    }

    public void setDateSigned(String dateSigned)
    {
        this.dateSigned = dateSigned;
    }

    public String getDateStart()
    {
        return dateStart;
    }

    public void setDateStart(String dateStart)
    {
        this.dateStart = dateStart;
    }

    public String getDateEnd()
    {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd)
    {
        this.dateEnd = dateEnd;
    }

    public String getAddressDropoff()
    {
        return addressDropoff;
    }

    public void setAddressDropoff(String addressDropoff)
    {
        this.addressDropoff = addressDropoff;
    }

    public String getAddressPickup()
    {
        return addressPickup;
    }

    public void setAddressPickup(String addressPickup)
    {
        this.addressPickup = addressPickup;
    }

    public int getBasePrice()
    {
        return basePrice;
    }

    public void setBasePrice(int basePrice)
    {
        this.basePrice = basePrice;
    }

    public int getFinalPrice()
    {
        return finalPrice;
    }

    public void setFinalPrice(int finalPrice)
    {
        this.finalPrice = finalPrice;
    }

    public int getKmDriven()
    {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven)
    {
        this.kmDriven = kmDriven;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public int getEmployee_id()
    {
        return employee_id;
    }

    public void setEmployee_id(int employee_id)
    {
        this.employee_id = employee_id;
    }
}