package za.ac.cput.views.orderTable;

import com.google.gson.Gson;


import okhttp3.*;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.factory.OrderFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveOrderTableGUI extends JFrame implements ActionListener {
    public static final okhttp3.MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblOrderTableId, lblNumberOfGuest, lblOrderId, lblOrderDetails, lblCustomerId,lblWaiterId,lblChefId;
    private JTextField txtOrderTableId, txtNumberOfGuest, txtOrderId, txtOrderDetails, txtCustomerId,txtWaiterId,txtChefId;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveOrderTableGUI() {
        super("Add new Order Table");

        pN = new JPanel();
        pS = new JPanel();

        lblOrderTableId= new JLabel(" Order Table Id: ");
        lblNumberOfGuest = new JLabel("Number Of Guest: ");
        lblOrderId = new JLabel("Order Id: ");
        lblOrderDetails = new JLabel("Order Details: ");
        lblWaiterId = new JLabel("Waiter Id: ");
        lblCustomerId = new JLabel("Customer Id: ");
        lblChefId = new JLabel("Chef Id: ");

        txtOrderTableId = new JTextField(30);
        txtNumberOfGuest = new JTextField(30);
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

        pN.add(lblOrderTableId);
        pN.add(txtOrderTableId);

        pN.add(lblNumberOfGuest);
        pN.add(txtNumberOfGuest);

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
            store(txtOrderTableId.getText(),
                    txtNumberOfGuest.getText(),
                    order);
        }
        if(e.getSource() == btnCancel) {
            OrderTableMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String orderTableId,String numberOfGuests, Order order) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/order-table/save";
            OrderTable orderTable = OrderTableFactory.build(orderTableId,numberOfGuests,order);
            Gson g = new Gson();
            String jsonString = g.toJson(orderTable);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Order Table saved successfully!");
                OrderTableMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Order Table not saved.");
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
        new SaveOrderTableGUI().setGUI();
    }
}

