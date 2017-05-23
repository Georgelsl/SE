package com.njupt.sms.ui;

import com.njupt.sms.beans.Milestone;
import com.njupt.sms.beans.Module;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddMilestoneUI {
    private JTextField detail;
    private JButton finishBt;
    private JRadioButton courseworkRb;
    private JRadioButton examRb;
    private JPanel mainPanel;
    private JTextField setDate;
    private JTextField dueDate;
    private static JFrame frame;

    private OnAddMilestoneFinishListener finishListener;


    public AddMilestoneUI(Module module) {

        frame = new JFrame("register");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(500, 300);
        frame.setVisible(true);

        courseworkRb.setSelected(true);

        finishBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = detail.getText().trim();
                boolean isExam = examRb.isSelected() ? true : false;

                if (name.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please input milestone detail!", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Milestone milestone = new Milestone();
                milestone.setName(name);
                if (isExam) {
                    milestone.setAttachExam("是");
                    milestone.setAttachCourseWork("否");
                } else {
                    milestone.setAttachExam("否");
                    milestone.setAttachCourseWork("是");
                }

                String setDateStr = setDate.getText().trim();
                String dueDateStr = dueDate.getText().trim();

                if (setDateStr.length() == 0 || dueDateStr.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please input date!", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                milestone.setDate = setDateStr;
                milestone.dueDate = dueDateStr;
                milestone.setModuleCode(module.getModuleCode());
                List<Milestone> milestones = module.getMilestones();
                milestones.add(milestone);

                JOptionPane.showMessageDialog(frame, "Add Success", "Tip", JOptionPane.WARNING_MESSAGE);
                frame.dispose();

                if (finishListener != null) {
                    finishListener.onAddMilestoneFinish();
                }
            }
        });
        courseworkRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (courseworkRb.isSelected()) {
                    examRb.setSelected(false);
                }
            }
        });
        examRb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (examRb.isSelected()) {
                    courseworkRb.setSelected(false);
                }
            }
        });
    }

    public void setFinishListener(OnAddMilestoneFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public interface OnAddMilestoneFinishListener {
        void onAddMilestoneFinish();
    }
}
