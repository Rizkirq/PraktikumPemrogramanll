/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Prak2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author ACER
 */
public class ButtonExample {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Example");
        JButton button = new JButton("Click Me");
        
        button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked!");
     
        }
    });
        
        button.setBounds(50, 50, 150, 30);
        frame.add(button);
        frame.setSize(300,200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
