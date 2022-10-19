package za.ac.cput.views.orderTable;

import za.ac.cput.views.MainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderTableMainGUI extends JFrame implements ActionListener {
    private JButton btnView, btnSave, btnUpdate, btnDelete, btnBack;
    private JLabel lblHeading;
    private JPanel panelNorth, panelCenter, panelSouth;
    private Font headingFont;

    public OrderTableMainGUI()
    {
        super("Order Table Main Menu");

        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();

        btnView = new JButton("View All Order Table");
        btnSave = new JButton("Save New Order Table");
        btnUpdate = new JButton("Update Order Table");
        btnDelete = new JButton("Delete Order Table");
        btnBack = new JButton("Back");

        lblHeading = new JLabel("Order Table", SwingConstants.CENTER);

        headingFont = new Font("Arial", Font.BOLD, 30);
    }

    public void setGUI()
    {
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelCenter.setLayout(new GridLayout(4, 1));
        panelSouth.setLayout(new GridLayout(1, 1));

        this.setPreferredSize(new Dimension(600, 600));

        lblHeading.setFont(headingFont);

        panelNorth.add(lblHeading);

        panelCenter.add(btnView);
        panelCenter.add(btnSave);
        panelCenter.add(btnUpdate);
        panelCenter.add(btnDelete);

        panelSouth.add(btnBack);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        btnView.addActionListener(this);
        btnSave .addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch (e.getActionCommand())
        {
            case "View All Order Table":
                ViewOrderTableGUI.main(null);
                this.setVisible(false);
                break;
            case "Save New Order Table":
                SaveOrderTableGUI.main(null);
                this.setVisible(false);
                break;
            case "Update Order Table":
                UpdateOrderTableGUI.main(null);
                this.setVisible(false);
                break;
            case "Delete Order Table":
                DeleteOrderTableGUI.main(null);
                this.setVisible(false);
                break;
            case "Back":
                MainGUI.main(null);
                this.setVisible(false);
                break;
        }
    }

    public static void main(String[] args)
    {
        new OrderTableMainGUI().setGUI();
    }
}

