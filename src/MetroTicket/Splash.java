package MetroTicket;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable{
    Thread thread;
    Splash(){
        //Creating a frame
        //setSize(1200, 600);
        //setLocation(200, 100);
        //To add image to our prject
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash.jpg"));
        //To adjust the size of the image
        Image i2 = i1.getImage().getScaledInstance(1200, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        //Add the image on the frame
        JLabel image = new JLabel(i3);
        add(image);
        setVisible(true);
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try{
            Thread.sleep(7000);
            new Login();
            setVisible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] arg){
        Splash frame = new Splash();
        //To open the image gradually
        int x = 7;
        for (int i=1 ; i<=500 ; x+=7 , i+=6){
            frame.setSize(x+i, i);
            frame.setLocation(750-(x+i)/2, 400-(i/2));
            try{
                //To take some time and start
                Thread.sleep(50);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
