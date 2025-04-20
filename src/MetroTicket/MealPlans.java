package MetroTicket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealPlans extends JFrame implements ActionListener {

    JButton subscribe, back;
    JRadioButton vegPlan, nonVegPlan, mixedPlan;
    JCheckBox breakfast, lunch, dinner;
    JLabel labelUsername, labelPrice;
    String username;
    ButtonGroup mealTypeGroup;

    MealPlans(String username){
        this.username = username;
        setTitle("Meal Plans");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        getContentPane().setBackground(new Color(230,222,255));

        //Creating label for the username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(20, 50, 100, 30);
        lblUsername.setForeground(new Color(108,48,130));
        lblUsername.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblUsername);

        //creating label that stores the original username
        labelUsername = new JLabel(username);
        labelUsername.setBounds(150, 50, 150, 30);
        labelUsername.setForeground(new Color(108,48,130));
        labelUsername.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        add(labelUsername);

        //Creating label for meal type
        JLabel lblMealType = new JLabel("Meal Type");
        lblMealType.setBounds(20, 100, 150, 30);
        lblMealType.setForeground(new Color(108,48,130));
        lblMealType.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblMealType);

        //Creating radio buttons for meal type
        mealTypeGroup = new ButtonGroup();
        
        vegPlan = new JRadioButton("Vegetarian");
        vegPlan.setBounds(200, 100, 150, 30);
        vegPlan.setBackground(new Color(230,222,255));
        vegPlan.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        mealTypeGroup.add(vegPlan);
        add(vegPlan);
        
        nonVegPlan = new JRadioButton("Non-Vegetarian");
        nonVegPlan.setBounds(350, 100, 180, 30);
        nonVegPlan.setBackground(new Color(230,222,255));
        nonVegPlan.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        mealTypeGroup.add(nonVegPlan);
        add(nonVegPlan);
        
        mixedPlan = new JRadioButton("Mixed");
        mixedPlan.setBounds(530, 100, 100, 30);
        mixedPlan.setBackground(new Color(230,222,255));
        mixedPlan.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        mealTypeGroup.add(mixedPlan);
        add(mixedPlan);

        //Creating label for meal times
        JLabel lblMealTimes = new JLabel("Meal Times");
        lblMealTimes.setBounds(20, 150, 150, 30);
        lblMealTimes.setForeground(new Color(108,48,130));
        lblMealTimes.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblMealTimes);

        //Creating checkboxes for meal times
        breakfast = new JCheckBox("Breakfast");
        breakfast.setBounds(200, 150, 150, 30);
        breakfast.setBackground(new Color(230,222,255));
        breakfast.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        add(breakfast);
        
        lunch = new JCheckBox("Lunch");
        lunch.setBounds(350, 150, 100, 30);
        lunch.setBackground(new Color(230,222,255));
        lunch.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        add(lunch);
        
        dinner = new JCheckBox("Dinner");
        dinner.setBounds(450, 150, 100, 30);
        dinner.setBackground(new Color(230,222,255));
        dinner.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        add(dinner);

        //Creating label for price
        JLabel lblPrice = new JLabel("Monthly Price");
        lblPrice.setBounds(20, 200, 150, 30);
        lblPrice.setForeground(new Color(108,48,130));
        lblPrice.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 20));
        add(lblPrice);

        //Creating label to display price
        labelPrice = new JLabel("₹0");
        labelPrice.setBounds(200, 200, 150, 30);
        labelPrice.setForeground(new Color(108,48,130));
        labelPrice.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 20));
        add(labelPrice);

        //Creating button to subscribe
        subscribe = new JButton("Subscribe");
        subscribe.setBounds(20, 250, 150, 30);
        subscribe.setBackground(new Color(227,218,255));
        subscribe.setForeground(new Color(85, 20, 90));
        subscribe.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        subscribe.addActionListener(this);
        add(subscribe);

        //Creating button to go back
        back = new JButton("Back");
        back.setBounds(180, 250, 100, 30);
        back.setBackground(new Color(227,218,255));
        back.setForeground(new Color(85, 20, 90));
        back.setFont(new Font("CENTURY GOTHIC", Font.BOLD, 16));
        back.addActionListener(this);
        add(back);

        //Adding meal plan details
        JTextArea mealDetails = new JTextArea();
        mealDetails.setBounds(20, 300, 700, 300);
        mealDetails.setEditable(false);
        mealDetails.setBackground(new Color(240, 240, 240));
        mealDetails.setFont(new Font("CENTURY GOTHIC", Font.PLAIN, 16));
        mealDetails.setText("Meal Plan Details:\n\n" +
                           "Vegetarian Plan: Includes a variety of vegetarian dishes, fruits, and dairy products.\n\n" +
                           "Non-Vegetarian Plan: Includes meat dishes along with regular vegetarian options.\n\n" +
                           "Mixed Plan: Alternates between vegetarian and non-vegetarian meals throughout the week.\n\n" +
                           "Pricing:\n" +
                           "- Breakfast: ₹2000/month\n" +
                           "- Lunch: ₹3000/month\n" +
                           "- Dinner: ₹3000/month\n" +
                           "- Vegetarian Plan: Base price\n" +
                           "- Non-Vegetarian Plan: +₹1000/month\n" +
                           "- Mixed Plan: +₹500/month");
        add(mealDetails);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == subscribe){
            // Calculate price based on selections
            int price = 0;
            
            if(breakfast.isSelected()) {
                price += 2000;
            }
            if(lunch.isSelected()) {
                price += 3000;
            }
            if(dinner.isSelected()) {
                price += 3000;
            }
            
            if(nonVegPlan.isSelected()) {
                price += 1000;
            } else if(mixedPlan.isSelected()) {
                price += 500;
            }
            
            if(price > 0) {
                labelPrice.setText("₹" + price);
                JOptionPane.showMessageDialog(null, "Meal plan subscribed successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Please select at least one meal time and meal type!");
            }
        }
        else if(ae.getSource() == back){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new MealPlans("");
    }
}