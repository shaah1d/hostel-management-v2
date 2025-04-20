package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HackathonDashboard extends JFrame implements ActionListener {

    JButton teamManagement, attendance, projectSubmission, schedule, leaderboard;
    String username;

    HackathonDashboard(String username) {
        this.username = username;

        setTitle("Hackathon Management System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 250));

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(51, 51, 153));
        p1.setBounds(0, 0, 1600, 60);
        add(p1);

      
        JLabel heading = new JLabel("Hackathon Management Dashboard");
        heading.setBounds(80, 10, 400, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 30));
        p1.add(heading);

        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(240, 240, 245));
        p2.setBounds(0, 65, 1600, 50);
        add(p2);

        teamManagement = new JButton("Team Management");
        teamManagement.setBounds(10, 10, 200, 30);
        teamManagement.setBackground(new Color(51, 51, 153));
        teamManagement.setForeground(Color.WHITE);
        teamManagement.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        teamManagement.addActionListener(this);
        p2.add(teamManagement);

        attendance = new JButton("Attendance");
        attendance.setBounds(220, 10, 200, 30);
        attendance.setBackground(new Color(51, 51, 153));
        attendance.setForeground(Color.WHITE);
        attendance.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        attendance.addActionListener(this);
        p2.add(attendance);

        projectSubmission = new JButton("Project Submission");
        projectSubmission.setBounds(440, 10, 200, 30);
        projectSubmission.setBackground(new Color(51, 51, 153));
        projectSubmission.setForeground(Color.WHITE);
        projectSubmission.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        projectSubmission.addActionListener(this);
        p2.add(projectSubmission);

        schedule = new JButton("Schedule");
        schedule.setBounds(660, 10, 200, 30);
        schedule.setBackground(new Color(51, 51, 153));
        schedule.setForeground(Color.WHITE);
        schedule.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        schedule.addActionListener(this);
        p2.add(schedule);

        leaderboard = new JButton("Leaderboard");
        leaderboard.setBounds(880, 10, 200, 30);
        leaderboard.setBackground(new Color(51, 51, 153));
        leaderboard.setForeground(Color.WHITE);
        leaderboard.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        leaderboard.addActionListener(this);
        p2.add(leaderboard);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == teamManagement) {
            new TeamManagement(username);
        } else if (ae.getSource() == attendance) {
            new AttendanceSystem(username);
        } else if (ae.getSource() == projectSubmission) {
            new ProjectSubmission(username);
        } else if (ae.getSource() == schedule) {
            new Schedule(username);
        } else if (ae.getSource() == leaderboard) {
            new Leaderboard(username);
        }
    }

    public static void main(String[] args) {
        new HackathonDashboard("");
    }
}