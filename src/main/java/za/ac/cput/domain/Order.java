package za.ac.cput.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Order {
    private String orderId;
    private String orderDetails;
    private String customerId;
    private String waiterId;
    private String chefId;



    private Order(Builder builder){
        this.orderId = builder.orderId;
        this.orderDetails = builder.orderDetails;
        this.customerId = builder.customerId;
        this.waiterId = builder.waiterId;
        this.chefId = builder.chefId;


    }

    protected Order(){
    }

    public String getOrderId() {return orderId;}
    public String getOrderDetails() {return orderDetails;}
    public String getCustomerId() {return customerId;}
    public String getWaiterId() {return waiterId;}
    public String getChefId() {return chefId;}


    private void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    private void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }
    private void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    private void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }
    private void setChefId(String chefId) {
        this.chefId = chefId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId.equals(order.orderId) && orderDetails.equals(order.orderDetails) && customerId.equals(order.customerId) && waiterId.equals(order.waiterId) && chefId.equals(order.chefId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderDetails,customerId,waiterId,chefId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDetails='" + orderDetails + '\'' +
                ", customerId='" + customerId + '\'' +
                ", waiterId='" + waiterId + '\'' +
                ", chefId='" + chefId + '\'' +
                '}';
    }

    public static class Builder{
        private String orderId, orderDetails,customerId,waiterId,chefId;

        public Builder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }

        public Builder orderDetails(String orderDetails){
            this.orderDetails = orderDetails;
            return this;
        }
        public Builder customerId(String customerId){
            this.customerId = customerId;
            return this;
        }
        public Builder waiterId(String waiterId){
            this.waiterId = waiterId;
            return this;
        }
        public Builder chefId(String chefId){
            this.chefId = chefId;
            return this;
        }


        public Builder copy(Order order){
            this.orderId = order.orderId;
            this.orderDetails = order.orderDetails;
            this.customerId = order.customerId;
            this.waiterId = order.waiterId;
            this.chefId = order.chefId;
            return this;
        }

        public Order build(){
            return new Order(this);
        }

    }
}
