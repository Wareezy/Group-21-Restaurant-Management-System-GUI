package za.ac.cput.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Chef implements Serializable {
    @Id
    private String chefId;
    private String email;
    private String cellPhoneNumber;

    @Embedded
    private Name name;

    protected Chef() {
    }

    private Chef(Builder builder) {
        this.chefId = builder.chefId;
        this.email = builder.email;
        this.cellPhoneNumber = builder.cellPhoneNumber;
        this.name = builder.name;
    }

    public String getChefId() {
        return chefId;
    }

    public String getEmail() {
        return email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "chefId='" + chefId + '\'' +
                ", email='" + email + '\'' +
                ", cellPhoneNumber='" + cellPhoneNumber + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chef chef = (Chef) o;
        return chefId.equals(chef.chefId) && email.equals(chef.email) && cellPhoneNumber.equals(chef.cellPhoneNumber) && name.equals(chef.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chefId, email, cellPhoneNumber, name);
    }

    public static class Builder {
        private String chefId, cellPhoneNumber, email;
        private Name name;

        public Builder chefId(String chefId) {
            this.chefId = chefId;
            return this;
        }

        public Builder cellPhoneNumber(String cellPhoneNumber) {
            this.cellPhoneNumber = cellPhoneNumber;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(Name name) {
            this.name = name;
            return this;
        }

        public Chef build() {
            return new Chef(this);
        }
    }
}

