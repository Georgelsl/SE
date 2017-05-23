package com.njupt.sms.beans;

public class Milestone {

    private String name;
    private int moduleCode;
    private String attachCourseWork;
    private String attachExam;
    public String setDate;
    public String dueDate;

    public Milestone() {
    }

    public Milestone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getAttachCourseWork() {
        return attachCourseWork;
    }

    public void setAttachCourseWork(String attachCourseWork) {
        this.attachCourseWork = attachCourseWork;
    }

    public String getAttachExam() {
        return attachExam;
    }

    public void setAttachExam(String attachExam) {
        this.attachExam = attachExam;
    }
}
