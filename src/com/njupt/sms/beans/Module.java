package com.njupt.sms.beans;

import java.util.ArrayList;
import java.util.List;

public class Module {
    private String moduleName;
    private int moduleCode;
    private List<Milestone> milestones;
    private List<StudyTask> studyTasks;
    private List<Activity> activities;
    private List<Exam> exams;

    public String assignmentName;
    public String setDate;
    public String dueDate;
    public String forExam;
    public String forCoursework;
    public String weighting;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    public List<Milestone> getMilestones() {
        if (milestones == null) {
            milestones = new ArrayList<>();
        }

        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }


    public List<StudyTask> getStudyTasks() {
        if (studyTasks == null) {
            studyTasks = new ArrayList<>();
        }

        return studyTasks;
    }

    public void setStudyTasks(List<StudyTask> studyTasks) {
        this.studyTasks = studyTasks;
    }

    public List<Activity> getActivities() {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}

