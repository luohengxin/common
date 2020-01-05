package com.cn.common.util.bean.entity;

import com.cn.common.util.bean.converter.ManConverter;
import com.cn.common.util.bean.BeanConvert;

@BeanConvert.NeedMapper
public class UserVO {

    private int id;

    @BeanConvert.FieldMapping("name")
    private String name2;

    @BeanConvert.FieldMapping(value = "sex",fieldConverter = ManConverter.class)
    private String isMan;

    private int age;


    @BeanConvert.FieldMapping("birthday")
    private String birthday2;

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name2='" + name2 + '\'' +
                ", isMan='" + isMan + '\'' +
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

    public String getIsMan() {
        return isMan;
    }

    public void setIsMan(String isMan) {
        this.isMan = isMan;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(String birthday2) {
        this.birthday2 = birthday2;
    }
}
