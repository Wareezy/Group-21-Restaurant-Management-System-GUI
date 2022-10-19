package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.MenuItem;
import za.ac.cput.domain.Order;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class MenuItemFactoryTest {
    Order order = OrderFactory.build("18","StoneHurst","001","001","001");

    @Test
    @org.junit.jupiter.api.Order(1)
    public void buildWithNullAddress(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                MenuItemFactory.build("001",null));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }


    @Test
    @org.junit.jupiter.api.Order(2)
    public void buildWithEmptyTitle(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                MenuItemFactory.build("",order));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void buildWithSuccess(){
        MenuItem menuItem = MenuItemFactory.build("001", order);
        assertNotNull(menuItem);
        System.out.println(menuItem);
    }
}
