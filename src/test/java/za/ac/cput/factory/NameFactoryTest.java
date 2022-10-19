package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;

class NameFactoryTest {
    @Test
    public void buildWithNullNameAndSuccess(){
        Name name = NameFactory.build("Jody",null, "Kearns");
        assertAll(
                ()-> assertTrue(name.getMiddleName().isEmpty()),
                ()-> assertNotNull(name)
        );
        System.out.println(name);
    }

    @Test
    public void buildWithCompletedValues(){
        Name name = NameFactory.build("Jody", "Reagan", "Kearns");
        System.out.println(name);
        assertNotNull(name);
    }

    @Test
    public void buildError(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->
                NameFactory.build("","Reagan","Kearns"));
        String exceptionMessage = exception.getMessage();
        System.out.println(exceptionMessage);
    }
}
