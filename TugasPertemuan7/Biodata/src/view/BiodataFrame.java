package view;

import dao.BiodataDao;
import model.Biodata;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BiodataFrame extends JFrame {
    private final JTextField idField = new JTextField(10);
    private final JTextField namaField = new JTextField(20);
    private final JTextField alamatField = new JTextField(30);
    private final BiodataDao dao = new BiodataDao();

    public BiodataFrame() {
        setTitle("Aplikasi Biodata");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Nama:"));
        add(namaField);
        add(new JLabel("Alamat:"));
        add(alamatField);

        JButton addButton = new JButton("Tambah");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahBiodata();
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBiodata();
            }
        });

        JButton deleteButton = new JButton("Hapus");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hapusBiodata();
            }
        });

        add(addButton);
        add(updateButton);
        add(deleteButton);
    }

    private void tambahBiodata() {
        try {
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            dao.addBiodata(new Biodata(0, nama, alamat));
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambah data.");
        }
    }

    private void updateBiodata() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            dao.updateBiodata(new Biodata(id, nama, alamat));
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data.");
        }
    }

    private void hapusBiodata() {
        try {
            int id = Integer.parseInt(idField.getText());
            dao.deleteBiodata(id);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menghapus data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BiodataFrame().setVisible(true));
    }
}
