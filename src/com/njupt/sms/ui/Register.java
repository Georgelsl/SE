package com.njupt.sms.ui;

import com.njupt.sms.beans.Account;
import com.njupt.sms.datas.PersistentUtils;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {
    private JTextField usernameTv;
    private JTextField passwordTv;
    private JButton registerButton;
    private JPanel registerPanel;
    private static JFrame frame;


    public Register() {
        frame = new JFrame("register");
        frame.setContentPane(registerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(500, 300);
        frame.setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordTv.getText().trim();
                String username = usernameTv.getText().trim();

                if (username.length() > 0 && password.length() > 0) {
                    Account newAccount = new Account(username, password);
                    PersistentUtils.storeAccount(newAccount);
                    JOptionPane.showMessageDialog(frame, "Register Success", "提示", JOptionPane.WARNING_MESSAGE);
                    frame.dispose();
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please input passwordTv or password", "提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });
    }

}
