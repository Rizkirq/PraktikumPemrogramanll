package view.jenismember;

import dao.JenisMemberDao;
import model.JenisMember;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class JenisMemberFrame extends JFrame {
    private JenisMemberDao jenisMemberDao;
    private JTable tableJenisMember;
    private DefaultTableModel tableModel;
    private JTextField textNama;
    private JButton buttonSimpan;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private List<JenisMember> jenisMemberList;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;

        this.setTitle("Jenis Member");
        this.setLayout(new BorderLayout());
        this.setSize(500, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Nama:"));
        textNama = new JTextField(20);
        inputPanel.add(textNama);
        buttonSimpan = new JButton("Simpan");
        buttonUpdate = new JButton("Update");
        buttonDelete = new JButton("Delete");
        inputPanel.add(buttonSimpan);
        inputPanel.add(buttonUpdate);
        inputPanel.add(buttonDelete);

        // Tabel
        tableJenisMember = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nama"}, 0);
        tableJenisMember.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableJenisMember);

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        // Event Listener
        buttonSimpan.addActionListener(new JenisMemberButtonSimpanActionListener(this, jenisMemberDao));
        buttonUpdate.addActionListener(e -> updateJenisMember());
        buttonDelete.addActionListener(e -> deleteJenisMember());

        loadTableData();
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        jenisMemberList = jenisMemberDao.findAll();
        for (JenisMember jm : jenisMemberList) {
            tableModel.addRow(new Object[]{jm.getId(), jm.getNama()});
        }
    }

    public String getNama() {
        return textNama.getText();
    }

    public void setNama(String nama) {
        textNama.setText(nama);
    }

    public JenisMember getSelectedJenisMember() {
        int selectedRow = tableJenisMember.getSelectedRow();
        if (selectedRow >= 0) {
            return jenisMemberList.get(selectedRow);
        }
        return null;
    }

    public void refreshList() {
        loadTableData();
    }

    private void updateJenisMember() {
        JenisMember selected = getSelectedJenisMember();
        if (selected != null) {
            String namaBaru = JOptionPane.showInputDialog(this, "Masukkan Nama Baru:", selected.getNama());
            if (namaBaru != null && !namaBaru.trim().isEmpty()) {
                selected.setNama(namaBaru);
                jenisMemberDao.update(selected);
                refreshList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin diupdate!");
        }
    }

    private void deleteJenisMember() {
        JenisMember selected = getSelectedJenisMember();
        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                jenisMemberDao.delete(selected);
                refreshList();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!");
        }
    }
}
