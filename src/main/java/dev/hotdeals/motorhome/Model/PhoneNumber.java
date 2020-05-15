package dev.hotdeals.motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneNumber
{
    @Id
    private String number;
    @Id
    private int customer_id;

    public PhoneNumber()
    {

    }

    public String toString()
    {
        return number + " " + customer_id;
    }

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public int getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(int customer_id)
    {
        this.customer_id = customer_id;
    }
}
