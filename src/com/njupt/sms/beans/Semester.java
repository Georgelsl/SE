package com.njupt.sms.beans;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    public List<Module> modules;
    public String desc;

    public Semester() {
        this.modules = new ArrayList<>();
    }
}
