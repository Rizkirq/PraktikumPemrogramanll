package com.example.Prak2;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;  
import java.awt.Insets;
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JRadioButton;  
import javax.swing.JTextField;  
import javax.swing.ButtonGroup;  
import javax.swing.JCheckBox;  

public class HelloGridLayout2 extends JFrame {  

    public HelloGridLayout2() {  
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header label
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);

        // Create JPanel with GridBagLayout
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Labels and inputs
        JLabel nameLabel = new JLabel("Nama:");
        JTextField nameField = new JTextField(15);

        JLabel phoneLabel = new JLabel("Nomor HP:");
        JTextField phoneField = new JTextField(15);

        JLabel genderLabel = new JLabel("Jenis Kelamin:");

        JRadioButton maleButton = new JRadioButton("Laki-Laki");
        JRadioButton femaleButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JCheckBox foreignCheckbox = new JCheckBox("Warga Negara Asing");

        JButton saveButton = new JButton("Simpan");

        // Layout settings
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the panel
        // First row - Nama label
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);

        // Nama text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(nameField, gbc);

        // Jenis Kelamin label
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(genderLabel, gbc);

        // Laki-Laki radio button
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(maleButton, gbc);

        // Perempuan radio button
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(femaleButton, gbc);

        // Warga Negara Asing checkbox
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(foreignCheckbox, gbc);

        // Nomor HP label
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);

        // Nomor HP text field
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(phoneField, gbc);

        // Simpan button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(saveButton, gbc);

        // Set up frame layout
        this.setLayout(new BorderLayout());
        this.add(headerLabel, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);

        this.setSize(400, 300); // Set frame size
    }

    public static void main(String[] args) {  
        javax.swing.SwingUtilities.invokeLater(new Runnable() {  
            @Override
            public void run() {  
                HelloGridLayout2 h = new HelloGridLayout2();  
                h.setVisible(true);  
            }  
        });  
    }  
}