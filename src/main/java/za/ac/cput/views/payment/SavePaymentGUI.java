package za.ac.cput.views.payment;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SavePaymentGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblCardNumber, lblPaymentAmount, lblOrderId, lblOrderDetails, lblCustomerId,lblWaiterId,lblChefId;
    private JTextField txtCardNumber, txtPaymentAmount, txtOrderId, txtOrderDetails, txtCustomerId,txtWaiterId,txtChefId;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SavePaymentGUI() {
        super("Add new Payment");

        pN = new JPanel();
        pS = new JPanel();

        lblCardNumber= new JLabel(" CardNumber: ");
        lblPaymentAmount = new JLabel("Payment Amount: ");
        lblOrderId = new JLabel("Order Id: ");
        lblOrderDetails = new JLabel("Order Details: ");
        lblWaiterId = new JLabel("Waiter Id: ");
        lblCustomerId = new JLabel("Customer Id: ");
        lblChefId = new JLabel("Chef Id: ");

        txtCardNumber = new JTextField(30);
        txtPaymentAmount = new JTextField(30);
        txtOrderId = new JTextField(30);
        txtOrderDetails = new JTextField(30);
        txtWaiterId = new JTextField(30);
        txtCustomerId = new JTextField(30);
        txtChefId = new JTextField(30);


        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
    }

    public void setGUI() {
        pN.setLayout(new GridLayout(0,2));
        pS.setLayout(new GridLayout(1,2));

        pN.add(lblCardNumber);
        pN.add(txtCardNumber);

        pN.add(lblPaymentAmount);
        pN.add(txtPaymentAmount);

        pN.add(lblOrderId);
        pN.add(txtOrderId);

        pN.add(lblOrderDetails);
        pN.add(txtOrderDetails);

        pN.add(lblCustomerId);
        pN.add(txtCustomerId);

        pN.add(lblWaiterId);
        pN.add(txtWaiterId);

        pN.add(lblChefId);
        pN.add(txtChefId);


        pS.add(btnSave);
        pS.add(btnCancel);

        this.add(pN, BorderLayout.NORTH);
        this.add(pS, BorderLayout.SOUTH);

        btnSave.addActionListener(this);
        btnCancel.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {
            Order order = OrderFactory.build(txtOrderId.getText(),txtOrderDetails.getText(),txtCustomerId.getText(),txtWaiterId.getText(),txtChefId.getText());
            store(txtCardNumber.getText(),
                    txtPaymentAmount.getText(),
                    order);
        }
        if(e.getSource() == btnCancel) {
            PaymentMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String cardNumber,String paymentAmount, Order order) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/payment/save";
            Payment payment = PaymentFactory.build(cardNumber,paymentAmount,order);
            Gson g = new Gson();
            String jsonString = g.toJson(payment);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Payment saved successfully!");
                PaymentMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Payment not saved.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public String post(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) {
        new SavePaymentGUI().setGUI();
    }

}
