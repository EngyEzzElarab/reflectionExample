package main;

import java.util.Date;

import static java.lang.reflect.AccessibleObject.setAccessible;

public class Student {
    public String name;
    public int age;
    protected Date joinDate;
    private boolean isEnrolled;


    public Student(String n, int age, Date date, boolean isEnrolled) {
        this.name = n;
        this.age = age;
        this.joinDate = date;
        this.isEnrolled = isEnrolled;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public boolean getIsEnrolled() {
        return isEnrolled;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
