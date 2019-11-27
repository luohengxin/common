package com.cn.common.util.bean;

import com.cn.common.util.bean.entity.UserDto;
import com.cn.common.util.bean.entity.UserVO;

import java.util.Date;

public class BeanConvertTest {

    public static void main(String[] args) {
        UserDto dto = new UserDto(1,"1","1",23,new Date());
        UserVO vo = new UserVO();
        BeanConvert.transfer(dto,vo,true);
        System.out.println(vo);
    }

}
