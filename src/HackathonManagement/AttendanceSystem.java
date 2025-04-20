package HackathonManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import MetroTicket.Conn;

public class AttendanceSystem extends JFrame implements ActionListener {

    JTextField participantIdField;
    JButton markAttendance, viewAttendance, back;
    String username;
    JTextArea attendanceInfoArea;

    public AttendanceSystem(String username) {
        this.username = username;

        setTitle("Attendance System");
        setBounds(350, 200, 1000, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Attendance System");
        title.setBounds(400, 30, 300, 35);
        title.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 25));
        title.setForeground(new Color(85, 20, 90));
        add(title);

        JLabel participantIdLabel = new JLabel("Participant ID:");
        participantIdLabel.setBounds(50, 100, 100, 25);
        participantIdLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(participantIdLabel);

        participantIdField = new JTextField();
        participantIdField.setBounds(160, 100, 200, 25);
        add(participantIdField);

        markAttendance = new JButton("Mark Attendance");
        markAttendance.setBounds(50, 150, 310, 30);
        markAttendance.setBackground(new Color(85, 20, 90));
        markAttendance.setForeground(Color.WHITE);
        markAttendance.addActionListener(this);
        add(markAttendance);

        viewAttendance = new JButton("View Attendance");
        viewAttendance.setBounds(50, 190, 310, 30);
        viewAttendance.setBackground(new Color(85, 20, 90));
        viewAttendance.setForeground(Color.WHITE);
        viewAttendance.addActionListener(this);
        add(viewAttendance);

        attendanceInfoArea = new JTextArea();
        attendanceInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(attendanceInfoArea);
        scrollPane.setBounds(400, 100, 550, 300);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(50, 400, 100, 30);
        back.setBackground(new Color(85, 20, 90));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == markAttendance) {
            String participantId = participantIdField.getText();
            if (!participantId.equals("")) {
                try {
                    Conn c = new Conn();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentTime = dateFormat.format(new Date());
                    
                    String query = "INSERT INTO attendance (participant_id, check_in_time) VALUES (?, ?)"; 
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, participantId);
                    pst.setString(2, currentTime);
                    pst.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Attendance marked successfully!");
                    participantIdField.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error marking attendance!");
                }
            }
        } else if (ae.getSource() == viewAttendance) {
            try {
                Conn c = new Conn();
                String query = "SELECT participant_id, check_in_time FROM attendance ORDER BY check_in_time DESC";
                ResultSet rs = c.stmt.executeQuery(query);
                
                StringBuilder info = new StringBuilder();
                info.append("Attendance Records:\n\n");
                while (rs.next()) {
                    info.append("Participant ID: ").append(rs.getString("participant_id"))
                        .append("\nCheck-in Time: ").append(rs.getString("check_in_time"))
                        .append("\n\n");
                }
                attendanceInfoArea.setText(info.toString());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching attendance records!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AttendanceSystem("");
    }
}