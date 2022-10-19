package za.ac.cput.factory;

import za.ac.cput.domain.Chef;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class ChefFactory {
    public static Chef build(String chefId, String email, String cellPhoneNumber, Name name)
    {
        Helper.checkStringParam("chef Id",chefId);
        Helper.checkStringParam("Email",email);
        Helper.checkStringParam("Cellphone Number",cellPhoneNumber);
        Helper.checkEmail(email);
        Helper.checkIfObjectNull("Name",name);

        return new Chef.Builder().chefId(chefId).email(email).cellPhoneNumber(cellPhoneNumber).name(name).build();
    }
}


