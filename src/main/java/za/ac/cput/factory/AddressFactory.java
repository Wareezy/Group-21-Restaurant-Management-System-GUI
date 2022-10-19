package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.util.Helper;

public class AddressFactory {
    public static Address build(String unitNumber, String complexName, String streetNumber, String streetName, String postalCode, String city) throws IllegalArgumentException
    {
        Helper.checkStringParam("Unit Number", unitNumber);
        Helper.checkStringParam("Complex Name", complexName);
        Helper.checkStringParam("Street Number", streetNumber);
        Helper.checkStringParam("Street Name", streetName);
        Helper.checkStringParam("Postal Code", postalCode);
        Helper.checkStringParam("City", city);


        return new Address.Builder().unitNumber(unitNumber).complexName(complexName).streetNumber(streetNumber).streetName(streetName).postalCode(postalCode).city(city).build();
    }
}
