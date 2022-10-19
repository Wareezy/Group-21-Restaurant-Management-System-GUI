package za.ac.cput.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderTable implements Serializable {
    @Id
    private String orderTableId;

    private String numberOfGuest;

    @Embedded
    private Order order;

    protected OrderTable(){

    }

    private OrderTable(Builder builder){
        this.orderTableId = builder.orderTableId;
        this.numberOfGuest = builder.numberOfGuest;
        this.order = builder.order;
    }

    public String getOrderTableId() {return orderTableId;}

    public String getNumberOfGuest() {return numberOfGuest;}

    public Order getOrder() {return order;}

    @Override
    public String toString() {
        return "OrderTable{" +
                "orderTableId='" + orderTableId + '\'' +
                ", numberOfGuest='" + numberOfGuest + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderTable orderTable = (OrderTable) o;
        return orderTableId.equals(orderTable.orderTableId) && numberOfGuest.equals(orderTable.numberOfGuest) && order.equals(orderTable.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderTableId, numberOfGuest, order);
    }

    public static class Builder{
        private String orderTableId, numberOfGuest;
        private Order order;

        public Builder orderTableId(String orderTableId){
            this.orderTableId = orderTableId;
            return this;
        }

        public Builder numberOfGuest(String numberOfGuest){
            this.numberOfGuest = numberOfGuest;
            return this;
        }

        public Builder order(Order order){
            this.order = order;
            return this;
        }

        public OrderTable build(){
            return new OrderTable(this);
        }
    }
}

