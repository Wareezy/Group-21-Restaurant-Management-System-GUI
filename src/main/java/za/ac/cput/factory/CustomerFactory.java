package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Name;
import za.ac.cput.util.Helper;

public class CustomerFactory {
    public static Customer build(String customerId, String email,String cellPhoneNumber,Name name) {

        Helper.checkStringParam("Customer Id", customerId);
        Helper.checkStringParam("Email", email);
        Helper.checkStringParam("Cellphone Number", cellPhoneNumber);
        Helper.checkEmail(email);
        Helper.checkIfObjectNull("Name",name);

        return new Customer.Builder().customerId(customerId).email(email).cellPhoneNumber(cellPhoneNumber).name(name).build();
    }
}
