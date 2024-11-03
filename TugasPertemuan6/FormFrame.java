/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Prak2.Tugas3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormFrame extends JFrame {

    public FormFrame() {
        setTitle("Form Input");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nama:"), gbc);
        
        gbc.gridx = 1;
        JTextField textFieldNama = new JTextField(15); 
        panel.add(textFieldNama, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Alamat:"), gbc);
        
        gbc.gridx = 1;
        JTextArea textAreaAlamat = new JTextArea(3, 15);
        panel.add(new JScrollPane(textAreaAlamat), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Jenis Kelamin:"), gbc);
        
        gbc.gridx = 1;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton radioLaki = new JRadioButton("Laki-laki");
        JRadioButton radioPerempuan = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioLaki);
        genderGroup.add(radioPerempuan);
        genderPanel.add(radioLaki);
        genderPanel.add(radioPerempuan);
        panel.add(genderPanel, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Minat:"), gbc);
        
        gbc.gridx = 1;
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JCheckBox checkBox1 = new JCheckBox("Olahraga");
        JCheckBox checkBox2 = new JCheckBox("Musik");
        JCheckBox checkBox3 = new JCheckBox("Seni");
        interestPanel.add(checkBox1);
        interestPanel.add(checkBox2);
        interestPanel.add(checkBox3);
        panel.add(interestPanel, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Kota:"), gbc);
        
        gbc.gridx = 1;
        JComboBox<String> comboBoxKota = new JComboBox<>(new String[]{"Jakarta", "Bandung", "Surabaya"});
        panel.add(comboBoxKota, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Hobi:"), gbc);
        
        gbc.gridx = 1;
        JList<String> listHobi = new JList<>(new String[]{"Membaca", "Menulis", "Memasak"});
        listHobi.setVisibleRowCount(3);
        panel.add(new JScrollPane(listHobi), gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(new JLabel("Umur:"), gbc);
        
        gbc.gridx = 1;
        JSpinner spinnerUmur = new JSpinner(new SpinnerNumberModel(20, 10, 100, 1));
        panel.add(spinnerUmur, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(new JLabel("Skor Kepuasan:"), gbc);
        
        gbc.gridx = 1;
        JSlider sliderSkor = new JSlider(0, 100, 50);
        sliderSkor.setPaintTicks(true);
        sliderSkor.setPaintLabels(true);
        sliderSkor.setMajorTickSpacing(10);
        panel.add(sliderSkor, gbc);

        
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton buttonSubmit = new JButton("Submit");
        panel.add(buttonSubmit, gbc);

        
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Nama", "Alamat", "Jenis Kelamin",
        "Minat", "Kota", "Hobi", "Umur", "Skor"}, 0);
        JTable table = new JTable(tableModel);

        
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String alamat = textAreaAlamat.getText();
                String jenisKelamin = radioLaki.isSelected() ? "Laki-laki" : "Perempuan";
                String minat = "";
                if (checkBox1.isSelected()) minat += "Olahraga ";
                if (checkBox2.isSelected()) minat += "Musik ";
                if (checkBox3.isSelected()) minat += "Seni ";
                String kota = (String) comboBoxKota.getSelectedItem();
                String hobi = listHobi.getSelectedValue();
                int umur = (int) spinnerUmur.getValue();
                int skor = sliderSkor.getValue();

                tableModel.addRow(new Object[]{nama, alamat, jenisKelamin, minat, kota, hobi, umur, skor});
            }
        });

        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}
