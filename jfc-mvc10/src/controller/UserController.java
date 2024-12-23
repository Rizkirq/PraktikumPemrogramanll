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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error adding user: " + ex.getMessage());
            }
        }
    }

    class UpdateUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = new User();
            user.setName(view.getNameInput());
            user.setEmail(view.getEmailInput());
            user.setPhone(view.getPhoneInput());
            user.setAddress(view.getAddressInput());

            try {
                mapper.updateUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User updated successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error updating user: " + ex.getMessage());
            }
        }
    }

    class DeleteUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = view.getEmailInput();
            try {
                mapper.deleteUser(email);
                session.commit();
                JOptionPane.showMessageDialog(view, "User deleted successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error deleting user: " + ex.getMessage());
            }
        }
    }

    class RefreshUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                    .map(u -> u.getName() + " (" + u.getEmail() + ")")
                    .toArray(String[]::new);
            view.setUserList(userArray);
        }
    }
}