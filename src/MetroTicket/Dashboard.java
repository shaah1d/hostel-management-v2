package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    JButton roomBooking, mealPlans, payFees, facilityInfo, maintenanceRequest;
    String username;

    Dashboard(String username){
        this.username = username;

        //Creating the frame
        setTitle("Hostel Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        //Creating a panel
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(236,230,255));
        p1.setBounds(0, 0 , 1600, 60);
        add(p1);

        //Adding image at dashboard
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(50,50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel icon = new JLabel(i3);
        icon.setBounds(5,0,50,50);
        p1.add(icon);

        //Creating a label for heading
        JLabel heading = new JLabel("Hostel Management Dashboard");
        heading.setBounds(80, 10, 400, 40);
        heading.setForeground(Color.DARK_GRAY);
        heading.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 30));
        p1.add(heading);

        //Creating panel for the menu
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(224,214,250));
        p2.setBounds(0, 65 , 1600, 50);
        add(p2);

        //Adding button to book room
        roomBooking = new JButton("Book Room");
        roomBooking.setBounds(10, 10, 200, 30);
        roomBooking.setBackground(new Color(227,218,255));
        roomBooking.setForeground(new Color(85, 20, 90));
        roomBooking.setFont(new Font("CENTURY GOTHIC" , Font.BOLD, 20));
        roomBooking.addActionListener(this);
        p2.add(roomBooking);

        //Adding button to meal plans
        mealPlans = new JButton("Meal Plans");
        mealPlans.setBounds(220, 10, 200, 30);
        mealPlans.setBackground(new Color(227,218,255));
        mealPlans.setForeground(new Color(85, 20, 90));
        mealPlans.setFont(new Font("CENTURY GOTHIC" , Font.BOLD, 20));
        mealPlans.addActionListener(this);
        p2.add(mealPlans);

        //Adding button to pay fees
        payFees = new JButton("Pay Fees");
        payFees.setBounds(440, 10, 200, 30);
        payFees.setBackground(new Color(227,218,255));
        payFees.setForeground(new Color(85, 20, 90));
        payFees.setFont(new Font("CENTURY GOTHIC" , Font.BOLD, 20));
        payFees.addActionListener(this);
        p2.add(payFees);

        //Adding button to facility info
        facilityInfo = new JButton("Facility Info");
        facilityInfo.setBounds(660, 10, 200, 30);
        facilityInfo.setBackground(new Color(227,218,255));
        facilityInfo.setForeground(new Color(85, 20, 90));
        facilityInfo.setFont(new Font("CENTURY GOTHIC" , Font.BOLD, 20));
        facilityInfo.addActionListener(this);
        p2.add(facilityInfo);

        //Adding button to maintenance request
        maintenanceRequest = new JButton("Maintenance");
        maintenanceRequest.setBounds(880, 10, 200, 30);
        maintenanceRequest.setBackground(new Color(227,218,255));
        maintenanceRequest.setForeground(new Color(85, 20, 90));
        maintenanceRequest.setFont(new Font("CENTURY GOTHIC" , Font.BOLD, 20));
        maintenanceRequest.addActionListener(this);
        p2.add(maintenanceRequest);

        //Image for the dashboard
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(1650, 700, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6);
        image.setBounds(0, 100, 1650, 700);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == roomBooking){
            new RoomBooking(username);
        }
        else if(ae.getSource() == mealPlans){
            new MealPlans(username);
        }
        else if(ae.getSource() == payFees){
            new PayFees(username);
        }
        else if(ae.getSource() == facilityInfo){
            new FacilityInfo(username);
        }
        else if(ae.getSource() == maintenanceRequest){
            new MaintenanceRequest(username);
        }
    }

    public static void main(String[] args){
        new Dashboard("");
    }
}
