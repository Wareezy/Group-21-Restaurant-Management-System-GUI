package za.ac.cput.views.menu;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewMenuGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnBack;

    public ViewMenuGUI(){
        super("All Menu");
        table=new JTable();

        pC=new JPanel();
        pS=new JPanel();

        btnBack=new JButton("Back");

    }
    public void setGUI(){
        pC.setLayout(new GridLayout(1,1));
        pS.setLayout(new GridLayout(1,1));

        pC.add(table);
        pS.add(btnBack);

        this.add(pC,BorderLayout.CENTER);
        this.add(pS,BorderLayout.SOUTH);

        btnBack.addActionListener(this);

        findAll();
        table.setRowHeight(30);
        this.add(new JScrollPane(table));
        this.pack();
        this.setSize(1000,450);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void findAll(){
        DefaultTableModel model=(DefaultTableModel)  table.getModel();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            MenuMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new ViewMenuGUI().setGUI();
    }
}
