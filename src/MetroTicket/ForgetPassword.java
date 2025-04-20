package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ForgetPassword extends JFrame implements ActionListener {

    JLabel lblUsername, lblname, lblquestion, lblanswer, lblPassword;
    JTextField tfUsername, tfname, tfquestion, tfanswer, tfPassword;
    JButton search,retrieve, back;

    ForgetPassword(){
        //Creating a frame
        setBounds(350, 200, 850, 380);
        getContentPane().setBackground(Color.WHITE);//To give colour to the panel
        setLayout(null);

        //Adding image in th eframe
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/forgetpassword1.png"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 70, 200, 200);
        add(image);

        //Create a panel
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(230,230,250 ));
        p1.setBounds(30, 30, 500, 280);
        add(p1);

        //Adding label for username
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(40, 20, 100, 25);
        lblUsername.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblUsername);
        //Adding textfeild for the username
        tfUsername = new JTextField();
        tfUsername.setBounds(220,20,150,25);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfUsername);

        //Adding button for search
        search = new JButton("Search");
        search.setBounds(380, 20, 100, 25);
        search.setForeground(new Color(230,230,250));
        search.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 14));
        search.setBackground(Color.DARK_GRAY);
        search.addActionListener(this);
        //create.setBorder(new LineBorder(new Color(170, 51 ,106)));
        p1.add(search);

        //Adding label for name
        lblname = new JLabel("Name");
        lblname.setBounds(40, 60, 100, 25);
        lblname.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblname);
        //Adding textfeild for the name
        tfname = new JTextField();
        tfname.setBounds(220,60,150,25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        //Adding label for security que
        lblquestion = new JLabel("Your Security Question");
        lblquestion.setBounds(40, 100, 180, 25);
        lblquestion.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblquestion);
        //Adding textfeild for the security que
        tfquestion = new JTextField();
        tfquestion.setBounds(220,100,150,25);
        tfquestion.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfquestion);

        //Adding label for security answer
        lblanswer = new JLabel("Answer");
        lblanswer.setBounds(40, 140, 180, 25);
        lblanswer.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblanswer);
        //Adding textfeild for the security answer
        tfanswer = new JTextField();
        tfanswer.setBounds(220,140,150,25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        //Adding button for retrieve the password
        retrieve = new JButton("Retrieve");
        retrieve.setBounds(380, 140, 100, 25);
        retrieve.setForeground(new Color(230,230,250 ));
        retrieve.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 14));
        retrieve.setBackground(Color.DARK_GRAY);
        retrieve.addActionListener(this);
        //create.setBorder(new LineBorder(new Color(170, 51 ,106)));
        p1.add(retrieve);

        //Adding label for returning the retieved password
        lblPassword = new JLabel("Password");
        lblPassword.setBounds(40, 180, 180, 25);
        lblPassword.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblPassword);
        //Adding textfeild for returning the retieved password
        tfPassword = new JTextField();
        tfPassword.setBounds(220,180,150,25);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfPassword);

        //Adding button for back
        back = new JButton("Back");
        back.setBounds(150, 230, 100, 25);
        back.setForeground(new Color(230,230,250 ));
        back.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 14));
        back.setBackground(Color.DARK_GRAY);
        back.addActionListener(this);
        //create.setBorder(new LineBorder(new Color(170, 51 ,106)));
        p1.add(back);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){

            try{
                String query = "SELECT * FROM account where username = '"+tfUsername.getText()+"'";

                Conn c = new Conn();
                ResultSet rs =  c.stmt.executeQuery(query);
                while (rs.next()){
                    tfname.setText(rs.getString("name"));
                    tfquestion.setText(rs.getString("securityQue"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (ae.getSource()== retrieve) {
            try{
                String query = "SELECT * FROM account where answer = '"+tfanswer.getText()+"' AND username = '"+tfUsername.getText()+"'";

                Conn c = new Conn();
                ResultSet rs =  c.stmt.executeQuery(query);
                while (rs.next()){
                    tfPassword.setText(rs.getString("password"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (ae.getSource() == back) {

            setVisible(false);
            new Login();

        }
    }


    public static void main(String[] arg){
        new ForgetPassword();
    }
}
