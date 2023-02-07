package ru.ialexdm.springapp.models;

import jakarta.validation.constraints.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reader {
    private Integer id;
    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2,max = 128, message = "Field should be greater than 2 and less than 128 letters")
    private String fullName;
    @Min(value = 0, message = "Age should be greater than 0 and less than 128")
    @Max(value = 128, message = "Age should be greater than 0 and less than 128")
    @NotNull(message = "Field should not be empty")
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
