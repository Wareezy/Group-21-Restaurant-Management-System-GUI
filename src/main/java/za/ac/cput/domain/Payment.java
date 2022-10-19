package za.ac.cput.domain;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Payment implements Serializable {
    @Id
    private String cardNumber;

    private String paymentAmount;

    @Embedded
    private Order order;

    protected Payment(){

    }

    private Payment(Builder builder){
        this.cardNumber = builder.cardNumber;
        this.paymentAmount = builder.paymentAmount;
        this.order = builder.order;
    }

    public String getCardNumber() {return cardNumber;}

    public String getPaymentAmount() {return paymentAmount;}

    public Order getOrder() {return order;}

    @Override
    public String toString() {
        return "Payment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", paymentAmount='" + paymentAmount + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return cardNumber.equals(payment.cardNumber) && paymentAmount.equals(payment.paymentAmount) && order.equals(payment.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, paymentAmount, order);
    }

    public static class Builder{
        private String cardNumber, paymentAmount;
        private Order order;

        public Builder cardNumber(String cardNumber){
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder paymentAmount(String paymentAmount){
            this.paymentAmount = paymentAmount;
            return this;
        }

        public Builder order(Order order){
            this.order = order;
            return this;
        }

        public Payment build(){
            return new Payment(this);
        }
    }
}

