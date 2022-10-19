package za.ac.cput.views.menuItem;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class DeleteMenuItemGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnDelete,btnBack;
    private JLabel lblDelete,blank1,blank2,blank3,blank4,blank5,blank6;
    private JTextField txtDeleteId;

    public DeleteMenuItemGUI(){
        super("Delete Menu Item");

        table=new JTable();

        pC=new JPanel();
        pS=new JPanel();

        btnDelete=new JButton("Delete");
        btnBack=new JButton("Back");

        lblDelete=new JLabel("Enter Title to Delete:");
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
        pS.setLayout(new GridLayout(7,2));

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
        model.addColumn("Title");
        model.addColumn("Order Id");
        model.addColumn("Order Details");
        model.addColumn("Customer Id");
        model.addColumn("Waiter Id");
        model.addColumn("Chef Id");
        try {
            final String URL = "http://localhost:9210/restaurant-management/menu-item/find-all";
            String responseBody = run(URL);
            JSONArray menuItems = new JSONArray(responseBody);

            for (int i = 0; i < menuItems.length(); i++) {
                JSONObject menuItem = menuItems.getJSONObject(i);

                Gson g = new Gson();
                MenuItem b = g.fromJson(menuItem.toString(), MenuItem.class);

                Object[] rowData = new Object[7];
                rowData[0] = b.getTitle();
                rowData[1] = b.getOrder().getOrderId();
                rowData[2] = b.getOrder().getOrderDetails();
                rowData[3] = b.getOrder().getCustomerId();
                rowData[4] = b.getOrder().getWaiterId();
                rowData[5] = b.getOrder().getChefId();
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
        final String URL = "http://localhost:9210/restaurant-management/menu-item/delete/" + id ;
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
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Delete Menu Item", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        if(request(txtDeleteId.getText())) {
                            JOptionPane.showMessageDialog(null,"Menu Item Deleted");
                            MenuItemMainGUI.main(null);
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null,"Problem,Title Not Deleted");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a value");
            }
        } else if (e.getSource() == btnBack) {
            MenuItemMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new DeleteMenuItemGUI().setGUI();
    }
}

