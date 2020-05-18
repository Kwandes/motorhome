package dev.hotdeals.motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "phone_number") // without this the table name is assumed to be 'PhoneNumber'
@IdClass(PhoneNumberID.class)
public class PhoneNumber implements Serializable    // implements Serizable as per composite Key requirement
{
    // define the attributes to be used as primary keys
    @Id
    private String number;
    @Id
    private int customer_id;

    // required constructor as per entity and composite key requirement
    public PhoneNumber()
    {

    }

    // everything below is an extra and not required for entity nor the composite key
    // getters and setters are needed though due to attributes being private

    public PhoneNumber(String number, int customer_id)
    {
        this.number = number;
        this.customer_id = customer_id;
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
