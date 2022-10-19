package za.ac.cput.views.chef;

import com.google.gson.Gson;
import okhttp3.*;
import za.ac.cput.domain.Chef;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.ChefFactory;
import za.ac.cput.factory.NameFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveChefGUI extends JFrame implements ActionListener {
    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JLabel lblFirstName, lblSurname, lblMiddleName, lblEmail, lblCellPhoneNumber,lblChefId;
    private JTextField txtFirstName, txtSurname, txtMiddleName, txtEmail, txtCellPhoneNumber,txtChefId;
    private JButton btnSave, btnCancel;
    public JPanel pN, pS;

    public SaveChefGUI() {
        super("Add new Chef");

        pN = new JPanel();
        pS = new JPanel();

        lblChefId = new JLabel("Chef ID: ");
        lblFirstName = new JLabel("First Name: ");
        lblMiddleName = new JLabel("Middle Name: ");
        lblSurname = new JLabel("Last Name: ");

        lblEmail = new JLabel("Email Address: ");
        lblCellPhoneNumber = new JLabel("CellPhone Number: ");

        txtChefId = new JTextField(30);
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

        pN.add(lblChefId);
        pN.add(txtChefId);

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
            store(txtChefId.getText(),
                    txtEmail.getText(),
                    txtCellPhoneNumber.getText(),name);
        }
        if(e.getSource() == btnCancel) {
            ChefMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public void store(String chefId, String email,String cellPhoneNumber,Name name) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/chef/save";
            Chef chef = ChefFactory.build(chefId,email,cellPhoneNumber,name);
            Gson g = new Gson();
            String jsonString = g.toJson(chef);
            String r = post(URL, jsonString);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Chef saved successfully!");
                ChefMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Chef not saved.");
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
        new SaveChefGUI().setGUI();
    }
}
