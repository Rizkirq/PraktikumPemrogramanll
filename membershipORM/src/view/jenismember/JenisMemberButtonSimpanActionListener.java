package view.jenismember;

import dao.JenisMemberDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import model.JenisMember;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private final JenisMemberFrame jenisMemberFrame;
    private final JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = jenisMemberFrame.getNama();
        JenisMember selected = jenisMemberFrame.getSelectedJenisMember();

        if (selected == null) {
            // Data baru
            JenisMember jenisMember = new JenisMember();
            jenisMember.setId(UUID.randomUUID().toString());
            jenisMember.setNama(nama);
            jenisMemberDao.insert(jenisMember);
        } else {
            // Perbarui data yang ada
            selected.setNama(nama);
            jenisMemberDao.update(selected);
        }

        // Refresh list untuk mencerminkan perubahan
        jenisMemberFrame.refreshList();
        jenisMemberFrame.setNama(""); // Kosongkan field nama
    }
}
