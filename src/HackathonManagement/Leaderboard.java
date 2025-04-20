package HackathonManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import MetroTicket.Conn;

public class Leaderboard extends JFrame implements ActionListener {

    JButton back;
    String username;
    JTextArea leaderboardArea;

    public Leaderboard(String username) {
        this.username = username;

        setTitle("Hackathon Leaderboard");
        setBounds(350, 200, 1000, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Hackathon Leaderboard");
        title.setBounds(400, 30, 300, 35);
        title.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 25));
        title.setForeground(new Color(85, 20, 90));
        add(title);

        leaderboardArea = new JTextArea();
        leaderboardArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(leaderboardArea);
        scrollPane.setBounds(50, 100, 900, 300);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(50, 420, 100, 30);
        back.setBackground(new Color(85, 20, 90));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        updateLeaderboard();
        setVisible(true);
    }

    private void updateLeaderboard() {
        try {
            Conn c = new Conn();
            String query = "SELECT team_name, project_title, score FROM hackathon_scores ORDER BY score DESC";
            ResultSet rs = c.stmt.executeQuery(query);

            StringBuilder leaderboard = new StringBuilder();
            leaderboard.append("Rank\tTeam\t\tProject\t\tScore\n");
            leaderboard.append("================================================\n\n");

            int rank = 1;
            while (rs.next()) {
                leaderboard.append(rank++).append("\t")
                    .append(rs.getString("team_name")).append("\t\t")
                    .append(rs.getString("project_title")).append("\t\t")
                    .append(rs.getInt("score")).append("\n");
            }

            leaderboardArea.setText(leaderboard.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching leaderboard data!");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Leaderboard("");
    }
}