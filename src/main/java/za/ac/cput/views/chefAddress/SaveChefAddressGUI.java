package za.ac.cput.views.chefAddress;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.ChefAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ChefAddressFactory;
import za.ac.cput.views.waiterAddress.WaiterAddressMainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



public class SaveChefAddressGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblChefId, lblUnitNumber, lblComplexName, lblStreetNumber, lblStreetName,lblPostalCode,lblCity;
    private JTextField txtChefId, txtUnitNumber, txtComplexName, txtStreetNumber, txtStreetName,txtPostalCode,txtCity;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveChefAddressGUI() {
        super("Add new Chef Address");

        pN = new JPanel();
        pS = new JPanel();

        lblChefId = new JLabel(" Chef ID: ");
        lblUnitNumber = new JLabel("UnitNumber: ");
        lblComplexName = new JLabel("Complex Name: ");
        lblStreetNumber = new JLabel("Street Number: ");
        lblStreetName = new JLabel("Street Name: ");
        lblPostalCode = new JLabel("Postal Code: ");
        lblCity = new JLabel("City: ");

        txtChefId = new JTextField(30);
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

        pN.add(lblChefId);
        pN.add(txtChefId);

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
            store(txtChefId.getText(),
                    address);
        }
        if(e.getSource() == btnCancel) {
            WaiterAddressMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String chefId, Address address) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/chef-address/save";
            ChefAddress chefAddress = ChefAddressFactory.build(chefId,address);
            Gson g = new Gson();
            String jsonString = g.toJson(chefAddress);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Chef Address saved successfully!");
                ChefAddressMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Chef Address not saved.");
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
        new SaveChefAddressGUI().setGUI();
    }
}

