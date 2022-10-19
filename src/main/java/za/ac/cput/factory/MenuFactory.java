package za.ac.cput.factory;

import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;
import za.ac.cput.util.Helper;

public class MenuFactory {
    public static Menu build(String menuId, String menuDetails,String title ,Order order) {

        Helper.checkStringParam("Menu Id", menuId);
        Helper.checkStringParam("Menu Details", menuDetails);
        Helper.checkStringParam("Title", title);
        Helper.checkIfObjectNull("Order",order);

        return new Menu.Builder().menuId(menuId).menuDetails(menuDetails).title(title).order(order).build();
    }
}

