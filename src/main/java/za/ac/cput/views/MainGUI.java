package za.ac.cput.views;

import za.ac.cput.views.chef.ChefMainGUI;
import za.ac.cput.views.chefAddress.ChefAddressMainGUI;
import za.ac.cput.views.customer.CustomerMainGUI;
import za.ac.cput.views.menu.MenuMainGUI;
import za.ac.cput.views.menuItem.MenuItemMainGUI;
import za.ac.cput.views.orderTable.OrderTableMainGUI;
import za.ac.cput.views.payment.PaymentMainGUI;
import za.ac.cput.views.waiter.WaiterMainGUI;
import za.ac.cput.views.waiterAddress.WaiterAddressMainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame implements ActionListener {
    private JButton btnCustomer,btnChef,btnWaiter,btnChefAddress,btnWaiterAddress,btnTable,btnPayment,btnMenuItem,btnMenu;

    private JLabel lblHeading;
    private JPanel pN,pC;
    public Font hFt;

    public MainGUI(){
        super("Restaurant Management System");

        pN=new JPanel();
        pC=new JPanel();

        btnCustomer=new JButton("Customer");//save completed
        btnChef=new JButton("Chef");//save Completed
        btnWaiter=new JButton("Waiter");//save completed
        btnChefAddress=new JButton("Chef Address");
        btnWaiterAddress=new JButton("Waiter Address");
        btnTable=new JButton("Order Table");
        btnPayment=new JButton("Payment");
        btnMenuItem=new JButton("Menu Item");
        btnMenu=new JButton("Menu");

        lblHeading=new JLabel("Main Menu",SwingConstants.CENTER);
        hFt=new Font("Arial",Font.BOLD,30);
    }
    public void setGUI(){
        pN.setLayout(new FlowLayout(FlowLayout.CENTER));
        pC.setLayout(new GridLayout(11,1));

        this.setPreferredSize(new Dimension(600,600));

        lblHeading.setFont(hFt);

        pN.add(lblHeading);

        pC.add(btnCustomer);
        pC.add(btnChef) ;
        pC.add(btnWaiter);
        pC.add(btnChefAddress);
        pC.add(btnWaiterAddress);
        pC.add(btnTable);
        pC.add(btnPayment);
        pC.add(btnMenuItem);
        pC.add(btnMenu);

        this.add(pN,BorderLayout.NORTH);
        this.add(pC,BorderLayout.CENTER);

        btnCustomer.addActionListener(this);
        btnChef.addActionListener(this);
        btnWaiter.addActionListener(this);
        btnChefAddress.addActionListener(this);
        btnWaiterAddress.addActionListener(this);
        btnTable.addActionListener(this);
        btnPayment.addActionListener(this);
        btnMenuItem.addActionListener(this);
        btnMenu.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Customer":
                CustomerMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Waiter":
                WaiterMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Waiter Address":
                WaiterAddressMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Chef":
                ChefMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Chef Address":
                ChefAddressMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Order Table":
                OrderTableMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Payment":
                PaymentMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Menu Item":
                MenuItemMainGUI.main(null);
                this.setVisible(false);
                break;
            case "Menu":
                MenuMainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }
    public static void main(String[] args) {
        new MainGUI().setGUI();
    }
}

