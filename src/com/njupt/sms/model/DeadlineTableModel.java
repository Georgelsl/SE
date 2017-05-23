package com.njupt.sms.model;

import com.njupt.sms.beans.Milestone;
import com.njupt.sms.beans.StudyTask;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class DeadlineTableModel extends AbstractTableModel {

    private List<StudyTask> list;
    public static final String[] columnShowStrings = {"Task Name", "startTime", "endTime", "for exam", "for coursework", "Spent Time", "Requirement criterion", "task dependency", "note"};

    public DeadlineTableModel(List<StudyTask> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size()+1;
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

        StudyTask studyTask = list.get(rowIndex-1);

        switch (columnIndex) {
            case 0:
                return studyTask.getDetail();
            case 1:
                return studyTask.getStartDate();
            case 2:
                return studyTask.getEndDate();
            case 3:
                return studyTask.getExam();
            case 4:
                return studyTask.getCoursework();
            case 5:
                return studyTask.spentTime;
            case 6:
                return studyTask.criterion;
            case 7:
                return studyTask.note;
        }
        return "";
    }
}
