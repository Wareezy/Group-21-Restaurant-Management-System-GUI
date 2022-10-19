package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Order;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {
    @Test
    public void buildWithValues(){
        Order order = OrderFactory.build("18","StoneHurst","001","001","001");
        System.out.println(order);
        assertNotNull(order);
    }

    @Test
    public void buildWithError(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                OrderFactory.build("18",null,"001","001","001"));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }
}


