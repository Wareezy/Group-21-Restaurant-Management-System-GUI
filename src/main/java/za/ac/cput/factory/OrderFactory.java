package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

public class OrderFactory {
    public static Order build(String orderId,String orderDetails,String customerId,String waiterId,String chefId) throws IllegalArgumentException
    {
        Helper.checkStringParam("Order ID", orderId);
        Helper.checkStringParam("Order Details", orderDetails);
        Helper.checkStringParam("Customer ID", customerId);
        Helper.checkStringParam("Waiter ID", waiterId);
        Helper.checkStringParam("Chef ID", chefId);

        return new Order.Builder().orderId(orderId).orderDetails(orderDetails).customerId(customerId).waiterId(waiterId).chefId(chefId).build();
    }
}



