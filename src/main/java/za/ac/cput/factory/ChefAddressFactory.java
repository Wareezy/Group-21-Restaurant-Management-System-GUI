package za.ac.cput.factory;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.ChefAddress;
import za.ac.cput.util.Helper;

public class ChefAddressFactory {
    public static ChefAddress build(String chefId, Address address){
        Helper.checkStringParam("chefId", chefId);
        Helper.checkIfObjectNull("address", address);
        return new ChefAddress.Builder().chefId(chefId).address(address)
                .build();
    }

}

