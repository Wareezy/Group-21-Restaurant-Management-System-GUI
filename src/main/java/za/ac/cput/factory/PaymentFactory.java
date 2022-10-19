package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.util.Helper;

public class PaymentFactory {
    public static Payment build(String cardNumber, String paymentAmount, Order order) {

        Helper.checkStringParam("Card Number", cardNumber);
        Helper.checkStringParam("Payment Amount", paymentAmount);
        Helper.checkIfObjectNull("Order",order);

        return new Payment.Builder().cardNumber(cardNumber).paymentAmount(paymentAmount).order(order).build();
    }
}


