package controller;

import model.User;
import model.UserMapper;
import org.apache.ibatis.session.SqlSession;
import view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addUpdateUserListener(new UpdateUserListener());
        this.view.addDeleteUserListener(new DeleteUserListener());
        this.view.addRefreshUserListener(new RefreshUserListener());

        // Panggil refresh saat aplikasi dimulai
        new RefreshUserListener().actionPerformed(null);
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = new User();
            user.setName(view.getNameInput());
            user.setEmail(view.getEmailInput());
            user.setPhone(view.getPhoneInput());
            user.setAddress(view.getAddressInput());

            try {
                mapper.insertUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User added successfully!");

                // Refresh data setelah penambahan
                new RefreshUserListener().actionPerformed(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error adding user: " + ex.getMessage());
            }
        }
    }

    class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUser = view.getSelectedUser();
            if (selectedUser == null) {
                JOptionPane.showMessageDialog(view, "Please select a user to update.");
                return;
            }

            String email = selectedUser.substring(selectedUser.indexOf("(") + 1, selectedUser.indexOf(")"));
            User user = new User();
            user.setName(view.getNameInput());
            user.setEmail(email); // Tetap gunakan email sebagai kunci
            user.setPhone(view.getPhoneInput());
            user.setAddress(view.getAddressInput());

            try {
                mapper.updateUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User updated successfully!");

                // Refresh data setelah pembaruan
                new RefreshUserListener().actionPerformed(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error updating user: " + ex.getMessage());
            }
        }
    }

    class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedUser = view.getSelectedUser();
            if (selectedUser == null) {
                JOptionPane.showMessageDialog(view, "Please select a user to delete.");
                return;
            }

            String email = selectedUser.substring(selectedUser.indexOf("(") + 1, selectedUser.indexOf(")"));
            try {
                mapper.deleteUser(email);
                session.commit();
                JOptionPane.showMessageDialog(view, "User deleted successfully!");

                // Refresh data setelah penghapusan
                new RefreshUserListener().actionPerformed(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error deleting user: " + ex.getMessage());
            }
        }
    }

    class RefreshUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingWorker<Void, String> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                    List<User> users = mapper.getAllUsers();
                    for (User user : users) {
                        publish(user.getName() + " (" + user.getEmail() + ")");
                    }
                    return null;
                }

                @Override
                protected void process(List<String> chunks) {
                    view.setUserList(chunks.toArray(new String[0]));
                }
            };
            worker.execute();
        }
    }
}
