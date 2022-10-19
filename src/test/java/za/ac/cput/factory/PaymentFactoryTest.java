package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class PaymentFactoryTest {
    Order order= OrderFactory.build("18","StoneHurst","001","001","001");
    @Test

    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                PaymentFactory.build("","15",order));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test

    public void buildWithSuccess(){
        Payment payment = PaymentFactory.build("219005303", "15",order);
        assertNotNull(payment);
        System.out.println(payment);
    }
}

