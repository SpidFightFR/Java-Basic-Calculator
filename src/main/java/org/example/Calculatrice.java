package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculatrice {

    JFrame frame;
    JPanel buttonPanel;
    JButton[] buttons = new JButton[10];
    String firstnum,secondnum,operator;
    boolean pendingSecondnum = false;

    static String[] content = new String[]{"9","8","7","+","6","5","4","-","3","2","1","*","AC","0","Enter","/"}; //declaring all the keys we need (in the order we need them, reading order)

    public Calculatrice() {
        firstnum = "0";
        secondnum = "";
        operator = "";


        frame = new JFrame("Calculatrice");
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //put the window in the center of the main screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Allow for closure

        //creating screen
        JTextField ecran = new JTextField();
        ecran.setEditable(false); //Don't edit the screen lmao
        ecran.setHorizontalAlignment(JTextField.RIGHT); //align the content on the right
        ecran.setFont(new Font("Sans Serif", Font.PLAIN, 20));
        ecran.setText("0"); //Default value

        //Adding screen to window
        frame.add(ecran, BorderLayout.NORTH);

        //Creating buttons
        int i = 0;

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4));
        for (String element : content) {
            buttons[i] = new JButton(element);
            buttonPanel.add(buttons[i]);

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton source = (JButton) e.getSource();
                    String commande = e.getActionCommand();
                    System.out.println(source.getText() + " clicked!");


                    if(firstnum.isEmpty()) {
                        if (commande.equals("+") || commande.equals("-") || commande.equals("*") || commande.equals("/")) {
                            //Bro just clicked an operator without a number
                            firstnum = "0";
                            operator = commande;
                            ecran.setText(firstnum + operator);
                        }
                    }
                    else if(pendingSecondnum) {
                        secondnum = commande;
                        ecran.setText(firstnum + operator + secondnum);
                        pendingSecondnum = false;
                    }

                    else if (firstnum.equals("0")) {
                        //Bro does things the right way
                        firstnum = commande;
                        System.out.println("log1: "+ firstnum);
                        ecran.setText(firstnum);
                    }

                    else if (commande.equals("+") || commande.equals("-") || commande.equals("*") || commande.equals("/")) {
                        //Bro just clicked an operator without a number
                        operator = commande;
                        ecran.setText(firstnum + operator);
                        pendingSecondnum = true;

                    }


                    if (commande.equals("Enter")){
                        System.out.println("Log: Pressed enter.");
                        float result = doCalculation(Float.parseFloat(firstnum),Float.parseFloat(secondnum),operator);
                        System.out.println("Log: " + result);
                        ecran.setText(String.valueOf(result));
                        firstnum = String.valueOf(result); //We use the most recent result as first input
                    }
                    if (commande.equals("AC")){
                        firstnum = "0";
                        secondnum = "";
                        operator = "";

                        ecran.setText("0");
                        pendingSecondnum = false;
                    }

                    System.out.println("Log: " + firstnum + " " + operator + " " + secondnum);
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public float doCalculation(float a, float b,String op){
        float result = 0;
        switch (op){
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0){
                    result = a / b;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
        }
        return result;
    }
}

