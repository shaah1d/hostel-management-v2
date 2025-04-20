package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaintenanceRequest extends JFrame implements ActionListener {

    JButton submit, back;
    JTextField tfRoomNumber;
    JComboBox<String> issueType;
    JTextArea issueDescription;
    JLabel labelUsername;
    String username;

    MaintenanceRequest(String username){
        this.username = username;
        setTitle("Maintenance Request");

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

        //Creating label for room number
        JLabel lblRoomNumber = new JLabel("Room Number");
        lblRoomNumber.setBounds(20, 100, 150, 30);
        lblRoomNumber.setForeground(new Color(108,48,130));
        lblRoomNumber.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblRoomNumber);

        //Creating text field for room number
        tfRoomNumber = new JTextField();
        tfRoomNumber.setBounds(200, 100, 150, 30);
        add(tfRoomNumber);

        //Creating label for issue type
        JLabel lblIssueType = new JLabel("Issue Type");
        lblIssueType.setBounds(20, 150, 150, 30);
        lblIssueType.setForeground(new Color(108,48,130));
        lblIssueType.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblIssueType);

        //Creating dropdown for issue type
        String[] issues = {"Plumbing", "Electrical", "Furniture", "Air Conditioning", "Internet", "Other"};
        issueType = new JComboBox<>(issues);
        issueType.setBounds(200, 150, 150, 30);
        issueType.setBackground(Color.WHITE);
        add(issueType);

        //Creating label for issue description
        JLabel lblDescription = new JLabel("Description");
        lblDescription.setBounds(20, 200, 150, 30);
        lblDescription.setForeground(new Color(108,48,130));
        lblDescription.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblDescription);

        //Creating text area for issue description
        issueDescription = new JTextArea();
        issueDescription.setBounds(200, 200, 400, 150);
        issueDescription.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        issueDescription.setLineWrap(true);
        issueDescription.setWrapStyleWord(true);
        add(issueDescription);

        //Creating button to submit request
        submit = new JButton("Submit Request");
        submit.setBounds(20, 380, 180, 30);
        submit.setBackground(new Color(227,218,255));
        submit.setForeground(new Color(85, 20, 90));
        submit.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        submit.addActionListener(this);
        add(submit);

        //Creating button to go back
        back = new JButton("Back");
        back.setBounds(210, 380, 100, 30);
        back.setBackground(new Color(227,218,255));
        back.setForeground(new Color(85, 20, 90));
        back.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        //Adding maintenance information
        JTextArea infoArea = new JTextArea();
        infoArea.setBounds(20, 430, 700, 200);
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(240, 240, 240));
        infoArea.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        infoArea.setText("Maintenance Request Information:\n\n" +
                        "1. All maintenance requests are processed within 24 hours.\n" +
                        "2. Emergency issues (water leakage, electrical hazards) are addressed on priority.\n" +
                        "3. Maintenance staff works from 8 AM to 8 PM daily.\n" +
                        "4. For emergency maintenance after hours, please contact the hostel warden.\n" +
                        "5. You will receive a confirmation once your request is processed.\n" +
                        "6. Please be present in your room during the scheduled maintenance time.");
        add(infoArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String roomNum = tfRoomNumber.getText();
            String issue = (String) issueType.getSelectedItem();
            String description = issueDescription.getText();
            
            if(roomNum.isEmpty() || description.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all fields!");
            } else {
                JOptionPane.showMessageDialog(null, "Maintenance request submitted successfully!\nA maintenance staff will attend to your issue soon.");
                tfRoomNumber.setText("");
                issueDescription.setText("");
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new MaintenanceRequest("");
    }
}