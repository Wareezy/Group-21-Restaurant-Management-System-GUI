package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;

import static org.junit.jupiter.api.Assertions.*;

//2199099839
//Nawaaz Amien
class AddressFactoryTest {
    @Test
    public void buildWithValues(){
        Address address = AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");
        System.out.println(address);
        assertNotNull(address);
    }

    @Test
    public void buildWithError(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                AddressFactory.build("18",null, "8","7th Ave","8324","Joburg"));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }
}