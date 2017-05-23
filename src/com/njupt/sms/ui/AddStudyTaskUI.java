package com.njupt.sms.ui;

import com.njupt.sms.beans.Milestone;
import com.njupt.sms.beans.Module;
import com.njupt.sms.beans.StudyTask;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddStudyTaskUI {
    private JTextField taskNameTf;
    private JRadioButton examRb;
    private JRadioButton courseworkRb;
    private JTextField startTimeTf;
    private JTextField endtimeTf;
    private JButton finishBt;
    private JPanel mainPanel;
    private JTextField spentTime;
    private JTextField taskType;
    private JTextField criterion;
    private JTextField dependency;
    private JTextField note;

    private static JFrame frame;
    private OnAddStudyTaskFinishListener finishListener;

    public AddStudyTaskUI(Module module) {
        frame = new JFrame("register");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(600, 400);
        frame.setVisible(true);


        courseworkRb.setSelected(true);

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
        finishBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String detail = taskNameTf.getText();
                boolean isExam = examRb.isSelected() ? true : false;
                if (detail.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please input task detail!", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                StudyTask studyTask = new StudyTask();
                studyTask.setDetail(detail);
                if (isExam) {
                    studyTask.setExam("是");
                    studyTask.setCoursework("否");
                } else {
                    studyTask.setExam("否");
                    studyTask.setCoursework("是");
                }

                String time = spentTime.getText().trim();
                String type = taskType.getText().trim();
                String criterionStr = criterion.getText().trim();
                String depen = dependency.getText().trim();
                String noteStr = note.getText().trim();

                if (time.length() == 0 || type.length() == 0 || criterionStr.length() == 0 || depen.length() == 0 || noteStr.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please input  all !", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                studyTask.spentTime = time;
                studyTask.taskType = type;
                studyTask.dependency = depen;
                studyTask.note = noteStr;
                studyTask.criterion = criterionStr;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date startTime, endTime;

                try {
                    startTime = format.parse(startTimeTf.getText());
                    endTime = format.parse(endtimeTf.getText());
                } catch (ParseException e1) {
                    JOptionPane.showMessageDialog(frame, "time format error!", "Tip", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                studyTask.setStartDate(startTimeTf.getText());
                studyTask.setEndDate(endtimeTf.getText());
                studyTask.setModuleCode(module.getModuleCode());
                List<StudyTask> studyTasks = module.getStudyTasks();
                studyTasks.add(studyTask);

                JOptionPane.showMessageDialog(frame, "Add Success", "Tip", JOptionPane.WARNING_MESSAGE);
                frame.dispose();

                if (finishListener != null) {
                    finishListener.onAddStudyTaskFinish();
                }
            }
        });
    }

    public void setFinishListener(OnAddStudyTaskFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public interface OnAddStudyTaskFinishListener {
        void onAddStudyTaskFinish();
    }
}
