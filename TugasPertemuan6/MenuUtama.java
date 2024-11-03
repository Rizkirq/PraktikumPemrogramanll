/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Prak2.Tugas3;

import javax.swing.*;

public class MenuUtama extends JFrame {

    public MenuUtama() {
        // Mengatur judul dan ukuran frame
        setTitle("Aplikasi Form Input");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat JMenuBar dan JMenu
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuForm = new JMenu("Form");

        // Menambahkan item menu
        JMenuItem menuItemExit = new JMenuItem("Keluar");
        menuItemExit.addActionListener(e -> System.exit(0));

        JMenuItem menuItemForm1 = new JMenuItem("Form 1");
        menuItemForm1.addActionListener(e -> new FormFrame());

        // Menambahkan item ke menu
        menuFile.add(menuItemExit);
        menuForm.add(menuItemForm1);

        // Menambahkan menu ke menu bar
        menuBar.add(menuFile);
        menuBar.add(menuForm);

        // Menambahkan menu bar ke frame
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuUtama frame = new MenuUtama();
            frame.setVisible(true);
        });
    }
}

