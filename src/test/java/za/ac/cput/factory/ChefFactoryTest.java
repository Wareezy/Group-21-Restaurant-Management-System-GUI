package za.ac.cput.factory;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Chef;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Waiter;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChefFactoryTest {
    Name name=NameFactory.build("Warren","Reagan","Jaftha");
    @Test
    @Order(1)
    public void BuildWithNullName()
    {
        Exception exception=assertThrows(IllegalArgumentException.class,()->
                ChefFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",null));
        String exeptionMessage=exception.getMessage();
        System.out.println(exeptionMessage);
    }

    @Test
    @Order(2)
    public void buildWithInvalidEmail(){
        Exception exception=assertThrows(IllegalArgumentException.class,()->
                ChefFactory.build("219005303","loremIpsum","0731791829",name));
        String exceptionMessage=exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(3)
    public void buildWithEmptyStaffId(){
        Exception exception=assertThrows(IllegalArgumentException.class,()->
                ChefFactory.build("","warrenjaftha16@gmail.com","0731791829",name));
        String exceptionMessage=exception.getMessage();
        System.out.println(exceptionMessage);
    }

    @Test
    @Order(4)
    public void buildWithSuccess()
    {
        Chef chef=ChefFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",name);
        assertNotNull(chef);
        System.out.println(chef);
    }

}
