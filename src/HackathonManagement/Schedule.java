package HackathonManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import MetroTicket.Conn;

public class Schedule extends JFrame implements ActionListener {

    JTextField eventNameField, eventTimeField, eventLocationField;
    JTextArea eventDescriptionArea, scheduleInfoArea;
    JButton addEvent, viewSchedule, back;
    String username;

    public Schedule(String username) {
        this.username = username;

        setTitle("Hackathon Schedule");
        setBounds(350, 200, 1000, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Hackathon Schedule Management");
        title.setBounds(350, 30, 400, 35);
        title.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 25));
        title.setForeground(new Color(85, 20, 90));
        add(title);

        JLabel eventNameLabel = new JLabel("Event Name:");
        eventNameLabel.setBounds(50, 100, 100, 25);
        eventNameLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventNameLabel);

        eventNameField = new JTextField();
        eventNameField.setBounds(160, 100, 200, 25);
        add(eventNameField);

        JLabel eventTimeLabel = new JLabel("Event Time:");
        eventTimeLabel.setBounds(50, 140, 100, 25);
        eventTimeLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventTimeLabel);

        eventTimeField = new JTextField();
        eventTimeField.setBounds(160, 140, 200, 25);
        add(eventTimeField);

        JLabel eventLocationLabel = new JLabel("Location:");
        eventLocationLabel.setBounds(50, 180, 100, 25);
        eventLocationLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventLocationLabel);

        eventLocationField = new JTextField();
        eventLocationField.setBounds(160, 180, 200, 25);
        add(eventLocationField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 220, 100, 25);
        descriptionLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(descriptionLabel);

        eventDescriptionArea = new JTextArea();
        eventDescriptionArea.setLineWrap(true);
        JScrollPane descScrollPane = new JScrollPane(eventDescriptionArea);
        descScrollPane.setBounds(160, 220, 200, 100);
        add(descScrollPane);

        addEvent = new JButton("Add Event");
        addEvent.setBounds(50, 340, 310, 30);
        addEvent.setBackground(new Color(85, 20, 90));
        addEvent.setForeground(Color.WHITE);
        addEvent.addActionListener(this);
        add(addEvent);

        viewSchedule = new JButton("View Schedule");
        viewSchedule.setBounds(50, 380, 310, 30);
        viewSchedule.setBackground(new Color(85, 20, 90));
        viewSchedule.setForeground(Color.WHITE);
        viewSchedule.addActionListener(this);
        add(viewSchedule);

        scheduleInfoArea = new JTextArea();
        scheduleInfoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(scheduleInfoArea);
        infoScrollPane.setBounds(400, 100, 550, 400);
        add(infoScrollPane);

        back = new JButton("Back");
        back.setBounds(50, 500, 100, 30);
        back.setBackground(new Color(85, 20, 90));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addEvent) {
            String eventName = eventNameField.getText();
            String eventTime = eventTimeField.getText();
            String location = eventLocationField.getText();
            String description = eventDescriptionArea.getText();

            if (!eventName.equals("") && !eventTime.equals("") && !location.equals("")) {
                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO hackathon_events (event_name, event_time, location, description) VALUES (?, ?, ?, ?)"; 
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, eventName);
                    pst.setString(2, eventTime);
                    pst.setString(3, location);
                    pst.setString(4, description);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Event added successfully!");
                    eventNameField.setText("");
                    eventTimeField.setText("");
                    eventLocationField.setText("");
                    eventDescriptionArea.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding event!");
                }
            }
        } else if (ae.getSource() == viewSchedule) {
            try {
                Conn c = new Conn();
                String query = "SELECT event_name, event_time, location, description FROM hackathon_events ORDER BY event_time";
                ResultSet rs = c.stmt.executeQuery(query);

                StringBuilder info = new StringBuilder();
                info.append("Hackathon Schedule:\n\n");
                while (rs.next()) {
                    info.append("Event: ").append(rs.getString("event_name"))
                        .append("\nTime: ").append(rs.getString("event_time"))
                        .append("\nLocation: ").append(rs.getString("location"))
                        .append("\nDescription: ").append(rs.getString("description"))
                        .append("\n\n");
                }
                scheduleInfoArea.setText(info.toString());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching schedule!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Schedule("");
    }
}