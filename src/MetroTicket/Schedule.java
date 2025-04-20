package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule extends JFrame implements ActionListener {

    JTextField eventNameField, eventDateField, eventTimeField, eventLocationField;
    JTextArea eventDescriptionArea, scheduleInfoArea;
    JButton addEvent, viewSchedule, back;
    String username;

    Schedule(String username) {
        this.username = username;

        setTitle("Hackathon Schedule");
        setBounds(350, 200, 1000, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Event Schedule Management");
        title.setBounds(400, 30, 300, 35);
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

        JLabel eventDateLabel = new JLabel("Date (YYYY-MM-DD):");
        eventDateLabel.setBounds(50, 140, 120, 25);
        eventDateLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventDateLabel);

        eventDateField = new JTextField();
        eventDateField.setBounds(180, 140, 180, 25);
        add(eventDateField);

        JLabel eventTimeLabel = new JLabel("Time (HH:MM):");
        eventTimeLabel.setBounds(50, 180, 100, 25);
        eventTimeLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventTimeLabel);

        eventTimeField = new JTextField();
        eventTimeField.setBounds(160, 180, 200, 25);
        add(eventTimeField);

        JLabel eventLocationLabel = new JLabel("Location:");
        eventLocationLabel.setBounds(50, 220, 100, 25);
        eventLocationLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(eventLocationLabel);

        eventLocationField = new JTextField();
        eventLocationField.setBounds(160, 220, 200, 25);
        add(eventLocationField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 260, 100, 25);
        descriptionLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(descriptionLabel);

        eventDescriptionArea = new JTextArea();
        eventDescriptionArea.setLineWrap(true);
        JScrollPane descScrollPane = new JScrollPane(eventDescriptionArea);
        descScrollPane.setBounds(160, 260, 200, 100);
        add(descScrollPane);

        addEvent = new JButton("Add Event");
        addEvent.setBounds(50, 380, 310, 30);
        addEvent.setBackground(new Color(85, 20, 90));
        addEvent.setForeground(Color.WHITE);
        addEvent.addActionListener(this);
        add(addEvent);

        viewSchedule = new JButton("View Schedule");
        viewSchedule.setBounds(50, 420, 310, 30);
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
            String eventDate = eventDateField.getText();
            String eventTime = eventTimeField.getText();
            String location = eventLocationField.getText();
            String description = eventDescriptionArea.getText();

            if (!eventName.equals("") && !eventDate.equals("") && !eventTime.equals("") && !location.equals("")) {
                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO schedule_events (event_name, event_date, event_time, location, description) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, eventName);
                    pst.setString(2, eventDate);
                    pst.setString(3, eventTime);
                    pst.setString(4, location);
                    pst.setString(5, description);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Event added successfully!");
                    eventNameField.setText("");
                    eventDateField.setText("");
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
                String query = "SELECT * FROM schedule_events ORDER BY event_date, event_time";
                ResultSet rs = c.stmt.executeQuery(query);

                StringBuilder info = new StringBuilder();
                info.append("Upcoming Events:\n\n");
                while (rs.next()) {
                    info.append("Event: ").append(rs.getString("event_name"))
                        .append("\nDate: ").append(rs.getString("event_date"))
                        .append("\nTime: ").append(rs.getString("event_time"))
                        .append("\nLocation: ").append(rs.getString("location"));
                    
                    String desc = rs.getString("description");
                    if (desc != null && !desc.trim().isEmpty()) {
                        info.append("\nDescription: ").append(desc);
                    }
                    info.append("\n\n");
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