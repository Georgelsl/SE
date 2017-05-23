package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Account;
import com.njupt.sms.datas.PersistentUtils;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton LoginButton;
    private JPanel LoginPanel;
    private JButton registerAccountButton;
    private static JFrame frame;

    public Login() {

        frame = new JFrame("Login");
        frame.setContentPane(LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(500, 200);
        frame.setVisible(true);


        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//
//
                if (usernameTextField.getText() == null || usernameTextField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please input username", "tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (passwordTextField.getText() == null || passwordTextField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Please input password", "tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String username = usernameTextField.getText().trim();
                String password = passwordTextField.getText().trim();
                Account loginAccount = new Account(username, password);
                if (PersistentUtils.checkAccount(loginAccount)) {
                    JOptionPane.showMessageDialog(frame, "LogIn Success!", "tip", JOptionPane.WARNING_MESSAGE);
                    Session.currentAccount = loginAccount;
                    new Home();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "error username or password!!", "tip", JOptionPane.WARNING_MESSAGE);
                }

            }
        });


        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    passwordTextField.requestFocus();

                }
                super.keyPressed(e);
            }
        });
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    LoginButton.doClick();

                }
                super.keyPressed(e);
            }
        });


        registerAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                frame.dispose();
            }
        });
    }


}
