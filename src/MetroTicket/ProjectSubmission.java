package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjectSubmission extends JFrame implements ActionListener {

    JTextField teamNameField, projectTitleField;
    JTextArea projectDescriptionArea, submissionInfoArea;
    JButton submit, viewSubmissions, back;
    String username;

    ProjectSubmission(String username) {
        this.username = username;

        setTitle("Project Submission");
        setBounds(350, 200, 1000, 600);
        setLayout(null);
        getContentPane().setBackground(new Color(230, 222, 255));

        JLabel title = new JLabel("Project Submission Portal");
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

        JLabel projectTitleLabel = new JLabel("Project Title:");
        projectTitleLabel.setBounds(50, 140, 100, 25);
        projectTitleLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(projectTitleLabel);

        projectTitleField = new JTextField();
        projectTitleField.setBounds(160, 140, 200, 25);
        add(projectTitleField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(50, 180, 100, 25);
        descriptionLabel.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 14));
        add(descriptionLabel);

        projectDescriptionArea = new JTextArea();
        projectDescriptionArea.setLineWrap(true);
        JScrollPane descScrollPane = new JScrollPane(projectDescriptionArea);
        descScrollPane.setBounds(160, 180, 200, 100);
        add(descScrollPane);

        submit = new JButton("Submit Project");
        submit.setBounds(50, 300, 310, 30);
        submit.setBackground(new Color(85, 20, 90));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        viewSubmissions = new JButton("View Submissions");
        viewSubmissions.setBounds(50, 340, 310, 30);
        viewSubmissions.setBackground(new Color(85, 20, 90));
        viewSubmissions.setForeground(Color.WHITE);
        viewSubmissions.addActionListener(this);
        add(viewSubmissions);

        submissionInfoArea = new JTextArea();
        submissionInfoArea.setEditable(false);
        JScrollPane infoScrollPane = new JScrollPane(submissionInfoArea);
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
        if (ae.getSource() == submit) {
            String teamName = teamNameField.getText();
            String projectTitle = projectTitleField.getText();
            String description = projectDescriptionArea.getText();

            if (!teamName.equals("") && !projectTitle.equals("") && !description.equals("")) {
                try {
                    Conn c = new Conn();
                    String query = "INSERT INTO project_submissions (team_name, project_title, description, submission_time) VALUES (?, ?, ?, NOW())";
                    PreparedStatement pst = c.con.prepareStatement(query);
                    pst.setString(1, teamName);
                    pst.setString(2, projectTitle);
                    pst.setString(3, description);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Project submitted successfully!");
                    teamNameField.setText("");
                    projectTitleField.setText("");
                    projectDescriptionArea.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error submitting project!");
                }
            }
        } else if (ae.getSource() == viewSubmissions) {
            try {
                Conn c = new Conn();
                String query = "SELECT team_name, project_title, description, submission_time FROM project_submissions ORDER BY submission_time DESC";
                ResultSet rs = c.stmt.executeQuery(query);

                StringBuilder info = new StringBuilder();
                info.append("Project Submissions:\n\n");
                while (rs.next()) {
                    info.append("Team: ").append(rs.getString("team_name"))
                        .append("\nProject: ").append(rs.getString("project_title"))
                        .append("\nDescription: ").append(rs.getString("description"))
                        .append("\nSubmitted: ").append(rs.getString("submission_time"))
                        .append("\n\n");
                }
                submissionInfoArea.setText(info.toString());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching submissions!");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new ProjectSubmission("");
    }
}