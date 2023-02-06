package ru.ialexdm.springapp.models;

import java.util.ArrayList;
import java.util.List;

public class Reader {
    private Integer id;
    private String fullName;
    private Integer age;
    public Reader(){
    }

    public Reader(Integer id, String fullName, Integer age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
