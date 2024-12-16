package controller;
import view.UserView;

import javax.swing.*;

import model.User;
import model.UserMapper;
<<<<<<< HEAD
import view.UserPdf;
import view.UserView;
=======
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.apache.ibatis.session.*;

public class UserController {
    private UserView view;
    private UserMapper mapper;
<<<<<<< HEAD
    private UserPdf pdf;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session){
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
=======
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, SqlSession session){
        this.view = view;
        this.mapper = mapper;
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshUserListener(new RefreshListener());
<<<<<<< HEAD
        this.view.addExportListener(new ExportListener());
=======
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565
    }

    class AddUserListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String name = view.getNameInput();
            String email = view.getEmailInput();
            if(!name.isEmpty() && !email.isEmpty()){
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                mapper.insertUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view,"User added successfully!");
            }else{
                JOptionPane.showMessageDialog(view,"Please fill in all fields");
            }
        }
    }

    class RefreshListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                                .map(u -> u.getName() + " ("+u.getEmail()+")")
                                .toArray(String[]::new);
            view.setUserList(userArray);
        }
    }
<<<<<<< HEAD

    class ExportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            List<User> users = mapper.getAllUsers();
            pdf.exportPdf(users);
        }
    }
}
=======
}
>>>>>>> 797ff23aeb0406db82a84c10a652d4c59f02e565
