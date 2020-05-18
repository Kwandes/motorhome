package dev.hotdeals.motorhome.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "rental_contract_has_rv_extra")
@IdClass(RentalContractHasRvExtraID.class)
public class RentalContractHasRvExtra implements Serializable
{
    @Id
    private int rental_contract_id;
    @Id
    private int rv_extra_id;
    private int amount;

    public RentalContractHasRvExtra() {}

    public String toString()
    {
        return rental_contract_id + " " + rv_extra_id + " " + amount;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public int getRental_contract_id()
    {
        return rental_contract_id;
    }

    public void setRental_contract_id(int rental_contract_id)
    {
        this.rental_contract_id = rental_contract_id;
    }

    public int getRv_extra_id()
    {
        return rv_extra_id;
    }

    public void setRv_extra_id(int rv_extra_id)
    {
        this.rv_extra_id = rv_extra_id;
    }
}
