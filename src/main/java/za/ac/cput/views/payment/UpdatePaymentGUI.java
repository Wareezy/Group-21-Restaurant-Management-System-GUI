package za.ac.cput.views.payment;



import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.Payment;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class UpdatePaymentGUI extends JFrame implements ActionListener {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JTable table;
    private JPanel pN, pC, pS, pFields, pUpdate;
    private JButton btnUpdate, btnBack, btnEnter;
    private JLabel lblUpdate, blank1, blank2, blank3, blank4,
            blank5, blank6, blank7, blank8,
            lblCardNumber, lblPaymentAmount, lblOrderId, lblOrderDetails, lblCustomerId,lblWaiterId,lblChefId;
    private JTextField txtUpdateId,txtCardNumber, txtPaymentAmount, txtOrderId, txtOrderDetails, txtCustomerId,txtWaiterId,txtChefId;

    public UpdatePaymentGUI() {
        super("Update Payment");
        table = new JTable();

        pN = new JPanel();
        pC = new JPanel();
        pS = new JPanel();
        pUpdate = new JPanel();
        pFields = new JPanel();

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        btnEnter = new JButton("Enter");

        lblUpdate = new JLabel("Enter CardNumber to Update: ", SwingConstants.CENTER);
        txtUpdateId = new JTextField();

        lblCardNumber = new JLabel("Card Number: ", SwingConstants.CENTER);
        lblPaymentAmount = new JLabel("Payment Amount: ", SwingConstants.CENTER);
        lblOrderId = new JLabel("Order ID: ", SwingConstants.CENTER);
        lblOrderDetails= new JLabel("Order Details: ", SwingConstants.CENTER);
        lblCustomerId= new JLabel("Customer ID: ", SwingConstants.CENTER);
        lblWaiterId= new JLabel("Waiter ID: ", SwingConstants.CENTER);
        lblChefId= new JLabel("Chef ID ", SwingConstants.CENTER);

        txtCardNumber= new JTextField();
        txtPaymentAmount = new JTextField();
        txtOrderId = new JTextField();
        txtOrderDetails = new JTextField();
        txtCustomerId = new JTextField();
        txtWaiterId = new JTextField();
        txtChefId = new JTextField();

        blank1 = new JLabel("");
        blank2 = new JLabel("");
        blank3 = new JLabel("");
        blank4 = new JLabel("");
        blank5 = new JLabel("");
        blank6 = new JLabel("");
        blank7 = new JLabel("");
        blank8 = new JLabel("");
    }

    public void setGUI() {
        pN.setLayout(new GridLayout(0,1));
        pC.setLayout(new GridLayout(0,1));
        pUpdate.setLayout(new GridLayout(3,3));
        pFields.setLayout(new GridLayout(4,2));
        pS.setLayout(new GridLayout(2,2));

        pN.add(new JScrollPane(table));

        pUpdate.add(blank1);
        pUpdate.add(blank2);
        pUpdate.add(blank3);
        pUpdate.add(lblUpdate);
        pUpdate.add(txtUpdateId);
        pUpdate.add(btnEnter);
        pUpdate.add(blank4);
        pUpdate.add(blank5);

        pFields.add(lblCardNumber);
        pFields.add(txtCardNumber);
        pFields.add(lblPaymentAmount);
        pFields.add(txtPaymentAmount);
        pFields.add(lblOrderId);
        pFields.add(txtOrderId);
        pFields.add(lblOrderDetails);
        pFields.add(txtOrderDetails);
        pFields.add(lblCustomerId);
        pFields.add(txtCustomerId);
        pFields.add(lblWaiterId);
        pFields.add(txtWaiterId);
        pFields.add(lblChefId);
        pFields.add(txtChefId);

        pC.add(pUpdate);
        pC.add(pFields);

        pS.add(blank7);
        pS.add(blank8);
        pS.add(btnUpdate);
        pS.add(btnBack);

        displayTable();
        pFields.setVisible(false);

        this.add(pN, BorderLayout.NORTH);
        this.add(pC, BorderLayout.CENTER);
        this.add(pS, BorderLayout.SOUTH);

        btnEnter.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnBack.addActionListener(this);

        table.setRowHeight(30);
        this.pack();
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void displayTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("CardNumber");
        model.addColumn("Payment Amount");
        model.addColumn("Order Id");
        model.addColumn("Order Details");
        model.addColumn("Customer Id");
        model.addColumn("Waiter Id");
        model.addColumn("Chef Id");

        try {
            final String URL = "http://localhost:9210/restaurant-management/payment/find-all";
            String responseBody = run(URL);
            JSONArray payments = new JSONArray(responseBody);

            for (int i = 0; i < payments.length(); i++) {
                JSONObject payment = payments.getJSONObject(i);

                Gson g = new Gson();
                Payment b = g.fromJson(payment.toString(), Payment.class);

                Object[] rowData = new Object[7];
                rowData[0] = b.getCardNumber();
                rowData[1] = b.getPaymentAmount();
                rowData[2] = b.getOrder().getOrderId();
                rowData[3] = b.getOrder().getOrderDetails();
                rowData[4] = b.getOrder().getCustomerId();
                rowData[5] = b.getOrder().getWaiterId();
                rowData[6] = b.getOrder().getChefId();
                model.addRow(rowData);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private Payment getPayment(String id) throws IOException {
        Payment payment = null;
        try {
            final String URL = "http://localhost:9210/restaurant-management/payment/read/" + id;
            String responseBody = run(URL);
            Gson gson = new Gson();
            payment = gson.fromJson(responseBody, Payment.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(payment);
        return payment;
    }

    public void store(String cardNumber,String paymentAmount, Order order) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/payment/update";
            Payment payment = PaymentFactory.build(cardNumber,paymentAmount,order);
            Gson g = new Gson();
            String jsonString = g.toJson(payment);
            String r = put(URL, jsonString);
            System.out.println(r);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Payment updated successfully!");
                PaymentMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Payment not updated.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public String put(final String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder().url(url).put(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Enter" :
                if (!Objects.equals(txtUpdateId.getText(), "")) {
                    try {
                        Payment b = getPayment(txtUpdateId.getText());
                        if(b != null) {
                            pFields.setVisible(true);
                            txtCardNumber.setText(b.getCardNumber());
                            txtPaymentAmount.setText(b.getPaymentAmount());
                            txtOrderId.setText(b.getOrder().getOrderId());
                            txtOrderDetails.setText(b.getOrder().getOrderDetails());
                            txtCustomerId.setText(b.getOrder().getCustomerId());
                            txtWaiterId.setText(b.getOrder().getWaiterId());
                            txtChefId.setText(b.getOrder().getChefId());
                        } else {
                            JOptionPane.showMessageDialog(null, "No CardNumber with that ID");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Card Number");
                }
                break;
            case "Update" :
                Order order = OrderFactory.build(txtOrderId.getText(),txtOrderDetails.getText(),txtCustomerId.getText(),txtWaiterId.getText(),txtChefId.getText());
                store(txtCardNumber.getText(),
                        txtPaymentAmount.getText(),
                        order);
                break;
            case "Back" :
                PaymentMainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }

    public static void main(String[] args) {

        new UpdatePaymentGUI().setGUI();
    }
}
