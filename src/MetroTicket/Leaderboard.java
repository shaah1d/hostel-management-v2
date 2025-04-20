package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Leaderboard extends JFrame implements ActionListener {

    JTextField teamNameField, scoreField;
    JTextArea commentsArea, leaderboardInfoArea;
    JButton updateScore, viewLeaderboard, back;
    String username;

    Leaderboard(String username) {
        this.username = username;

        setTitle("Hackathon Leaderboard");
        setBounds(350, 200, 1000, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Hackathon Leaderboard");
        title.setBounds(400, 30, 300, 35);
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

        JLabel scoreLabel = new JLabel("Score (0-100):");
        scoreLabel.setBounds(50, 140, 100, 25);
        scoreLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(scoreLabel);

        scoreField = new JTextField();
        scoreField.setBounds(160, 140, 200, 25);
        add(scoreField);

        JLabel commentsLabel = new JLabel("Comments:");
        commentsLabel.setBounds(50, 180, 100, 25);
        commentsLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(commentsLabel);

        commentsArea = new JTextArea();
        commentsArea.setLineWrap(true);
        JScrollPane commentsScrollPane = new JScrollPane(commentsArea);
        commentsScrollPane.setBounds(160, 180, 200, 100);
        add(commentsScrollPane);

        updateScore = new JButton("Update Score");
        updateScore.setBounds(50, 300, 310, 30);
        updateScore.setBackground(new Color(85, 20, 90));
        updateScore.setForeground(Color.WHITE);
        updateScore.addActionListener(this);
        add(updateScore);

        viewLeaderboard = new JButton("View Leaderboard");
        viewLeaderboard.setBounds(50, 340, 310, 30);
        viewLeaderboard.setBackground(new Color(85, 20, 90));
        viewLeaderboard.setForeground(Color.WHITE);
        viewLeaderboard.addActionListener(this);
        add(viewLeaderboard);

        leaderboardInfoArea = new JTextArea();
        leaderboardInfoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(leaderboardInfoArea);
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
        if (ae.getSource() == updateScore) {
            String teamName = teamNameField.getText();
            String scoreText = scoreField.getText();
            String comments = commentsArea.getText();

            if (!teamName.equals("") && !scoreText.equals("")) {
                try {
                    int score = Integer.parseInt(scoreText);
                    if (score >= 0 && score <= 100) {
                        Conn c = new Conn();
                        String query = "INSERT INTO team_scores (team_name, score, comments, evaluation_time) VALUES (?, ?, ?, NOW()) " +
                                     "ON DUPLICATE KEY UPDATE score = ?, comments = ?, evaluation_time = NOW()";
                        PreparedStatement pst = c.con.prepareStatement(query);
                        pst.setString(1, teamName);
                        pst.setInt(2, score);
                        pst.setString(3, comments);
                        pst.setInt(4, score);
                        pst.setString(5, comments);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Score updated successfully!");
                        teamNameField.setText("");
                        scoreField.setText("");
                        commentsArea.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Score must be between 0 and 100!");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid score!");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating score!");
                }
            }
        } else if (ae.getSource() == viewLeaderboard) {
            try {
                Conn c = new Conn();
                String query = "SELECT team_name, score, comments, evaluation_time FROM team_scores ORDER BY score DESC";
                ResultSet rs = c.stmt.executeQuery(query);

                StringBuilder info = new StringBuilder();
                info.append("Current Rankings:\n\n");
                int rank = 1;
                while (rs.next()) {
                    info.append(rank++).append(". Team: ").append(rs.getString("team_name"))
                        .append("\nScore: ").append(rs.getString("score"));
                    
                    String comments = rs.getString("comments");
                    if (comments != null && !comments.trim().isEmpty()) {
                        info.append("\nFeedback: ").append(comments);
                    }
                    info.append("\nLast Updated: ").append(rs.getString("evaluation_time"))
                        .append("\n\n");
                }
                leaderboardInfoArea.setText(info.toString());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching leaderboard!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Leaderboard("");
    }
}