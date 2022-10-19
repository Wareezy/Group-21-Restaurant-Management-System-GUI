package za.ac.cput.views.customer;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class DeleteCustomerGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnDelete,btnBack;
    private JLabel lblDelete,blank1,blank2,blank3,blank4,blank5,blank6;
    private JTextField txtDeleteId;

    public DeleteCustomerGUI(){
        super("Delete Customers");

        table=new JTable();

        pC=new JPanel();
        pS=new JPanel();

        btnDelete=new JButton("Delete");
        btnBack=new JButton("Back");

        lblDelete=new JLabel("Enter Customer ID to Delete:");
        txtDeleteId=new JTextField();
        blank1=new JLabel("");
        blank2=new JLabel("");
        blank3=new JLabel("");
        blank4=new JLabel("");
        blank5=new JLabel("");
        blank6=new JLabel("");

    }

    public void setGUI(){
        pC.setLayout(new GridLayout(1,1));
        pS.setLayout(new GridLayout(6,2));

        pC.add(table);

        pS.add(blank1);
        pS.add(blank2);

        pS.add(lblDelete);
        pS.add(txtDeleteId);

        pS.add(blank3);
        pS.add(blank4);

        pS.add(btnDelete);
        pS.add(btnBack);

        displayTable();

        this.add(pC, BorderLayout.CENTER);
        this.add(pS, BorderLayout.SOUTH);

        btnBack.addActionListener(this);
        btnDelete.addActionListener(this);

        table.setRowHeight(30);
        this.add(new JScrollPane(table));
        this.pack();
        this.setSize(1000,450);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void displayTable() {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addColumn("Customer ID");
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

    public boolean request(String id) throws IOException {
        final String URL = "http://localhost:9210/restaurant-management/customer/delete/" + id ;
        RequestBody body = RequestBody
                .create( "charset=utf-8", MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .delete(body) //this was at first post(body)--> why would we wanna save it doesnt make sense
                .addHeader("Accept", "application/json")
                .url(URL)
                .build();

        Response response = client.newCall(request).execute();
        return response.isSuccessful();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDelete) {
            if (!Objects.equals(txtDeleteId.getText(), "")) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Customer", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if(request(txtDeleteId.getText())) {
                            JOptionPane.showMessageDialog(null,"Customer Deleted");
                            CustomerMainGUI.main(null);
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null,"Problem, Customer Not Deleted");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a value");
            }
        } else if (e.getSource() == btnBack) {
            CustomerMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new DeleteCustomerGUI().setGUI();
    }

}
