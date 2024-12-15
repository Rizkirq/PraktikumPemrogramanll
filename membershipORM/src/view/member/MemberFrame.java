package view.member;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import dao.MemberDao;
import dao.JenisMemberDao;
import model.JenisMember;
import model.Member;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private JTable table;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        // Inisialisasi data
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        // Komponen Input
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 20);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 100, 20);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 200, 20);
        populateComboJenis();

        JButton buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 150, 80, 30);

        JButton buttonEdit = new JButton("Edit");
        buttonEdit.setBounds(110, 150, 80, 30);

        JButton buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(200, 150, 80, 30);

        // Tabel
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 450, 200);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);

        // Listener
        buttonSimpan.addActionListener(new MemberButtonSimpanActionListener(this, memberDao));
        buttonEdit.addActionListener(e -> editMember());
        buttonDelete.addActionListener(e -> deleteMember());

        // Tambah ke frame
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(buttonSimpan);
        this.add(buttonEdit);
        this.add(buttonDelete);
        this.add(scrollableTable);

        this.setSize(500, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void refreshTableData() {
        memberList = memberDao.findAll();
        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private Member getSelectedMember() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return memberList.get(selectedRow);
        } else {
            showAlert("Pilih baris terlebih dahulu!");
            return null;
        }
    }

    private void editMember() {
        Member selectedMember = getSelectedMember();
        if (selectedMember != null) {
            String namaBaru = JOptionPane.showInputDialog(this, "Masukkan Nama Baru:", selectedMember.getNama());
            if (namaBaru != null && !namaBaru.trim().isEmpty()) {
                selectedMember.setNama(namaBaru);

                JenisMember jenisBaru = jenisMemberList.get(comboJenis.getSelectedIndex());
                selectedMember.setJenisMember(jenisBaru);
                selectedMember.setJenisMemberId(jenisBaru.getId());

                memberDao.update(selectedMember);
                refreshTableData();
            }
        }
    }

    private void deleteMember() {
        Member selectedMember = getSelectedMember();
        if (selectedMember != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                memberDao.delete(selectedMember);
                memberList.remove(selectedMember);
                refreshTableData();
            }
        }
    }
}
