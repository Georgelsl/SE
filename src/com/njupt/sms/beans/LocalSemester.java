package com.njupt.sms.beans;

public class LocalSemester {
    public String username;
    public Semester semester;

    public LocalSemester() {
        semester = new Semester();
    }

    public LocalSemester(String username, Semester semester) {
        this.username = username;
        this.semester = semester;
    }
}
