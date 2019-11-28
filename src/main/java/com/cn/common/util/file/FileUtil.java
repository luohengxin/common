package com.cn.common.util.file;

import com.cn.common.util.check.CheckUtil;
import com.cn.common.util.exception.FileOperationException;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static void copy(File file, String target) {
        CheckUtil.check(StringUtils.isEmpty(target), new FileOperationException("目标地址不能为空"));
        copy(file, new File(target));
    }

    public static void copy(File source, File target) {

        CheckUtil.check(null == source, new FileOperationException("源文件为null"));
        CheckUtil.check(null == target, new FileOperationException("目标文件为null"));
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


    public static void main(String[] args) {
        copy(new File("E:\\图片\\证件照.png"), "E:\\图片\\jj.png");
    }


}
