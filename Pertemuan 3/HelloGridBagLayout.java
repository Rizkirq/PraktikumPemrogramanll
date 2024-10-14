package com.example.Prak2;  

import java.awt.Color;  
import java.awt.FlowLayout;  
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  
import java.awt.GridLayout;  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  

/**  
 *  
 * @author ACER  
 */  
public class HelloGridBagLayout extends JFrame {  
    
    public HelloGridBagLayout() {  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);  
        
        JPanel controlPanel = new JPanel();  
        controlPanel.setLayout(new FlowLayout());  
        
        JPanel panel = new JPanel();  
        panel.setBackground(Color.DARK_GRAY);  
        panel.setSize(300, 300);  
        GridBagLayout layout = new GridBagLayout();  
        panel.setLayout(layout);  
        GridBagConstraints gbc = new GridBagConstraints();  
        
        gbc.fill = GridBagConstraints.HORIZONTAL;  

        // First row  
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        panel.add(new JButton("Button 1"), gbc);  
        
        gbc.gridx = 1;  
        panel.add(new JButton("Button 2"), gbc);  
        
        // Second row  
        gbc.gridx = 0;  
        gbc.gridy = 1;  
        gbc.ipady = 20;  // Increase button height  
        panel.add(new JButton("Button 3"), gbc);  
        
        gbc.gridx = 1;  
        panel.add(new JButton("Button 4"), gbc);  
        
        // Third row (spanning both columns)  
        gbc.gridx = 0;  
        gbc.gridy = 2;  
        gbc.gridwidth = 2; // Span two columns  
        panel.add(new JButton("Button 5"), gbc);  
        
        controlPanel.add(panel);  
        
        this.setLayout(new GridLayout(2, 1));  
        this.add(headerLabel);  
        this.add(controlPanel);  
        this.setSize(400, 400);  
    }  
    
    public static void main(String[] args) {  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                HelloGridBagLayout h = new HelloGridBagLayout();  
                h.setVisible(true);  
            }  
        });  
    }  
}