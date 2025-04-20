package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomBooking extends JFrame implements ActionListener {

    JButton checkAvailability, bookRoom, back;
    JComboBox<String> roomType, floorNumber, roomDuration;
    JLabel labelUsername, labelPrice;
    String username;
    JTextArea bookingDetails;

    RoomBooking(String username){
        this.username = username;
        setTitle("Room Booking");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        getContentPane().setBackground(new Color(230,222,255));

        //Creating label for the username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 50, 100, 30);
        lblUsername.setForeground(new Color(108,48,130));
        lblUsername.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblUsername);

        //creating label that stores the original username
        labelUsername = new JLabel(username);
        labelUsername.setBounds(150, 50, 150, 30);
        labelUsername.setForeground(new Color(108,48,130));
        labelUsername.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        add(labelUsername);

        //Creating label for room type
        JLabel lblRoomType = new JLabel("Room Type");
        lblRoomType.setBounds(20, 100, 150, 30);
        lblRoomType.setForeground(new Color(108,48,130));
        lblRoomType.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblRoomType);

        //Creating dropdown for room type
        String[] rooms = {"Single Room", "Double Room", "Triple Room", "Quad Room"};
        roomType = new JComboBox<>(rooms);
        roomType.setBounds(200, 100, 150, 30);
        roomType.setBackground(Color.WHITE);
        add(roomType);

        //Creating label for floor number
        JLabel lblFloor = new JLabel("Floor Number");
        lblFloor.setBounds(20, 150, 150, 30);
        lblFloor.setForeground(new Color(108,48,130));
        lblFloor.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblFloor);

        //Creating dropdown for floor
        String[] floors = {"Ground Floor", "First Floor", "Second Floor", "Third Floor"};
        floorNumber = new JComboBox<>(floors);
        floorNumber.setBounds(200, 150, 150, 30);
        floorNumber.setBackground(Color.WHITE);
        add(floorNumber);

        //Creating label for duration
        JLabel lblDuration = new JLabel("Duration");
        lblDuration.setBounds(20, 200, 150, 30);
        lblDuration.setForeground(new Color(108,48,130));
        lblDuration.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblDuration);

        //Creating dropdown for duration
        String[] durations = {"1 Month", "3 Months", "6 Months", "1 Year"};
        roomDuration = new JComboBox<>(durations);
        roomDuration.setBounds(200, 200, 150, 30);
        roomDuration.setBackground(Color.WHITE);
        add(roomDuration);

        //Creating button to check availability
        checkAvailability = new JButton("Check Availability");
        checkAvailability.setBounds(20, 250, 200, 30);
        checkAvailability.setBackground(new Color(227,218,255));
        checkAvailability.setForeground(new Color(85, 20, 90));
        checkAvailability.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        checkAvailability.addActionListener(this);
        add(checkAvailability);

        //Creating label for price
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(20, 300, 100, 30);
        lblPrice.setForeground(new Color(108,48,130));
        lblPrice.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblPrice);

        //Creating label to display price
        labelPrice = new JLabel("");
        labelPrice.setBounds(150, 300, 200, 30);
        labelPrice.setForeground(new Color(108,48,130));
        labelPrice.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        add(labelPrice);

        //Creating text area for booking details
        bookingDetails = new JTextArea();
        bookingDetails.setBounds(400, 100, 400, 300);
        bookingDetails.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        bookingDetails.setEditable(false);
        add(bookingDetails);

        //Creating button to book room
        bookRoom = new JButton("Book Room");
        bookRoom.setBounds(20, 350, 150, 30);
        bookRoom.setBackground(new Color(227,218,255));
        bookRoom.setForeground(new Color(85, 20, 90));
        bookRoom.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        bookRoom.addActionListener(this);
        add(bookRoom);

        //Creating button to go back
        back = new JButton("Back");
        back.setBounds(180, 350, 100, 30);
        back.setBackground(new Color(227,218,255));
        back.setForeground(new Color(85, 20, 90));
        back.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == checkAvailability){
            String room = (String) roomType.getSelectedItem();
            String floor = (String) floorNumber.getSelectedItem();
            String duration = (String) roomDuration.getSelectedItem();
            
            // Calculate price based on room type and duration
            int price = 0;
            if(room.equals("Single Room")) {
                price = 5000;
            } else if(room.equals("Double Room")) {
                price = 8000;
            } else if(room.equals("Triple Room")) {
                price = 10000;
            } else if(room.equals("Quad Room")) {
                price = 12000;
            }
            
            if(duration.equals("3 Months")) {
                price = price * 3;
            } else if(duration.equals("6 Months")) {
                price = price * 6;
            } else if(duration.equals("1 Year")) {
                price = price * 12;
            }
            
            labelPrice.setText("₹" + price);
        }
        else if(ae.getSource() == bookRoom){
            String room = (String) roomType.getSelectedItem();
            String floor = (String) floorNumber.getSelectedItem();
            String duration = (String) roomDuration.getSelectedItem();
            
            bookingDetails.setText("Booking Details:\n\n" +
                                  "Username: " + username + "\n" +
                                  "Room Type: " + room + "\n" +
                                  "Floor: " + floor + "\n" +
                                  "Duration: " + duration + "\n" +
                                  "Price: " + labelPrice.getText() + "\n\n" +
                                  "Room booked successfully!\n" +
                                  "Please proceed to payment.");
            
            JOptionPane.showMessageDialog(null, "Room booked successfully!");
        }
        else if(ae.getSource() == back){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new RoomBooking("");
    }
}