package com.cn.common.util.bean.entity;

import com.cn.common.util.bean.BeanConvert;

import java.util.Date;

@BeanConvert.NeedMapper
public class UserVO {

    private int id;

    @BeanConvert.FieldMapping("name")
    private String name2;

    private String sex2;

    private int age;


    @BeanConvert.FieldMapping("birthday")
    private String birthday2;

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name2='" + name2 + '\'' +
                ", sex2='" + sex2 + '\'' +
                ", age=" + age +
                ", birthday2='" + birthday2 + '\'' +
                '}';
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getSex2() {
        return sex2;
    }

    public void setSex2(String sex2) {
        this.sex2 = sex2;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
