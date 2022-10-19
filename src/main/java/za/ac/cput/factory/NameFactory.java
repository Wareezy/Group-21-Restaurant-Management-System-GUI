package za.ac.cput.factory;


import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class NameFactory {

    public static Name build(String firstName, String middleName, String lastName){

        Helper.checkStringParam("First Name", firstName);
        Helper.checkStringParam("Last Name", lastName);
        middleName = Helper.setEmptyIfNull(middleName);

        return new Name.Builder().firstName(firstName).middleName(middleName).lastName(lastName).build();
    }
}
