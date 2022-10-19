package za.ac.cput.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {

    private String firstName;
    private String middleName;
    private String lastName;


    private Name(Builder builder){
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
    }

    protected Name(){
    }

    public String getFirstName() {return firstName;}

    public String getMiddleName() {return middleName;}

    public String getLastName() {return lastName;}

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstName.equals(name.firstName) && middleName.equals(name.middleName) && lastName.equals(name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder{
        private String firstName, middleName, lastName;

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(String middleName){
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder copy(Name name){
            this.firstName = name.firstName;
            this.middleName = name.middleName;
            this.lastName = name.lastName;
            return this;
        }

        public Name build(){
            return new Name(this);
        }

    }
}

