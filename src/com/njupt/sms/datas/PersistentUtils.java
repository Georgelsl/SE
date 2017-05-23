package com.njupt.sms.datas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.njupt.sms.Session;
import com.njupt.sms.beans.*;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PersistentUtils {

    private static final String ACCOUNTS_FILE_NAME = "./accounts.txt";
    private static final String SEMESTER_FILE_NAME = "./semester_list.txt";

    public static void storeSemesterForCurrentAccount() {
        LocalSemester semester = new LocalSemester(Session.currentAccount.getUsername(), Session.semester);
        LocalSemesterData semesterData = loadSemesters();
        semesterData.semesterMap.put(Session.currentAccount.getUsername(), semester);
        storeSemesters(semesterData);
    }

    public static Semester loadSemesterByAccount() {
        LocalSemesterData semesterData = loadSemesters();
        LocalSemester s = semesterData.semesterMap.get(Session.currentAccount.getUsername());

        if (s == null) {
            s = new LocalSemester();
        }
        Session.semester = s.semester;
        return s.semester;
    }

    public static LocalSemesterData loadSemesters() {
        Gson gson = new Gson();
        LocalSemesterData data = gson.fromJson(getStringFromFile(new File(SEMESTER_FILE_NAME)), LocalSemesterData.class);
        if (data == null) {
            data = new LocalSemesterData();
        }
        return data;
    }

    public static void storeSemesters(LocalSemesterData data) {
        Gson gson = new Gson();
        writeStringToFile(gson.toJson(data), new File(SEMESTER_FILE_NAME));
    }

    public static void storeAccount(Account newAccount) {
        Set<Account> accounts = getAccountList();
        if (accounts == null) {
            accounts = new HashSet<>();
        }
        accounts.add(newAccount);

        Gson gson = new Gson();
        writeStringToFile(gson.toJson(accounts), new File(ACCOUNTS_FILE_NAME));
    }

    private static void writeStringToFile(String jsonStr, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            ByteArrayInputStream in = new ByteArrayInputStream(jsonStr.getBytes());

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                out.write(buffer, 0, len);
            }

            in.close();
            out.close();
        } catch (Exception e) {
        }
    }

    private static Set<Account> getAccountList() {
        File accountFile = new File(ACCOUNTS_FILE_NAME);
        Gson gson = new Gson();
        Set<Account> accounts = gson.fromJson(getStringFromFile(accountFile), new TypeToken<Set<Account>>() {
        }.getType());
        return accounts;
    }

    public static String getStringFromFile(File file) {
        try {
            StringBuffer sb = new StringBuffer();
            readToBuffer(sb, file);
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    public static void readToBuffer(StringBuffer buffer, File file) throws IOException {
        InputStream is = new FileInputStream(file);
        String line; 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); 
        while (line != null) { 
            buffer.append(line); 
            buffer.append("\n"); 
            line = reader.readLine();
        }
        reader.close();
        is.close();
    }

    public static boolean checkAccount(Account account) {
        Set<Account> accounts = getAccountList();
        return accounts.contains(account);
    }

    public static Semester loadSemesterFile(File file) {
        Gson gson = new Gson();
        Semester semester = gson.fromJson(getStringFromFile(file), Semester.class);
        return semester;
    }


}
