package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TeamManagement extends JFrame implements ActionListener {

    JTextField teamNameField, memberNameField, memberEmailField;
    JButton createTeam, addMember, viewTeams, back;
    String username;
    JTextArea teamInfoArea;

    TeamManagement(String username) {
        this.username = username;

        setTitle("Team Management");
        setBounds(350, 200, 1000, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Team Management");
        title.setBounds(400, 30, 200, 35);
        title.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 25));
        title.setForeground(new Color(85, 20, 90));
        add(title);

        JLabel teamNameLabel = new JLabel("Team Name:");
        teamNameLabel.setBounds(50, 100, 100, 25);
        teamNameLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(teamNameLabel);

        teamNameField = new JTextField();
        teamNameField.setBounds(160, 100, 200, 25);
        add(teamNameField);

        JLabel memberNameLabel = new JLabel("Member Name:");
        memberNameLabel.setBounds(50, 140, 100, 25);
        memberNameLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(memberNameLabel);

        memberNameField = new JTextField();
        memberNameField.setBounds(160, 140, 200, 25);
        add(memberNameField);

        JLabel memberEmailLabel = new JLabel("Member Email:");
        memberEmailLabel.setBounds(50, 180, 100, 25);
        memberEmailLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(memberEmailLabel);

        memberEmailField = new JTextField();
        memberEmailField.setBounds(160, 180, 200, 25);
        add(memberEmailField);

        createTeam = new JButton("Create Team");
        createTeam.setBounds(50, 230, 150, 30);
        createTeam.setBackground(new Color(85, 20, 90));
        createTeam.setForeground(Color.WHITE);
        createTeam.addActionListener(this);
        add(createTeam);

        addMember = new JButton("Add Member");
        addMember.setBounds(210, 230, 150, 30);
        addMember.setBackground(new Color(85, 20, 90));
        addMember.setForeground(Color.WHITE);
        addMember.addActionListener(this);
        add(addMember);

        viewTeams = new JButton("View Teams");
        viewTeams.setBounds(50, 270, 310, 30);
        viewTeams.setBackground(new Color(85, 20, 90));
        viewTeams.setForeground(Color.WHITE);
        viewTeams.addActionListener(this);
        add(viewTeams);

        teamInfoArea = new JTextArea();
        teamInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(teamInfoArea);
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
        if (ae.getSource() == createTeam) {
            String teamName = teamNameField.getText();
            if (!teamName.equals("")) {
                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO teams (team_name, leader_username) VALUES (?, ?)";
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, teamName);
                    pst.setString(2, username);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Team created successfully!");
                    teamNameField.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error creating team!");
                }
            }
        } else if (ae.getSource() == addMember) {
            String teamName = teamNameField.getText();
            String memberName = memberNameField.getText();
            String memberEmail = memberEmailField.getText();
            if (!teamName.equals("") && !memberName.equals("") && !memberEmail.equals("")) {
                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO team_members (team_name, member_name, member_email) VALUES (?, ?, ?)";
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, teamName);
                    pst.setString(2, memberName);
                    pst.setString(3, memberEmail);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Member added successfully!");
                    memberNameField.setText("");
                    memberEmailField.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding member!");
                }
            }
        } else if (ae.getSource() == viewTeams) {
            try {
                Conn c = new Conn();
                String query = "SELECT t.team_name, t.leader_username, m.member_name, m.member_email FROM teams t LEFT JOIN team_members m ON t.team_name = m.team_name";
                ResultSet rs = c.stmt.executeQuery(query);
                
                StringBuilder info = new StringBuilder();
                String currentTeam = "";
                while (rs.next()) {
                    String teamName = rs.getString("team_name");
                    if (!teamName.equals(currentTeam)) {
                        info.append("\nTeam: ").append(teamName)
                            .append("\nLeader: ").append(rs.getString("leader_username"))
                            .append("\nMembers:\n");
                        currentTeam = teamName;
                    }
                    String memberName = rs.getString("member_name");
                    String memberEmail = rs.getString("member_email");
                    if (memberName != null) {
                        info.append("- ").append(memberName)
                            .append(" (").append(memberEmail).append(")\n");
                    }
                }
                teamInfoArea.setText(info.toString());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching teams!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new TeamManagement("");
    }
}