package za.ac.cput.views.waiter;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Waiter;
import za.ac.cput.views.customer.CustomerMainGUI;
import za.ac.cput.views.customer.ViewCustomerGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ViewWaiterGUI extends JFrame implements ActionListener {
    private static OkHttpClient client=new OkHttpClient();

    private JTable table;
    private JPanel pC,pS;
    private JButton btnBack;

    public ViewWaiterGUI(){
        super("All Waiters");
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
        model.addColumn("Waiter email");
        model.addColumn("Waiter CellPhoneNumber");
        model.addColumn("Waiter FirstName");
        model.addColumn("Waiter MiddleName");
        model.addColumn("Waiter LastName");

        try {
            final String URL = "http://localhost:9210/restaurant-management/waiter/find-all";
            String responseBody = run(URL);
            JSONArray waiters = new JSONArray(responseBody);

            for (int i = 0; i < waiters.length(); i++) {
                JSONObject waiter = waiters.getJSONObject(i);

                Gson g = new Gson();
                Waiter b = g.fromJson(waiter.toString(), Waiter.class);

                Object[] rowData = new Object[6];
                rowData[0] = b.getWaiterId();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            WaiterMainGUI.main(null);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {

        new ViewWaiterGUI().setGUI();
    }
}
