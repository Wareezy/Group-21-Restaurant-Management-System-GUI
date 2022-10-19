package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

//2199099839
//Nawaaz Amien
@Entity
public class WaiterAddress implements Serializable {
    @NotNull
    @Id
    private String waiterId;
    @NotNull

    @Embedded
    private Address address;
    protected WaiterAddress(){}

    private WaiterAddress(Builder builder){

        this.waiterId = builder.waiterId;
        this.address = builder.address;

    }
    public String getWaiterId() {
        return waiterId;
    }

    public Address getAddress() {
        return address;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaiterAddress that = (WaiterAddress) o;
        return waiterId.equals(that.waiterId) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(waiterId, address);
    }

    @Override
    public String toString() {
        return "WaiterAddress{" +
                "waiterId='" + waiterId + '\'' +
                ", address=" + address +
                '}';
    }

    public static class Builder {
        private String waiterId;
        private Address address;

        public Builder waiterId(String waiterId) {
            this.waiterId = waiterId;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }
        public Builder copy(WaiterAddress waiterAddress){
            this.waiterId = waiterAddress.waiterId;
            this.address = waiterAddress.address;
            return this;
        }

        public WaiterAddress build(){
            return new WaiterAddress(this);
        }
    }
}
