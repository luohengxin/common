package com.cn.common.util.file;

import com.cn.common.util.check.AssertUtil;
import com.cn.common.util.exception.FileOperationException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static void copy(File file, String target) {
        AssertUtil.check(StringUtils.isEmpty(target), new FileOperationException("目标地址不能为空"));
        copy(file, new File(target));
    }

    public static void copy(File source, File target) {

        AssertUtil.check(null == source, new FileOperationException("源文件为null"));
        AssertUtil.check(null == target, new FileOperationException("目标文件为null"));
        try {
            FileInputStream fileInputStream = new FileInputStream(source);
            FileOutputStream fileOutputStream = new FileOutputStream(target);
            FileChannel sourceChannel = fileInputStream.getChannel();
            FileChannel targetChannel = fileOutputStream.getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean move(File source,String target){

        AssertUtil.check(null == source, new FileOperationException("源文件为null"));
        AssertUtil.check(!source.exists(), new FileOperationException("源文件不存在"));
        AssertUtil.check(StringUtils.isEmpty(target), new FileOperationException("目标地址不能为空"));
        File file = new File(target);
        AssertUtil.check(file.exists(), new FileOperationException("目标地址已存在文件不能移动"));
        return source.renameTo(file);

    }


    public static void main(String[] args) {
        move(new File("E:\\图片\\证件照.png"), "E:\\图片\\jj.png");
    }


}
