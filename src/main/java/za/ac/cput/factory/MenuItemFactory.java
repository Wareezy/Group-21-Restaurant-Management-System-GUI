package za.ac.cput.factory;

import za.ac.cput.domain.MenuItem;
import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

public class MenuItemFactory {
    public static MenuItem build(String title, Order order){
        Helper.checkStringParam("Title", title);
        Helper.checkIfObjectNull("Order", order);
        return new MenuItem.Builder().title(title).order(order)
                .build();
    }
}

