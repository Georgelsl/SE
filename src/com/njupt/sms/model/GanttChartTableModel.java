package com.njupt.sms.model;

import com.njupt.sms.beans.Exam;
import com.njupt.sms.beans.GanttBean;
import com.njupt.sms.beans.Module;
import com.njupt.sms.beans.StudyTask;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GanttChartTableModel extends AbstractTableModel {

    public static final String[] columnShowStrings = {"name", "category", "deadline date", "last day"};
    private List<GanttBean> list = new ArrayList<>();

    public GanttChartTableModel(Module currentSelectedModule) {
        List<Exam> exams = currentSelectedModule.getExams();
        if (exams != null) {
            for (Exam exam : exams) {
                GanttBean ganttBean = new GanttBean();
                ganttBean.name = exam.getExam();
                ganttBean.category = "exam";
                ganttBean.deadline = exam.getDeadline();
                ganttBean.lastTime = getLastTime(ganttBean.deadline);
                list.add(ganttBean);
            }
        }

        List<StudyTask> studyTasks = currentSelectedModule.getStudyTasks();
        if (studyTasks != null) {
            for (StudyTask studyTask : studyTasks) {
                GanttBean ganttBean = new GanttBean();
                ganttBean.name = studyTask.getDetail();
                ganttBean.category = "studytask";
                ganttBean.deadline = studyTask.getEndDate();
                ganttBean.lastTime = getLastTime(ganttBean.deadline);
                list.add(ganttBean);
            }
        }
    }

    private int getLastTime(String deadline) {
        int lastTime = -1;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date deadlineDate = dateFormat.parse(deadline);
            Date currentDate = new Date();
            lastTime = (int) ((deadlineDate.getTime() - currentDate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            return -1;
        }

        return lastTime;
    }

    @Override
    public int getRowCount() {
        return list.size() + 1;
    }

    @Override
    public int getColumnCount() {
        return columnShowStrings.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex == 0) {
            return columnShowStrings[columnIndex];
        }

        GanttBean ganttBean = list.get(rowIndex - 1);

        switch (columnIndex) {
            case 0:
                return ganttBean.name;
            case 1:
                return ganttBean.category;
            case 2:
                return ganttBean.deadline;
            case 3:
                return ganttBean.lastTime + " day";
        }
        return "";
    }
}
