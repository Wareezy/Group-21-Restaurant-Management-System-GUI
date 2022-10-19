package za.ac.cput.views.customer;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.NameFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class UpdateCustomerGUI extends JFrame implements ActionListener {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JTable table;
    private JPanel pN, pC, pS, pFields, pUpdate;
    private JButton btnUpdate, btnBack, btnEnter;
    private JLabel lblUpdate, blank1, blank2, blank3, blank4,
            blank5, blank6, blank7, blank8,
            lblFirstName, lblLastName, lblMiddleName, lblEmail, lblCellPhoneNumber,lblCustomerId;
    private JTextField txtUpdateId, txtCustomerId, txtEmail,txtCellPhoneNumber,
            txtFirstName, txtMiddleName,txtLastName;

    public UpdateCustomerGUI() {
        super("Update Customers");
        table = new JTable();

        pN = new JPanel();
        pC = new JPanel();
        pS = new JPanel();
        pUpdate = new JPanel();
        pFields = new JPanel();

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        btnEnter = new JButton("Enter");

        lblUpdate = new JLabel("Enter Customer ID to Update: ", SwingConstants.CENTER);
        txtUpdateId = new JTextField();

        lblCustomerId = new JLabel("Customer ID: ", SwingConstants.CENTER);
        lblEmail = new JLabel("Email: ", SwingConstants.CENTER);
        lblCellPhoneNumber = new JLabel("CellPhone Number: ", SwingConstants.CENTER);
        lblFirstName= new JLabel("FirstName: ", SwingConstants.CENTER);
        lblMiddleName= new JLabel("Middle: ", SwingConstants.CENTER);
        lblLastName= new JLabel("LastName: ", SwingConstants.CENTER);

        txtCustomerId = new JTextField();
        txtEmail = new JTextField();
        txtCellPhoneNumber = new JTextField();
        txtFirstName = new JTextField();
        txtMiddleName = new JTextField();
        txtLastName = new JTextField();

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
        pFields.setLayout(new GridLayout(2,2));
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

        pFields.add(lblCustomerId);
        pFields.add(txtCustomerId);
        pFields.add(lblEmail);
        pFields.add(txtEmail);
        pFields.add(lblCellPhoneNumber);
        pFields.add(txtCellPhoneNumber);
        pFields.add(lblFirstName);
        pFields.add(txtFirstName);
        pFields.add(lblMiddleName);
        pFields.add(txtMiddleName);
        pFields.add(lblLastName);
        pFields.add(txtLastName);

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
        model.addColumn("Customer Id");
        model.addColumn("Customer Email");
        model.addColumn("Customer CellPhoneNumber");
        model.addColumn("Customer FirstName");
        model.addColumn("Customer MiddleName");
        model.addColumn("Customer LastName");

        try {
            final String URL = "http://localhost:9210/restaurant-management/customer/find-all";
            String responseBody = run(URL);
            JSONArray customers = new JSONArray(responseBody);

            for (int i = 0; i < customers.length(); i++) {
                JSONObject customer = customers.getJSONObject(i);

                Gson g = new Gson();
                Customer b = g.fromJson(customer.toString(), Customer.class);

                Object[] rowData = new Object[6];
                rowData[0] = b.getCustomerId();
                rowData[1] = b.getEmail();
                rowData[2] = b.getCellPhoneNumber();
                rowData[3] = b.getName().getFirstName();
                rowData[4] = b.getName().getMiddleName();
                rowData[5] = b.getName().getLastName();
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

    private Customer getCustomer(String id) throws IOException {
        Customer customer = null;
        try {
            final String URL = "http://localhost:9210/restaurant-management/customer/read/" + id;
            String responseBody = run(URL);
            Gson gson = new Gson();
            customer = gson.fromJson(responseBody, Customer.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(customer);
        return customer;
    }

    public void store(String customerId, String email, String cellPhoneNumber, Name name) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/customer/update";
            Customer customer = CustomerFactory.build(customerId,email,cellPhoneNumber,name);
            Gson g = new Gson();
            String jsonString = g.toJson(customer);
            String r = put(URL, jsonString);
            System.out.println(r);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Customer updated successfully!");
                CustomerMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Customer not updated.");
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
                        Customer b = getCustomer(txtUpdateId.getText());
                        if(b != null) {
                            pFields.setVisible(true);
                            txtCustomerId.setText(b.getCustomerId());
                            txtEmail.setText(b.getEmail());
                            txtCellPhoneNumber.setText(b.getCellPhoneNumber());
                            txtFirstName.setText(b.getName().getFirstName());
                            txtMiddleName.setText(b.getName().getMiddleName());
                            txtLastName.setText(b.getName().getLastName());
                        } else {
                            JOptionPane.showMessageDialog(null, "No Customer with that ID");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Customer ID");
                }
                break;
            case "Update" :
                Name name = NameFactory.build(txtFirstName.getText(),txtMiddleName.getText(),txtLastName.getText());
                store(txtCustomerId.getText(),
                        txtEmail.getText(),
                        txtCellPhoneNumber.getText(),
                        name);
                break;
            case "Back" :
                CustomerMainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }

    public static void main(String[] args) {

        new UpdateCustomerGUI().setGUI();
    }
}
