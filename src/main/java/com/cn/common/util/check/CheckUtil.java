package com.cn.common.util.check;

public class CheckUtil {


    public static void check(boolean result,RuntimeException e){
        if(result){
            throw e;
        }
    }

}
