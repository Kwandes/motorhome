/*
    An Composite Key class for the PhoneNumber model
    Necessary due to the Database table having a composite primary key
 */

package dev.hotdeals.motorhome.Model;

import java.io.Serializable;
import java.util.Objects;

// has to implement Serializable in order to work as a composite key
public class PhoneNumberID implements Serializable
{
    // mirrored PhoneNumber ID attributes. Other attributes don't have to be mirrored
    private String number;
    private int customer_id;

    // Constructors as per composite Key requirements
    public PhoneNumberID() {}

    public PhoneNumberID(String number, int customer_id)
    {
        this.number = number;
        this.customer_id = customer_id;
    }

    // required definition of an equals() method.
    // returns a comparison of the 2 composite key attributes
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhoneNumberID phoneNumberID = (PhoneNumberID) obj;
        return number.equals(phoneNumberID.number) && customer_id == phoneNumberID.customer_id;
    }

    // required definition of the hashCode() method, used for comparing the 2 classes
    // returns a hashed version of the attributes
    @Override
    public int hashCode()
    {
        return Objects.hash(number, customer_id);
    }
}
