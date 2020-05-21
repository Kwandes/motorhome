package dev.hotdeals.motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee
{
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String position;    //Owner,Sales assistant, Cleaning staff, Auto-mechanic, Bookkeeper.
    private String gender;      //Male, Female, Other

    public Employee()
    {
    }

    public String toString()
    {
        return id + " " + firstName + " " + lastName + " " + position + " " + gender;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }
}
