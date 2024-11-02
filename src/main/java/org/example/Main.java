package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                Calculatrice calculatrice = new Calculatrice();// We create a new calculatrice
            }
        });
    }
}