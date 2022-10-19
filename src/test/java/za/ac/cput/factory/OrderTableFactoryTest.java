package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class OrderTableFactoryTest {
    Order order= OrderFactory.build("18","StoneHurst","001","001","001");
    @Test

    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                OrderTableFactory.build("","15",order));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test

    public void buildWithSuccess(){
        OrderTable orderTable = OrderTableFactory.build("219005303", "15",order);
        assertNotNull(orderTable);
        System.out.println(orderTable);
    }
}
