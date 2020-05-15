package dev.hotdeals.motorhome.Model;

import java.io.Serializable;
import java.util.Objects;

public class RentalContractHasRvExtraID implements Serializable
{
    private int rental_contract_id;
    private int rv_extra_id;

    public RentalContractHasRvExtraID() {}

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RentalContractHasRvExtraID contractHasRvExtraID = (RentalContractHasRvExtraID) obj;
        return rental_contract_id == contractHasRvExtraID.rental_contract_id && rv_extra_id == contractHasRvExtraID.rv_extra_id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(rental_contract_id, rv_extra_id);
    }
}
