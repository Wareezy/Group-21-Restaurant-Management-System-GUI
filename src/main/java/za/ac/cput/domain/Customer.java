package za.ac.cput.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Customer implements Serializable {

    @Id
    private String customerId;

    private String email;
    private String cellPhoneNumber;

    @Embedded
    private Name name;

    protected Customer(){

    }

    private Customer(Builder builder){
        this.customerId = builder.customerId;
        this.email = builder.email;
        this.cellPhoneNumber=builder.cellPhoneNumber;
        this.name = builder.name;
    }

    public String getCustomerId() {return customerId;}

    public String getEmail() {return email;}
    public String getCellPhoneNumber() {return cellPhoneNumber;}

    public Name getName() {return name;}

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", email='" + email + '\'' +
                ", cellPhoneNumber='" + cellPhoneNumber + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) && email.equals(customer.email) && cellPhoneNumber.equals(customer.cellPhoneNumber) && name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email,cellPhoneNumber, name);
    }

    public static class Builder{
        private String customerId, email,cellPhoneNumber;
        private Name name;

        public Builder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder cellPhoneNumber(String cellPhoneNumber){
            this.cellPhoneNumber = cellPhoneNumber;
            return this;
        }

        public Builder name(Name name){
            this.name = name;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}

