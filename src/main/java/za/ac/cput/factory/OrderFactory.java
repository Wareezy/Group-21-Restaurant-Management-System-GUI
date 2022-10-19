package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.util.Helper;

public class OrderTableFactory {
    public static OrderTable build(String orderTableId, String numberOfGuest, Order order) {

        Helper.checkStringParam("Order Id", orderTableId);
        Helper.checkStringParam("Number Of Guest", numberOfGuest);
        Helper.checkIfObjectNull("Order",order);

        return new OrderTable.Builder().orderTableId(orderTableId).numberOfGuest(numberOfGuest).order(order).build();
    }
}

