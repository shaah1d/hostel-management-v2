package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FacilityInfo extends JFrame implements ActionListener {

    JLabel labelUsername;
    JButton back;
    JTextArea facilityInfoTextArea;
    String username;
    JScrollPane scrollPane;
    // Use a Map to store facility details
    private Map<String, String> facilityDetails = new HashMap<>();

    FacilityInfo(String username) {
        this.username = username;
        setTitle("Hostel Facility Information");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        //Creating label for the title
        JLabel lblTitle = new JLabel("Hostel Facility Information");
        lblTitle.setBounds(20, 20, 350, 30);
        lblTitle.setForeground(new Color(108,48,130));
        lblTitle.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 24));
        add(lblTitle);

        //creating label that stores the original username
        labelUsername = new JLabel(username);
        labelUsername.setBounds(150, 60, 100, 30);
        labelUsername.setForeground(new Color(108,48,130));
        labelUsername.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        add(labelUsername);

        // Text area to display facility information
        facilityInfoTextArea = new JTextArea();
        facilityInfoTextArea.setBounds(0, 0, 700, 500);
        facilityInfoTextArea.setBackground(new Color(240, 240, 240));
        facilityInfoTextArea.setFont(new Font("Century Gothic", Font.PLAIN, 16));
        facilityInfoTextArea.setEditable(false);
        facilityInfoTextArea.setLineWrap(true);
        facilityInfoTextArea.setWrapStyleWord(true);

        // Add a scroll pane for the text area
        scrollPane = new JScrollPane(facilityInfoTextArea);
        scrollPane.setBounds(20, 100, 900, 500);
        add(scrollPane);

        // Initialize facility details
        initializeFacilityDetails();
        
        // Display facility information
        displayFacilityInfo();

        // Back button
        back = new JButton("Back");
        back.setBounds(20, 620, 100, 30);
        back.setBackground(new Color(227,218,255));
        back.setForeground(new Color(85, 20, 90));
        back.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    private void initializeFacilityDetails() {
        facilityDetails.put("Rooms", "Our hostel offers various room types:\n" +
                "- Single Rooms: Private rooms with attached bathroom\n" +
                "- Double Rooms: Shared rooms with two beds and attached bathroom\n" +
                "- Triple Rooms: Shared rooms with three beds and attached bathroom\n" +
                "- Quad Rooms: Shared rooms with four beds and attached bathroom\n\n" +
                "All rooms are equipped with:\n" +
                "- Study tables and chairs\n" +
                "- Wardrobes\n" +
                "- High-speed Wi-Fi\n" +
                "- Air conditioning\n" +
                "- Regular housekeeping services");

        facilityDetails.put("Dining", "Our dining facilities include:\n" +
                "- Modern cafeteria serving breakfast, lunch, and dinner\n" +
                "- Multiple meal plan options (vegetarian, non-vegetarian, mixed)\n" +
                "- Hygienic food preparation\n" +
                "- Special diet accommodations available upon request\n" +
                "- 24/7 access to drinking water and basic refreshments");

        facilityDetails.put("Recreation", "Recreation facilities include:\n" +
                "- Indoor games room (table tennis, carrom, chess)\n" +
                "- Outdoor sports area (basketball, volleyball)\n" +
                "- Fitness center with modern equipment\n" +
                "- TV lounge with streaming services\n" +
                "- Reading room and library");

        facilityDetails.put("Security", "Security measures include:\n" +
                "- 24/7 security personnel\n" +
                "- CCTV surveillance throughout the premises\n" +
                "- Biometric access control for entry\n" +
                "- Regular security patrols\n" +
                "- Emergency response system");

        facilityDetails.put("Utilities", "Utilities and services include:\n" +
                "- Laundry facilities (washing machines and dryers)\n" +
                "- High-speed internet throughout the building\n" +
                "- Study rooms for quiet learning\n" +
                "- Power backup for uninterrupted electricity\n" +
                "- Medical assistance on call");
    }

    private void displayFacilityInfo() {
        StringBuilder info = new StringBuilder();
        info.append("HOSTEL FACILITIES INFORMATION\n\n");
        
        for (Map.Entry<String, String> entry : facilityDetails.entrySet()) {
            info.append("=== ").append(entry.getKey().toUpperCase()).append(" ===\n");
            info.append(entry.getValue()).append("\n\n");
        }
        
        info.append("HOSTEL RULES:\n\n");
        info.append("1. Maintain silence during study hours (8 PM - 10 PM)\n");
        info.append("2. No visitors allowed after 8 PM\n");
        info.append("3. Keep your rooms and common areas clean\n");
        info.append("4. Report any maintenance issues promptly\n");
        info.append("5. Respect fellow residents and staff\n");
        info.append("6. Smoking and alcohol are strictly prohibited\n");
        info.append("7. Conserve water and electricity\n");
        
        facilityInfoTextArea.setText(info.toString());
        facilityInfoTextArea.setCaretPosition(0);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new FacilityInfo("");
    }
}