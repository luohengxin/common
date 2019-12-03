package com.cn.common.util.bean.converter;

import com.cn.common.util.bean.BeanConvert;

public class ManConverter implements BeanConvert.FieldConverter<Integer,String> {

    @Override
    public String convert(Integer integer) {
        return integer == 0 ? "是" : "否";
    }
}
