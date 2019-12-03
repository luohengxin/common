package com.cn.common.util.check;

public class AssertUtil {

    public static void check(boolean result,RuntimeException e){
        if(!result){
            throw e;
        }
    }

}
