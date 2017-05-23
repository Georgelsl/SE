package com.njupt.sms.beans;

public class Activity {
    private String desc;
    private String courseWork;
    private Milestone milestone;
    private int moduleCode;


    public String getDesc() {
        return desc;
    }

    public Activity(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCourseWork() {
        return courseWork;
    }

    public void setCourseWork(String courseWork) {
        this.courseWork = courseWork;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }
}
