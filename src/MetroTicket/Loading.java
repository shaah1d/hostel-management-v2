package MetroTicket;

import javax.swing.*;
import java.awt.*;

public class Loading extends JFrame implements Runnable{

    Thread t;
    JProgressBar bar;
    String username;

    public void run(){
        try{
            for(int i= 1 ; i<=101 ; i++){
                int max = bar.getMaximum();//In this case max value is 100
                int value = bar.getValue();//This gives the current value

                if(value < max){
                    bar.setValue(bar.getValue() + 1);
                }
                else{
                    setVisible(false);
                    new Dashboard("");
                }
                Thread.sleep(50);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    Loading(String username){
        this.username = username;

        t =  new Thread(this);

        //Creating a frame
        setBounds(500,200,650,400);
        getContentPane().setBackground(new Color( 230,230,250));
        setLayout(null);

        //Creating a label for heading
        JLabel text = new JLabel("PUNE METRO TICKETING SYSTEM");
        text.setBounds(50,20, 600, 40);
        text.setForeground(new Color(108,48,130));
        text.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 35));
        add(text);

        //Creating a progress bar to show the loading progress
        bar = new JProgressBar();
        bar.setBounds(150, 100, 300, 35);
        bar.setStringPainted(true);
        add(bar);

        //Creating a label for loading
        JLabel loading = new JLabel("Loading, Please wait...");
        loading.setBounds(200,140, 600, 30);
        loading.setForeground(new Color(108,48,130));
        loading.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(loading);

        //Creating a label for username
        JLabel lblusername = new JLabel("Welcome "+ username);
        lblusername.setBounds(100,310, 400, 40);
        lblusername.setForeground(new Color(103,49,71));
        lblusername.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 35));
        add(lblusername);

        t.start();

        setVisible(true);
    }


    public static void main(String[] arg){

        new Loading(" ");
    }
}
