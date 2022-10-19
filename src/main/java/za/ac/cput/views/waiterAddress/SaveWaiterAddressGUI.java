package za.ac.cput.views.waiterAddress;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//2199099839
//Nawaaz Amien
public class SaveWaiterAddressGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblWaiterId, lblUnitNumber, lblComplexName, lblStreetNumber, lblStreetName,lblPostalCode,lblCity;
    private JTextField txtWaiterId, txtUnitNumber, txtComplexName, txtStreetNumber, txtStreetName,txtPostalCode,txtCity;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveWaiterAddressGUI() {
        super("Add new Waiter Address");

        pN = new JPanel();
        pS = new JPanel();

        lblWaiterId = new JLabel(" Waiter ID: ");
        lblUnitNumber = new JLabel("UnitNumber: ");
        lblComplexName = new JLabel("Complex Name: ");
        lblStreetNumber = new JLabel("Street Number: ");
        lblStreetName = new JLabel("Street Name: ");
        lblPostalCode = new JLabel("Postal Code: ");
        lblCity = new JLabel("City: ");

        txtWaiterId = new JTextField(30);
        txtUnitNumber = new JTextField(30);
        txtComplexName = new JTextField(30);
        txtStreetNumber = new JTextField(30);
        txtStreetName = new JTextField(30);
        txtPostalCode = new JTextField(30);
        txtCity = new JTextField(30);


        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
    }

    public void setGUI() {
        pN.setLayout(new GridLayout(0,2));
        pS.setLayout(new GridLayout(1,2));

        pN.add(lblWaiterId);
        pN.add(txtWaiterId);

        pN.add(lblUnitNumber);
        pN.add(txtUnitNumber);

        pN.add(lblComplexName);
        pN.add(txtComplexName);

        pN.add(lblStreetNumber);
        pN.add(txtStreetNumber);

        pN.add(lblStreetName);
        pN.add(txtStreetName);

        pN.add(lblPostalCode);
        pN.add(txtPostalCode);

        pN.add(lblCity);
        pN.add(txtCity);


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
            Address address = AddressFactory.build(txtUnitNumber.getText(),txtComplexName.getText(),txtStreetNumber.getText(),txtStreetName.getText(),txtPostalCode.getText(),txtCity.getText());
            store(txtWaiterId.getText(),
                    address);
        }
        if(e.getSource() == btnCancel) {
            WaiterAddressMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String waiterId, Address address) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter-address/save";
            WaiterAddress waiterAddress = WaiterAddressFactory.build(waiterId,address);
            Gson g = new Gson();
            String jsonString = g.toJson(waiterAddress);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Waiter Address saved successfully!");
                WaiterAddressMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Waiter Address not saved.");
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
        new SaveWaiterAddressGUI().setGUI();
    }
}
