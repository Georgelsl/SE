package com.njupt.sms.model;

import com.njupt.sms.beans.Activity;
import com.njupt.sms.beans.StudyTask;
import org.jfree.base.modules.AbstractModule;
import org.jfree.base.modules.ModuleInitializeException;
import org.jfree.base.modules.SubSystem;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class ActivityTableModel extends AbstractTableModel {

    private List<Activity> list;
    public static final String[] columnShowStrings = {"description", "for coursework", "milestone"};

    public ActivityTableModel(List<Activity> list) {
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

        Activity activity = list.get(rowIndex - 1);

        switch (columnIndex) {
            case 0:
                return activity.getDesc();
            case 1:
                return activity.getCourseWork();
            case 2:

                if (activity.getMilestone() != null) {
                    return activity.getMilestone().getName();
                } else {
                    return "no milestone desc";
                }


        }
        return "";
    }
}
