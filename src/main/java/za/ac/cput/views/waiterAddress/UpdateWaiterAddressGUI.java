package za.ac.cput.views.waiterAddress;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

//2199099839
//Nawaaz Amien
public class UpdateWaiterAddressGUI extends JFrame implements ActionListener {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JTable table;
    private JPanel pN, pC, pS, pFields, pUpdate;
    private JButton btnUpdate, btnBack, btnEnter;
    private JLabel lblUpdate, blank1, blank2, blank3, blank4,
            blank5, blank6, blank7, blank8,
            lblWaiterId, lblUnitNumber, lblComplexName, lblStreetNumber, lblStreetName,lblPostalCode,lblCity;
    private JTextField txtUpdateId,txtWaiterId, txtUnitNumber, txtComplexName, txtStreetNumber, txtStreetName,txtPostalCode,txtCity;

    public UpdateWaiterAddressGUI() {
        super("Update Waiter Address");
        table = new JTable();

        pN = new JPanel();
        pC = new JPanel();
        pS = new JPanel();
        pUpdate = new JPanel();
        pFields = new JPanel();

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        btnEnter = new JButton("Enter");

        lblUpdate = new JLabel("Enter Waiter ID to Update: ", SwingConstants.CENTER);
        txtUpdateId = new JTextField();

        lblWaiterId = new JLabel("Waiter ID: ", SwingConstants.CENTER);
        lblUnitNumber = new JLabel("UnitNumber: ", SwingConstants.CENTER);
        lblComplexName = new JLabel("Complex Name: ", SwingConstants.CENTER);
        lblStreetNumber= new JLabel("Street Number: ", SwingConstants.CENTER);
        lblStreetName= new JLabel("Street Name: ", SwingConstants.CENTER);
        lblPostalCode= new JLabel("Postal Code: ", SwingConstants.CENTER);
        lblCity= new JLabel("City: ", SwingConstants.CENTER);

        txtWaiterId= new JTextField();
        txtUnitNumber = new JTextField();
        txtComplexName = new JTextField();
        txtStreetNumber = new JTextField();
        txtStreetName = new JTextField();
        txtPostalCode = new JTextField();
        txtCity = new JTextField();

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

        pFields.add(lblWaiterId);
        pFields.add(txtWaiterId);
        pFields.add(lblUnitNumber);
        pFields.add(txtUnitNumber);
        pFields.add(lblComplexName);
        pFields.add(txtComplexName);
        pFields.add(lblStreetNumber);
        pFields.add(txtStreetNumber);
        pFields.add(lblStreetName);
        pFields.add(txtStreetName);
        pFields.add(lblPostalCode);
        pFields.add(txtPostalCode);
        pFields.add(lblCity);
        pFields.add(txtCity);

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
        model.addColumn("Waiter Id");
        model.addColumn("Waiter UnitNumber");
        model.addColumn("Waiter ComplexName");
        model.addColumn("Waiter StreetNumber");
        model.addColumn("Waiter StreetName");
        model.addColumn("Waiter PostalCode");
        model.addColumn("Waiter city");

        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter-address/find-all";
            String responseBody = run(URL);
            JSONArray waiterAddresss = new JSONArray(responseBody);

            for (int i = 0; i < waiterAddresss.length(); i++) {
                JSONObject waiterAddress = waiterAddresss.getJSONObject(i);

                Gson g = new Gson();
                WaiterAddress b = g.fromJson(waiterAddress.toString(), WaiterAddress.class);

                Object[] rowData = new Object[7];
                rowData[0] = b.getWaiterId();
                rowData[1] = b.getAddress().getUnitNumber();
                rowData[2] = b.getAddress().getComplexName();
                rowData[3] = b.getAddress().getStreetNumber();
                rowData[4] = b.getAddress().getStreetName();
                rowData[5] = b.getAddress().getPostalCode();
                rowData[6] = b.getAddress().getCity();
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

    private WaiterAddress getWaiterAddress(String id) throws IOException {
        WaiterAddress waiterAddress = null;
        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter-address/read/" + id;
            String responseBody = run(URL);
            Gson gson = new Gson();
            waiterAddress = gson.fromJson(responseBody, WaiterAddress.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(waiterAddress);
        return waiterAddress;
    }

    public void store(String waiterId, Address address) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter-address/update";
            WaiterAddress waiterAddress = WaiterAddressFactory.build(waiterId,address);
            Gson g = new Gson();
            String jsonString = g.toJson(waiterAddress);
            String r = put(URL, jsonString);
            System.out.println(r);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Waiter Address updated successfully!");
                WaiterAddressMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Waiter Address not updated.");
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
                        WaiterAddress b = getWaiterAddress(txtUpdateId.getText());
                        if(b != null) {
                            pFields.setVisible(true);
                            txtWaiterId.setText(b.getWaiterId());
                            txtUnitNumber.setText(b.getAddress().getUnitNumber());
                            txtComplexName.setText(b.getAddress().getComplexName());
                            txtStreetNumber.setText(b.getAddress().getStreetNumber());
                            txtStreetName.setText(b.getAddress().getStreetName());
                            txtPostalCode.setText(b.getAddress().getPostalCode());
                            txtCity.setText(b.getAddress().getCity());
                        } else {
                            JOptionPane.showMessageDialog(null, "No Waiter Address with that ID");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Waiter Address ID");
                }
                break;
            case "Update" :
                Address address = AddressFactory.build(txtUnitNumber.getText(),txtComplexName.getText(),txtStreetNumber.getText(),txtStreetName.getText(),txtPostalCode.getText(),txtCity.getText());
                store(txtWaiterId.getText(),
                        address);
                break;
            case "Back" :
                WaiterAddressMainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }

    public static void main(String[] args) {

        new UpdateWaiterAddressGUI().setGUI();
    }
}
