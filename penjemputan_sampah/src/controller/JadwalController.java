package controller;

import model.JadwalModel;
import view.JadwalView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JadwalController {
    private JadwalModel model;
    private JadwalView view;

    public JadwalController(JadwalModel model, JadwalView view) {
        this.model = model;
        this.view = view;

        loadTableData();

        view.btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nama = view.textNama.getText().trim();
                String tanggal = view.textTanggal.getText().trim();
                String lokasi = view.textLokasi.getText().trim();

                // Validasi input
                if (nama.isEmpty() || tanggal.isEmpty() || lokasi.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!");
                    return;
                }

                // Validasi format tanggal
                if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(view, "Format tanggal harus YYYY-MM-DD!");
                    return;
                }

                if (model.addJadwal(nama, tanggal, lokasi)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil ditambahkan!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menambahkan data!");
                }
            }
        });

        view.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih data yang akan diupdate!");
                    return;
                }

                int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
                String nama = view.textNama.getText().trim();
                String tanggal = view.textTanggal.getText().trim();
                String lokasi = view.textLokasi.getText().trim();

                if (nama.isEmpty() || tanggal.isEmpty() || lokasi.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Semua kolom harus diisi!");
                    return;
                }

                if (!tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    JOptionPane.showMessageDialog(view, "Format tanggal harus YYYY-MM-DD!");
                    return;
                }

                if (model.updateJadwal(id, nama, tanggal, lokasi)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil diupdate!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal mengupdate data!");
                }
            }
        });

        view.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Pilih data yang akan dihapus!");
                    return;
                }

                int id = Integer.parseInt(view.tableModel.getValueAt(selectedRow, 0).toString());
                if (model.deleteJadwal(id)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                    loadTableData();
                } else {
                    JOptionPane.showMessageDialog(view, "Gagal menghapus data!");
                }
            }
        });
    }

    private void loadTableData() {
        view.tableModel.setRowCount(0);
        for (String[] row : model.getAllJadwal()) {
            view.tableModel.addRow(row);
        }
    }
}
