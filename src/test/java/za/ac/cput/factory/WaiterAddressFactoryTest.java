package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;

import static org.junit.jupiter.api.Assertions.*;
//2199099839
//Nawaaz Amien
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class WaiterAddressFactoryTest {
    Address address = AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");

    @Test
    @Order(1)
    public void buildWithNullAddress(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                WaiterAddressFactory.build("001",null));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }


    @Test
    @Order(2)
    public void buildWithEmptyStaffId(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                WaiterAddressFactory.build("",address));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(3)
    public void buildWithSuccess(){
        WaiterAddress waiterAddress = WaiterAddressFactory.build("001", address);
        assertNotNull(waiterAddress);
        System.out.println(waiterAddress);
    }
}