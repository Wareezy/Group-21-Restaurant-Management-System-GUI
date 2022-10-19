package za.ac.cput.factory;

import za.ac.cput.domain.Name;
import za.ac.cput.domain.Waiter;
import za.ac.cput.util.Helper;

public class WaiterFactory {
    public static Waiter build(String waiterId, String email, String cellPhoneNumber, Name name)
    {
        Helper.checkStringParam("Waiter Id",waiterId);
        Helper.checkStringParam("Email",email);
        Helper.checkStringParam("Cellphone Number",cellPhoneNumber);
        Helper.checkEmail(email);
        Helper.checkIfObjectNull("Name",name);

        return new Waiter.Builder().waiterId(waiterId).email(email).cellPhoneNumber(cellPhoneNumber).name(name).build();
    }
}
