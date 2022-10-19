package za.ac.cput.domain;
import com.sun.istack.NotNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class ChefAddress implements Serializable {
    @NotNull
    @Id
    private String chefId;
    @NotNull

    @Embedded
    private Address address;
    protected ChefAddress(){}

    private ChefAddress (Builder builder){

        this.chefId = builder.chefId;
        this.address = builder.address;

    }
    public String getChefId() {
        return chefId;
    }

    public Address getAddress() {
        return address;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChefAddress that = (ChefAddress) o;
        return chefId.equals(that.chefId) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chefId, address);
    }

    @Override
    public String toString() {
        return "ChefAddress{" +
                "chefId='" + chefId + '\'' +
                ", address=" + address +
                '}';
    }
    public static class Builder {
        private String chefId;
        private Address address;

        public Builder chefId(String chefId) {
            this.chefId = chefId;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }
        public Builder copy(ChefAddress chefAddress){
            this.chefId = chefAddress.chefId;
            this.address = chefAddress.address;
            return this;
        }

        public ChefAddress build(){
            return new ChefAddress(this);
        }
    }

}

