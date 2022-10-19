package za.ac.cput.views.orderTable;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.OrderTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewOrderTableGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnBack;

    public ViewOrderTableGUI(){
        super("All Order Table");
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
        model.addColumn("Order Table Id");
        model.addColumn("Number Of Guest");
        model.addColumn("Order Id");
        model.addColumn("Order Details");
        model.addColumn("Customer Id");
        model.addColumn("Waiter Id");
        model.addColumn("Chef Id");


        try {
            final String URL = "http://localhost:9210/restaurant-management/order-table/find-all";
            String responseBody = run(URL);
            JSONArray orderTables = new JSONArray(responseBody);

            for (int i = 0; i < orderTables.length(); i++) {
                JSONObject orderTable = orderTables.getJSONObject(i);

                Gson g = new Gson();
                OrderTable b = g.fromJson(orderTable.toString(), OrderTable.class);

                Object[] rowData = new Object[7];
                rowData[0] = b.getOrderTableId();
                rowData[1] = b.getNumberOfGuest();
                rowData[2] = b.getOrder().getOrderId();
                rowData[3] = b.getOrder().getOrderDetails();
                rowData[4] = b.getOrder().getCustomerId();
                rowData[5] = b.getOrder().getWaiterId();
                rowData[6] = b.getOrder().getChefId();
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
            OrderTableMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new ViewOrderTableGUI().setGUI();
    }
}

