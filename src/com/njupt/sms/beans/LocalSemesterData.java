package com.njupt.sms.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalSemesterData {
    public String semester;
    public Map<String, LocalSemester> semesterMap;

    public LocalSemesterData() {
        semesterMap = new HashMap<>();
    }
}
