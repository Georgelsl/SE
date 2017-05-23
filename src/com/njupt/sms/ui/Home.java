package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Module;
import com.njupt.sms.beans.Semester;
import com.njupt.sms.datas.PersistentUtils;
import com.njupt.sms.model.*;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Home implements AddMilestoneUI.OnAddMilestoneFinishListener, AddStudyTaskUI.OnAddStudyTaskFinishListener, AddActivityUI.OnAddActivityFinishListener {
    private JPanel homePanel;
    private JButton exit;
    private JButton loadSemester;
    private JList moduleList;
    private JLabel welcomeTip;
    private JPanel moduleDetailPanel;
    private JTabbedPane tabLayout;
    private JButton addMilestone;
    private JTable milestoneTable;
    private JTable ganttchatTable;
    private JButton addStudyTaskButton;
    private JTable deadlineTable;
    private JTable modulesTable;
    private static JFrame frame;

    private Module currentSelectedModule;

    public Home() {
        frame = new JFrame("homePanel");
        frame.setContentPane(homePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(1000, 800);
        frame.setVisible(true);


        welcomeTip.setText("Welcome " + Session.currentAccount.getUsername());
        Semester semester = PersistentUtils.loadSemesterByAccount();
        if (semester != null) {
            showSemester();
            refreshCurrentTab();
        }

        addFrameListener();

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                saveData();
            }
        });

        loadSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                if (file.isDirectory()) {
                    JOptionPane.showMessageDialog(frame, "Please select file", "tip", JOptionPane.WARNING_MESSAGE);
                } else if (file.isFile()) {
                    Session.semester = PersistentUtils.loadSemesterFile(file);
                    showSemester();
                    refreshCurrentTab();
                }
            }
        });
        tabLayout.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                refreshCurrentTab();
            }
        });


        addMilestone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (assertModuleExist()) return;

                AddMilestoneUI addMilestoneUI = new AddMilestoneUI(currentSelectedModule);
                addMilestoneUI.setFinishListener(Home.this);
            }
        });

        addStudyTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (assertModuleExist()) return;
                AddStudyTaskUI addStudyTaskUI = new AddStudyTaskUI(currentSelectedModule);
                addStudyTaskUI.setFinishListener(Home.this);
            }
        });
    }

    private boolean assertModuleExist() {
        if (Session.semester == null || Session.semester.modules.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please load semester file", "tip", JOptionPane.WARNING_MESSAGE);
            return true;
        }
        return false;
    }

    private void refreshCurrentTab() {
        if (currentSelectedModule == null) {
            return;
        }

        switch (tabLayout.getSelectedIndex()) {
            case 0:
                modulesTable.setModel(new ModuleTableModel(Session.semester.modules));
                break;
            case 1:
                milestoneTable.setModel(new MilestoneTableModel(currentSelectedModule.getMilestones()));
                break;
            case 2:
                deadlineTable.setModel(new DeadlineTableModel(currentSelectedModule.getStudyTasks()));
                break;
            case 3:
                ganttchatTable.setModel(new GanttChartTableModel(currentSelectedModule));
                break;
        }
    }

    private void showSemester() {

        if (Session.semester.modules.isEmpty()) {
            return;
        }

        currentSelectedModule = Session.semester.modules.get(0);
        moduleList.setModel(new ModuleListModel());

        moduleList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                currentSelectedModule = (Module) moduleList.getSelectedValue();
                refreshCurrentTab();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {


            }
        });

        moduleList.setCellRenderer(new ModuleCell());
    }

    private void saveData() {
        PersistentUtils.storeSemesterForCurrentAccount();
    }

    @Override
    public void onAddMilestoneFinish() {
        refreshCurrentTab();
    }

    @Override
    public void onAddStudyTaskFinish() {
        refreshCurrentTab();
    }

    @Override
    public void onAddActivityFinish() {
        refreshCurrentTab();
    }

    class ModuleListModel extends AbstractListModel {
        @Override
        public int getSize() {
            return Session.semester.modules.size();
        }

        @Override
        public Object getElementAt(int index) {
            return Session.semester.modules.get(index);
        }
    }

    class ModuleCell extends JLabel implements ListCellRenderer<Module> {
        @Override
        public Component getListCellRendererComponent(
                JList<? extends Module> list, Module value,
                int index, boolean isSelected, boolean cellHasFocus) {

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);

            setText(value.getModuleName());

            return this;
        }
    }

    private void addFrameListener() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                saveData();
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }


}
