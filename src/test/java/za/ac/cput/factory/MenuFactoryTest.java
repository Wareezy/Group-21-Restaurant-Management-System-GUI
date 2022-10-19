package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class MenuFactoryTest {
    Order order= OrderFactory.build("18","StoneHurst","001","001","001");
    @Test
    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                MenuFactory.build("","Chips","Spur",order));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test

    public void buildWithSuccess(){
        Menu menu = MenuFactory.build("219005303", "Chips","Spur",order);
        assertNotNull(menu);
        System.out.println(menu);
    }
}
