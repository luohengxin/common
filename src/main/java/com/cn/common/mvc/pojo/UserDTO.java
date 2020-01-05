package com.cn.common.mvc.pojo;

import com.cn.common.mvc.http.valid.SexConstraint;

import javax.validation.constraints.NotNull;

public class UserDTO {

    private int id;


    @NotNull
    private String name;

    @SexConstraint(message = "性别不匹配")
    private String sex;
    @NotNull
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
