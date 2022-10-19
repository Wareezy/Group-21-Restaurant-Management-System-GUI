package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CustomerFactoryTest {
    Name name = NameFactory.build("Jody","Reagan","Kearns");

    @Test
    @Order(1)
    public void buildWithNullName(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                CustomerFactory.build("209023651","209023651@mycput.ac.za","0731791829",null));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(2)
    public void buildWithInvalidEmail(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                CustomerFactory.build("209023651","loremIpsum","0731791829",name));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(3)
    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                CustomerFactory.build("","209023651@mycput.ac.za","0731791829",name));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(4)
    public void buildWithSuccess(){
        Customer customer = CustomerFactory.build("209023651", "209023651@mycput.ac.za","0731791829",name);
        assertNotNull(customer);
        System.out.println(customer);
    }
}