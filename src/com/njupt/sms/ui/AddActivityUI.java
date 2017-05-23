package com.njupt.sms.ui;

import com.njupt.sms.beans.Activity;
import com.njupt.sms.beans.Milestone;
import com.njupt.sms.beans.Module;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddActivityUI {
    private JTextField descTf;
    private JTextField courseworkTf;
    private JButton finishBt;

    private JPanel mainPanel;
    private JTextField milestoneTf;
    private OnAddActivityFinishListener finishListener;

    private static JFrame frame;


    public AddActivityUI(Module module) {
        frame = new JFrame("add activity");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(500, 200);
        frame.setVisible(true);


        finishBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = descTf.getText().trim();
                String coursework = courseworkTf.getText().trim();
                String milestone = milestoneTf.getText().trim();

                if (desc.length() == 0 || coursework.length() == 0 || milestone.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please input all info!", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Activity activity = new Activity(module.getModuleCode());
                activity.setCourseWork(coursework);
                activity.setDesc(desc);
                activity.setMilestone(new Milestone(milestone));
                List<Activity> activities = module.getActivities();
                activities.add(activity);

                JOptionPane.showMessageDialog(frame, "Add Success", "Tip", JOptionPane.WARNING_MESSAGE);
                frame.dispose();

                if (finishListener != null) {
                    finishListener.onAddActivityFinish();
                }

            }
        });
    }

    public void setFinishListener(OnAddActivityFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public interface OnAddActivityFinishListener {
        void onAddActivityFinish();
    }
}
