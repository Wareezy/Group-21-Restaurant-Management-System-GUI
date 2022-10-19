package za.ac.cput.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//2199099839
//Nawaaz Amien
@Embeddable
public class Address implements Serializable {
    private String unitNumber;
    private String complexName;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;

    private Address(Builder builder) {
        this.unitNumber = builder.unitNumber;
        this.complexName = builder.complexName;
        this.streetNumber = builder.streetNumber;
        this.streetName = builder.streetName;
        this.postalCode = builder.postalCode;
        this.city = builder.city;
    }

    protected Address() {}

    public String getUnitNumber() {return unitNumber;}

    public String getComplexName() {return complexName;}

    public String getStreetNumber() {return streetNumber;}

    public String getStreetName() {return streetName;}

    public String getPostalCode() {return postalCode;}

    public String getCity() {return city;}

    private void setUnitNumber(String unitNumber) {this.unitNumber = unitNumber;}
    private void setComplexName(String complexName) {this.complexName = complexName;}
    private void setStreetNumber(String streetNumber) {this.streetNumber = streetNumber;}
    private void setStreetName(String streetName) {this.streetName = streetName;}
    private void setPostalCode(String postalCode) {this.postalCode = postalCode;}
    private void setCity(String city) {this.city = city;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return unitNumber.equals(address.unitNumber) && complexName.equals(address.complexName) && streetNumber.equals(address.streetNumber) && streetName.equals(address.streetName) && postalCode.equals(address.postalCode) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitNumber, complexName, streetNumber, streetName, postalCode, city);
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "unitNumber='" + unitNumber + '\'' +
                ", complexName='" + complexName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city=" + city +
                '}';
    }

    public static class Builder {
        private String unitNumber, complexName, streetNumber, streetName, postalCode, city;

        public Builder unitNumber(String unitNumber) {
            this.unitNumber = unitNumber;
            return this;
        }

        public Builder complexName(String complexName) {
            this.complexName = complexName;
            return this;
        }

        public Builder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder copy(Address address) {
            this.unitNumber = address.unitNumber;
            this.complexName = address.complexName;
            this.streetNumber = address.streetNumber;
            this.streetName = address.streetName;
            this.postalCode = address.postalCode;
            this.city = address.city;
            return this;
        }

        public Address build(){return new Address(this);}
    }}