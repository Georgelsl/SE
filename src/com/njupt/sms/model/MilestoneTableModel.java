package com.njupt.sms.model;

import com.njupt.sms.beans.Milestone;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MilestoneTableModel extends AbstractTableModel {

    private List<Milestone> list;
    public static final String[] columnShowStrings = {"detail", "Set Date", "Due Date", "isCourseWOrk", "isExam"};

    public MilestoneTableModel(List<Milestone> list) {
        this.list = list;
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

        Milestone milestone = list.get(rowIndex - 1);

        switch (columnIndex) {
            case 0:
                return milestone.getName();
            case 1:
                return milestone.setDate;
            case 2:
                return milestone.dueDate;
            case 3:
                return milestone.getAttachCourseWork();
            case 4:
                return milestone.getAttachExam();
        }

        return "";
    }


    @Override
    public String getColumnName(int column) {
        return columnShowStrings[column];
    }

}
