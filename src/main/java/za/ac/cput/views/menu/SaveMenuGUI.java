package za.ac.cput.views.menu;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.MenuFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.views.orderTable.OrderTableMainGUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveMenuGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblMenuId, lblMenuDetails,lblTitle, lblOrderId, lblOrderDetails, lblCustomerId,lblWaiterId,lblChefId;
    private JTextField txtMenuId, txtMenuDetails,txtTitle, txtOrderId, txtOrderDetails, txtCustomerId,txtWaiterId,txtChefId;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveMenuGUI() {
        super("Add new Menu");

        pN = new JPanel();
        pS = new JPanel();

        lblMenuId= new JLabel(" Menu Id: ");
        lblMenuDetails = new JLabel("Menu Details: ");
        lblTitle = new JLabel("Title: ");
        lblOrderId = new JLabel("Order Id: ");
        lblOrderDetails = new JLabel("Order Details: ");
        lblWaiterId = new JLabel("Waiter Id: ");
        lblCustomerId = new JLabel("Customer Id: ");
        lblChefId = new JLabel("Chef Id: ");

        txtMenuId = new JTextField(30);
        txtMenuDetails = new JTextField(30);
        txtTitle = new JTextField(30);
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

        pN.add(lblMenuId);
        pN.add(txtMenuId);

        pN.add(lblMenuDetails);
        pN.add(txtMenuDetails);

        pN.add(lblTitle);
        pN.add(txtTitle);

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
            store(txtMenuId.getText(),
                    txtMenuDetails.getText(),
                    txtTitle.getText(),
                    order);
        }
        if(e.getSource() == btnCancel) {
            OrderTableMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String menuId,String menuDetails,String title, Order order) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/menu/save";
            Menu menu = MenuFactory.build(menuId,menuDetails,title,order);
            Gson g = new Gson();
            String jsonString = g.toJson(menu);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Menu saved successfully!");
                MenuMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Menu not saved.");
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
        new SaveMenuGUI().setGUI();
    }
}

