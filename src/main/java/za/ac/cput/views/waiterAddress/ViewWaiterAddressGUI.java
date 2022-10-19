package za.ac.cput.views.waiterAddress;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.WaiterAddress;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//2199099839
//Nawaaz Amien
public class ViewWaiterAddressGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnBack;

    public ViewWaiterAddressGUI(){
        super("All WaiterAddress");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WaiterAddressMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new ViewWaiterAddressGUI().setGUI();
    }
}
