package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.util.Helper;

public class WaiterAddressFactory {
    public static WaiterAddress build(String waiterId, Address address){
        Helper.checkStringParam("waiterId", waiterId);
        Helper.checkIfObjectNull("address", address);
        return new WaiterAddress.Builder().waiterId(waiterId).address(address)
                .build();
    }
}
