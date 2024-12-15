package JFC;

import java.awt.event.*;
import javax.swing.*;

public class HelloInput extends JFrame {

    public HelloInput() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(400, 600);
        this.setLayout(null);

       
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 10, 350, 30);
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 40, 350, 20);

        
        JLabel labelNomorHP = new JLabel("Nomor HP:");
        labelNomorHP.setBounds(15, 70, 350, 30);
        JTextField textFieldNomorHP = new JTextField();
        textFieldNomorHP.setBounds(15, 100, 350, 20);

        
        JLabel labelJenisTabungan = new JLabel("Jenis Tabungan:");
        labelJenisTabungan.setBounds(15, 130, 350, 30);
        String[] jenisTabungan = {"Tabungan Harian", "Tabungan Bulanan", "Tabungan Pendidikan", "Tabungan Liburan"};
        JList<String> listJenisTabungan = new JList<>(jenisTabungan);
        listJenisTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listJenisTabungan);
        scrollPane.setBounds(15, 160, 150, 60);

        
        JLabel labelFrekuensiTransaksi = new JLabel("Frekuensi Transaksi per Bulan:");
        labelFrekuensiTransaksi.setBounds(15, 230, 350, 30);
        JSpinner spinnerFrekuensiTransaksi = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerFrekuensiTransaksi.setBounds(15, 260, 50, 20);

        
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 290, 350, 30);
        JSpinner spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(15, 320, 100, 20);

        
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(15, 350, 350, 30);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(15, 380, 350, 20);

        
        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(15, 410, 350, 30);
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(15, 440, 350, 20);

        
        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 480, 100, 40);

        
        JTextArea txtOutput = new JTextArea();
        txtOutput.setBounds(15, 530, 350, 100);

       
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenuItem menuItemReset = new JMenuItem("Reset");
        JMenuItem menuItemExit = new JMenuItem("Exit");

       
        menuItemReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNama.setText("");
                textFieldNomorHP.setText("");
                listJenisTabungan.clearSelection();
                spinnerFrekuensiTransaksi.setValue(1);
                spinnerTanggalLahir.setValue(new java.util.Date());
                passwordField.setText("");
                confirmPasswordField.setText("");
                txtOutput.setText("");
            }
        });

      
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
        menuFile.add(menuItemReset);
        menuFile.add(menuItemExit);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        
        buttonSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nomorHP = textFieldNomorHP.getText();
                String jenisTabungan = listJenisTabungan.getSelectedValue();
                int frekuensiTransaksi = (int) spinnerFrekuensiTransaksi.getValue();
                String tanggalLahir = spinnerTanggalLahir.getValue().toString();

                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String passwordMatch = password.equals(confirmPassword) ? "Cocok" : "Tidak Cocok";

                txtOutput.setText(""); 
                txtOutput.append("Nama: " + nama + "\n");
                txtOutput.append("Nomor HP: " + nomorHP + "\n");
                txtOutput.append("Jenis Tabungan: " + (jenisTabungan != null ? jenisTabungan : "Belum Dipilih") + "\n");
                txtOutput.append("Frekuensi Transaksi: " + frekuensiTransaksi + "\n");
                txtOutput.append("Tanggal Lahir: " + tanggalLahir + "\n");
                txtOutput.append("Password dan Confirm Password: " + passwordMatch + "\n");
            }
        });

   
        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelNomorHP);
        this.add(textFieldNomorHP);
        this.add(labelJenisTabungan);
        this.add(scrollPane);
        this.add(labelFrekuensiTransaksi);
        this.add(spinnerFrekuensiTransaksi);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(labelConfirmPassword);
        this.add(confirmPasswordField);
        this.add(buttonSimpan);
        this.add(txtOutput);
    }

    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloInput frame = new HelloInput();
                frame.setVisible(true);
            }
        });
    }
}
