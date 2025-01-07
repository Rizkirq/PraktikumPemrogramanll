package main;

import model.*;
import view.*;
import controller.*;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Membuat frame utama
            JFrame mainFrame = new JFrame("Aplikasi Pengelolaan Data");
            mainFrame.setSize(800, 600);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Membuat tabbed pane untuk menampung semua view
            JTabbedPane tabbedPane = new JTabbedPane();

            // Pemesan
            PemesanModel pemesanModel = new PemesanModel();
            PemesanView pemesanView = new PemesanView();
            new PemesanController(pemesanModel, pemesanView);
            tabbedPane.addTab("Pemesan", pemesanView);

            // Jadwal
            JadwalModel jadwalModel = new JadwalModel();
            JadwalView jadwalView = new JadwalView();
            new JadwalController(jadwalModel, jadwalView);
            tabbedPane.addTab("Jadwal", jadwalView);

            // Armada
            ArmadaModel armadaModel = new ArmadaModel();
            ArmadaView armadaView = new ArmadaView();
            new ArmadaController(armadaView, armadaModel); // Perbaiki urutan parameter di sini
            tabbedPane.addTab("Armada", armadaView);

            // Menambahkan tabbed pane ke frame utama
            mainFrame.add(tabbedPane);
            mainFrame.setVisible(true);
        });
    }
}
