package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.ChefAddress;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChefAddressFactoryTest {
    Address address = AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");

    @Test
    @Order(1)
    public void buildWithNullAddress(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                ChefAddressFactory.build("001",null));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }


    @Test
    @Order(2)
    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                ChefAddressFactory.build("",address));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(3)
    public void buildWithSuccess(){
        ChefAddress chefAddress = ChefAddressFactory.build("001", address);
        assertNotNull(chefAddress);
        System.out.println(chefAddress);
    }


}


