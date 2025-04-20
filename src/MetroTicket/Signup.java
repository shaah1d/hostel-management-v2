package MetroTicket;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame implements ActionListener {

    //Buttons for the operations like create acc and go back to login page
    JButton create , back;
    //Textfeilds for the input from user for various  actions
    JTextField tfname, tfUsername, tfPassword, tfanswer;
    //Choice dropdown menu for the security question
    Choice security;

    Signup(){
        setSize(900, 360);
        setLocation(350, 200);
        setLayout(null);

        //to get the costumized background colour
        getContentPane().setBackground(Color.WHITE);
        //panel part 1
        JPanel p1  = new JPanel();
        p1.setBackground(new Color(230,230,250 ));
        p1.setBounds(0, 0 , 500, 400);
        p1.setLayout(null);
        add(p1);

        //label for username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 20, 125, 25);
        lblUsername.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblUsername);
        //textbox for the username
        tfUsername = new JTextField();
        tfUsername.setBounds(190,20,200,25);
        tfUsername.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfUsername);

        //label for name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 60, 125, 25);
        lblname.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblname);
        //textbox for the username
        tfname = new JTextField();
        tfname.setBounds(190,60,200,25);
        tfname.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfname);

        //label for password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 100, 125, 25);
        lblPassword.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblPassword);
        //textbox for the password
        tfPassword = new JTextField();
        tfPassword.setBounds(190,100,200,25);
        tfPassword.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfPassword);

        //label for security question
        JLabel lblsecurity = new JLabel("Security Question");
        lblsecurity.setBounds(50, 140, 125, 25);
        lblsecurity.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblsecurity);

        //Choices dropdown for the security questions using choice
        security = new Choice();
        security.add("Fav bollywood actor");
        security.add("Fav childhood superhero");
        security.add("Your lucky number");
        security.add("Your pet's name");
        security.setBounds(190, 140 , 180 , 25);
        p1.add(security);

        //label for security que answer
        JLabel lblanswer = new JLabel("Answer");
        lblanswer.setBounds(50, 180, 125, 25);
        lblanswer.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 14));
        p1.add(lblanswer);
        //textbox for the security que answer
        tfanswer = new JTextField();
        tfanswer.setBounds(190,180,200,25);
        tfanswer.setBorder(BorderFactory.createEmptyBorder());
        p1.add(tfanswer);

        //Button for create account
        create = new JButton("Create");
        create.setBounds(80, 250, 100, 30);
        create.setForeground(new Color(230,230,250 ));
        create.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 14));
        create.setBackground(Color.DARK_GRAY);
        create.addActionListener(this);
        //create.setBorder(new LineBorder(new Color(170, 51 ,106)));
        p1.add(create);

        //Button for back
        back = new JButton("Back");
        back.setBounds(250, 250, 100, 30);
        back.setForeground(new Color(230,230,250));
        back.setFont(new Font("CENTURY GOTHIC" , Font.PLAIN, 14));
        back.setBackground(Color.DARK_GRAY);
        create.setBorder(new LineBorder(new Color(108,48,130)));
        back.addActionListener(this);
        p1.add(back);


        //Add image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/signup1.png"));
        Image i2 = i1.getImage().getScaledInstance(250,250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(580, 50, 250, 250);
        add(image);


        setVisible(true);
    }

    //this gives the button through which we can trigger
    public void actionPerformed(ActionEvent ae){
        //for the create button
        if(ae.getSource() == create){
            String username = tfUsername.getText();//Function to return the string from the textfeild
            String name = tfname.getText();//Function to return the string from the textfeild
            String password = tfPassword.getText();//Function to return the string from the textfeild
            String question = security.getSelectedItem();//Function to get the selected question from the dropdown menu
            String answer = tfanswer.getText();//Function to return the string from th textfeild

            //Validation to check if all the feilds are filled
            if (username.isEmpty() || name.isEmpty() || password.isEmpty() || answer.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out!");
                return;
            }


            //Query to be executed , here to insert the values in the database
            String query = "INSERT INTO account VALUES('"+username+"', '"+name+"', '"+password+"', '"+question+"', '"+answer+"')";

            try{
                //Creating the connection with database
                Conn c = new Conn();
                c.stmt.executeUpdate(query);

                //This will open a dialog box once we click on create account
                JOptionPane.showMessageDialog(null, "Account Created Succesfully");

                //When we click ok in the dialog box the current window should be replaced byu the login window
                setVisible(false);
                new Login();

                //Closing the connection
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if (ae.getSource() == back) {
            setVisible(false);
            new Login();//Creating an object of login class so thet when we press back button we can go to login page
        }
    }


    public static void main(String[] arg){
        Signup s1 = new Signup();
    }
}
