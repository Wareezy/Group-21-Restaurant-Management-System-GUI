package za.ac.cput.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Waiter implements Serializable {
    @Id
    private String waiterId;
    private String email;
    private String cellPhoneNumber;

    @Embedded
    private Name name;


    protected Waiter(){}

    private Waiter(Builder builder)
    {
        this.waiterId=builder.waiterId;
        this.email=builder.email;
        this.cellPhoneNumber=builder.cellPhoneNumber;
        this.name=builder.name;
    }

    public String getWaiterId(){return waiterId;}
    public String getEmail(){return email;}
    public String getCellPhoneNumber(){return cellPhoneNumber;}
    public Name getName(){return name;}

    @Override
    public String toString() {
        return "Waiter{" +
                "waiterId='" + waiterId + '\'' +
                ", email='" + email + '\'' +
                ", cellPhoneNumber='" + cellPhoneNumber + '\'' +
                ", name=" + name +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o) return true;
        if(o==null || getClass() !=o.getClass()) return false;
        Waiter waiter=(Waiter) o;
        return waiterId.equals(waiter.waiterId) && email.equals(waiter.email) && cellPhoneNumber.equals(waiter.cellPhoneNumber) && name.equals(waiter.name);
    }

    @Override
    public int hashCode(){return Objects.hash(waiterId,email,cellPhoneNumber,name);}

    public static class Builder{
        private String waiterId,email,cellPhoneNumber;
        private Name name;

        public Builder waiterId(String waiterId)
        {
            this.waiterId=waiterId;
            return this;
        }

        public Builder email(String email)
        {
            this.email=email;
            return this;
        }
        public Builder cellPhoneNumber(String cellPhoneNumber)
        {
            this.cellPhoneNumber=cellPhoneNumber;
            return this;
        }

        public Builder name(Name name)
        {
            this.name=name;
            return this;
        }

        public Waiter build(){return new Waiter(this);}
    }
}

