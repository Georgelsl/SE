package com.njupt.sms.model;

import com.njupt.sms.beans.Milestone;
import com.njupt.sms.beans.Module;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class ModuleTableModel extends AbstractTableModel {


    private List<Module> list;
    public static final String[] columnShowStrings = {"moduleName", "assignment", "Set Date", "Due Date", "Weighting", "For Exam", "For Coursework"};


    public ModuleTableModel(List<Module> list) {
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

        Module module = list.get(rowIndex - 1);

        switch (columnIndex) {
            case 0:
                return module.getModuleName();
            case 1:
                return module.assignmentName;
            case 2:
                return module.setDate;
            case 3:
                return module.dueDate;
            case 4:
                return module.weighting;
            case 5:
                return module.forExam;
            case 6:
                return module.forCoursework;
        }

        return "";
    }


    @Override
    public String getColumnName(int column) {
        return columnShowStrings[column];
    }

}
