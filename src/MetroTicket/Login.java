package MetroTicket;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField tfUsername, tfPassword ;
    JButton login, signup, forgetPassword;

    Login(){
        setSize(900, 400);
        setLocation(350, 200);
        //To create the custom layout
        setLayout(null);

        //to get the costumized background colour
        getContentPane().setBackground(Color.WHITE);

        //Panel part 1 for image
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(230,230,250 ));//pastel pink colour
        //p1.setBackground(new Color(131 , 193 , 233));//blue colour
        p1.setBounds(0,0, 400, 400);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login1.png"));
        Image i2 = i1.getImage().getScaledInstance(200,200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(80, 90, 200, 200);
        p1.add(image);

        //Panel part 2 for information
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBounds(400, 30, 450, 300);
        add(p2);

        //label for the username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(60, 20, 100, 25);
        lblUsername.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        p2.add(lblUsername);

        //textbox for the username
        tfUsername = new JTextField();
        tfUsername.setBounds(60,60,300,30);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfUsername);


        //label for the Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(60, 110, 100, 25);
        lblPassword.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        p2.add(lblPassword);
        //textbox for the password
        tfPassword = new JTextField();
        tfPassword.setBounds(60,150,300,30);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfPassword);


        //Button for login
        login = new JButton("Login");
        login.setBounds(60, 200, 130, 30);
        login.setBackground(new Color(230,230,250 ));
        login.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 20));
        login.setForeground(Color.DARK_GRAY);
        login.setBorder(new LineBorder(new Color(108,48,130)));
        login.addActionListener(this);
        p2.add(login);

        //Button for signup
        signup = new JButton("Sign Up");
        signup.setBounds(230, 200, 130, 30);
        signup.setBackground(new Color(230,230,250 ));
        signup.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 20));
        signup.setForeground(Color.DARK_GRAY);
        signup.setBorder(new LineBorder(new Color(108,48,130)));
        signup.addActionListener(this);
        p2.add(signup);

        //Button for forget password
        forgetPassword = new JButton("Forget Password");
        forgetPassword.setBounds(110, 250, 180, 30);
        forgetPassword.setBackground(new Color(230,230,250));
        forgetPassword.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 20));
        forgetPassword.setForeground(Color.DARK_GRAY);
        forgetPassword.setBorder(new LineBorder(new Color(108,48,130)));
        forgetPassword.addActionListener(this);
        p2.add(forgetPassword);

        //label in front of the forget password button
        JLabel text = new JLabel("Trouble in login...");
        text.setBounds(300, 250, 150, 20);
        text.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 15));
        text.setForeground(new Color(103,49,71));
        p2.add(text);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){

            try{
                String username = tfUsername.getText();
                String password = tfPassword.getText();

                String query = "SELECT * FROM account where username = '"+username+"' AND password = '"+password+"'";

                Conn c= new Conn();
                ResultSet rs =  c.stmt.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Loading(username);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }
        else if (ae.getSource() == signup) {

            setVisible(false);
            new Signup();

        }
        else if (ae.getSource() == forgetPassword) {

            setVisible(false);
            new ForgetPassword();

        }
    }

    public static void main(String[] arg){

        Login l = new Login();
    }
}
