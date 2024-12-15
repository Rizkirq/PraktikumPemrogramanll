package view.member;

import dao.MemberDao;
import model.JenisMember;
import model.Member;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class MemberButtonSimpanActionListener implements ActionListener {
    private MemberFrame memberFrame;
    private MemberDao memberDao;

    public MemberButtonSimpanActionListener(MemberFrame memberFrame, MemberDao memberDao) {
        this.memberFrame = memberFrame;
        this.memberDao = memberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = memberFrame.getNama();
        if (nama.isEmpty()) {
            memberFrame.showAlert("Nama tidak boleh kosong");
        } else if (nama.length() < 3) {
            memberFrame.showAlert("Nama harus lebih dari 3 karakter");
        } else {
            JenisMember jenisMember = memberFrame.getJenisMember();
            Member member = new Member();
            member.setId(UUID.randomUUID().toString());
            member.setNama(nama);
            member.setJenisMember(jenisMember);
            member.setJenisMemberId(jenisMember.getId());

            memberDao.insert(member);
            memberFrame.addMember(member);
        }
    }
}
