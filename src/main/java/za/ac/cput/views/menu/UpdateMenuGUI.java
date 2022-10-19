package za.ac.cput.views.menu;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.MenuFactory;
import za.ac.cput.factory.OrderFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class UpdateMenuGUI extends JFrame implements ActionListener {
    static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    private JTable table;
    private JPanel pN, pC, pS, pFields, pUpdate;
    private JButton btnUpdate, btnBack, btnEnter;
    private JLabel lblUpdate, blank1, blank2, blank3, blank4,
            blank5, blank6, blank7, blank8,
            lblMenuId, lblMenuDetails, lblTitle, lblOrderId, lblOrderDetails, lblCustomerId, lblWaiterId, lblChefId;
    private JTextField txtUpdateId, txtMenuId, txtMenuDetails, txtTitle, txtOrderId, txtOrderDetails, txtCustomerId, txtWaiterId, txtChefId;

    public UpdateMenuGUI() {
        super("Update Menu");
        table = new JTable();

        pN = new JPanel();
        pC = new JPanel();
        pS = new JPanel();
        pUpdate = new JPanel();
        pFields = new JPanel();

        btnUpdate = new JButton("Update");
        btnBack = new JButton("Back");
        btnEnter = new JButton("Enter");

        lblUpdate = new JLabel("Enter Menu ID to Update: ", SwingConstants.CENTER);
        txtUpdateId = new JTextField();

        lblMenuId = new JLabel("Menu ID: ", SwingConstants.CENTER);
        lblMenuDetails = new JLabel("Menu Details: ", SwingConstants.CENTER);
        lblTitle = new JLabel("Title: ", SwingConstants.CENTER);
        lblOrderId = new JLabel("Order ID: ", SwingConstants.CENTER);
        lblOrderDetails = new JLabel("Order Details: ", SwingConstants.CENTER);
        lblCustomerId = new JLabel("Customer ID: ", SwingConstants.CENTER);
        lblWaiterId = new JLabel("Waiter ID: ", SwingConstants.CENTER);
        lblChefId = new JLabel("Chef ID ", SwingConstants.CENTER);

        txtMenuId = new JTextField();
        txtMenuDetails = new JTextField();
        txtTitle = new JTextField();
        txtOrderId = new JTextField();
        txtOrderDetails = new JTextField();
        txtCustomerId = new JTextField();
        txtWaiterId = new JTextField();
        txtChefId = new JTextField();

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
        pN.setLayout(new GridLayout(0, 1));
        pC.setLayout(new GridLayout(0, 1));
        pUpdate.setLayout(new GridLayout(3, 3));
        pFields.setLayout(new GridLayout(4, 2));
        pS.setLayout(new GridLayout(2, 2));

        pN.add(new JScrollPane(table));

        pUpdate.add(blank1);
        pUpdate.add(blank2);
        pUpdate.add(blank3);
        pUpdate.add(lblUpdate);
        pUpdate.add(txtUpdateId);
        pUpdate.add(btnEnter);
        pUpdate.add(blank4);
        pUpdate.add(blank5);

        pFields.add(lblMenuId);
        pFields.add(txtMenuId);
        pFields.add(lblMenuDetails);
        pFields.add(txtMenuDetails);
        pFields.add(lblTitle);
        pFields.add(txtTitle);
        pFields.add(lblOrderId);
        pFields.add(txtOrderId);
        pFields.add(lblOrderDetails);
        pFields.add(txtOrderDetails);
        pFields.add(lblCustomerId);
        pFields.add(txtCustomerId);
        pFields.add(lblWaiterId);
        pFields.add(txtWaiterId);
        pFields.add(lblChefId);
        pFields.add(txtChefId);

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
        model.addColumn("Menu Id");
        model.addColumn("Menu Details");
        model.addColumn("Title");
        model.addColumn("Order Id");
        model.addColumn("Order Details");
        model.addColumn("Customer Id");
        model.addColumn("Waiter Id");
        model.addColumn("Chef Id");

        try {
            final String URL = "http://localhost:9210/restaurant-management/menu/find-all";
            String responseBody = run(URL);
            JSONArray menus = new JSONArray(responseBody);

            for (int i = 0; i < menus.length(); i++) {
                JSONObject menu = menus.getJSONObject(i);

                Gson g = new Gson();
                Menu b = g.fromJson(menu.toString(), Menu.class);

                Object[] rowData = new Object[8];
                rowData[0] = b.getMenuId();
                rowData[1] = b.getMenuDetails();
                rowData[2] = b.getTitle();
                rowData[3] = b.getOrder().getOrderId();
                rowData[4] = b.getOrder().getOrderDetails();
                rowData[5] = b.getOrder().getCustomerId();
                rowData[6] = b.getOrder().getWaiterId();
                rowData[7] = b.getOrder().getChefId();
                model.addRow(rowData);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private Menu getMenu(String id) throws IOException {
        Menu menu = null;
        try {
            final String URL = "http://localhost:9210/restaurant-management/menu/read/" + id;
            String responseBody = run(URL);
            Gson gson = new Gson();
            menu = gson.fromJson(responseBody, Menu.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(menu);
        return menu;
    }

    public void store(String menuId, String menuDetails, String title, Order order) {
        try {
            final String URL = "http://localhost:9210/restaurant-management/menu/update";
            Menu menu = MenuFactory.build(menuId, menuDetails, title, order);
            Gson g = new Gson();
            String jsonString = g.toJson(menu);
            String r = put(URL, jsonString);
            System.out.println(r);
            if (r != null) {
                JOptionPane.showMessageDialog(null, "Menu updated successfully!");
                MenuMainGUI.main(null);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Oops, Menu not updated.");
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
            case "Enter":
                if (!Objects.equals(txtUpdateId.getText(), "")) {
                    try {
                        Menu b = getMenu(txtUpdateId.getText());
                        if (b != null) {
                            pFields.setVisible(true);
                            txtMenuId.setText(b.getMenuId());
                            txtMenuDetails.setText(b.getMenuDetails());
                            txtTitle.setText(b.getTitle());
                            txtOrderId.setText(b.getOrder().getOrderId());
                            txtOrderDetails.setText(b.getOrder().getOrderDetails());
                            txtCustomerId.setText(b.getOrder().getCustomerId());
                            txtWaiterId.setText(b.getOrder().getWaiterId());
                            txtChefId.setText(b.getOrder().getChefId());
                        } else {
                            JOptionPane.showMessageDialog(null, "No Menu with that ID");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Menu ID");
                }
                break;
            case "Update":
                Order order = OrderFactory.build(txtOrderId.getText(), txtOrderDetails.getText(), txtCustomerId.getText(), txtWaiterId.getText(), txtChefId.getText());
                store(txtMenuId.getText(),
                        txtMenuDetails.getText(),
                        txtTitle.getText(),
                        order);
                break;
            case "Back":
                MenuMainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }

    public static void main(String[] args) {

        new UpdateMenuGUI().setGUI();

    }
}
