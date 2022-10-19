package za.ac.cput.views.waiter;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Waiter;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.WaiterFactory;
import za.ac.cput.views.customer.CustomerMainGUI;
import za.ac.cput.views.customer.SaveCustomerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveWaiterGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblFirstName, lblSurname, lblMiddleName, lblEmail, lblCellPhoneNumber,lblWaiterId;
    private JTextField txtFirstName, txtSurname, txtMiddleName, txtEmail, txtCellPhoneNumber,txtWaiterId;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveWaiterGUI() {
        super("Add new Waiter");

        pN = new JPanel();
        pS = new JPanel();

        lblWaiterId = new JLabel("Waiter ID: ");
        lblFirstName = new JLabel("First Name: ");
        lblMiddleName = new JLabel("Middle Name: ");
        lblSurname = new JLabel("Last Name: ");

        lblEmail = new JLabel("Email Address: ");
        lblCellPhoneNumber = new JLabel("CellPhone Number: ");

        txtWaiterId = new JTextField(30);
        txtFirstName = new JTextField(30);
        txtMiddleName = new JTextField(30);
        txtSurname = new JTextField(30);

        txtEmail = new JTextField(30);
        txtCellPhoneNumber = new JTextField(30);

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
    }

    public void setGUI() {
        pN.setLayout(new GridLayout(0,2));
        pS.setLayout(new GridLayout(1,2));

        pN.add(lblWaiterId);
        pN.add(txtWaiterId);

        pN.add(lblFirstName);
        pN.add(txtFirstName);

        pN.add(lblMiddleName);
        pN.add(txtMiddleName);

        pN.add(lblSurname);
        pN.add(txtSurname);

        pN.add(lblEmail);
        pN.add(txtEmail);

        pN.add(lblCellPhoneNumber);
        pN.add(txtCellPhoneNumber);

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
            Name name = NameFactory.build(txtFirstName.getText(),txtMiddleName.getText(),txtSurname.getText());
            store(txtWaiterId.getText(),
                    txtEmail.getText(),
                    txtCellPhoneNumber.getText(),name);
        }
        if(e.getSource() == btnCancel) {
            WaiterMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String waiterId, String email,String cellPhoneNumber,Name name) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter/save";
            Waiter waiter = WaiterFactory.build(waiterId,email,cellPhoneNumber,name);
            Gson g = new Gson();
            String jsonString = g.toJson(waiter);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Waiter saved successfully!");
                WaiterMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Waiter not saved.");
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
        new SaveWaiterGUI().setGUI();
    }
}
