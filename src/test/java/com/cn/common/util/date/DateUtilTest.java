package com.cn.common.util.date;

import java.text.ParseException;
import java.util.Date;

public class DateUtilTest {
    public static void main(String[] args) {

        System.out.println(DateFormatUtil.format(new Date(), "yyyy-MM-dd"));

        Thread thread1 = new Thread(() -> {
            System.out.println("***************start**************");

            for (int i = 10; i <= 31; i++) {
                try {
                    Date date = DateFormatUtil.parse("2019-10-" + i, "yyyy-MM-dd");
                    System.out.println(Thread.currentThread().getName()+" : "+date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("***************end***************");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("***************start**************");

            for (int i = 10; i <= 31; i++) {
                try {
                    Date date = DateFormatUtil.parse("2019/10/" + i, "yyyy/MM/dd");
                    System.out.println(Thread.currentThread().getName()+" : "+date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("***************end***************");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("***************start**************");

            for (int i = 10; i <= 31; i++) {
                try {
                    Date date = DateFormatUtil.parse("201910" + i, "yyyyMMdd");
                    System.out.println(Thread.currentThread().getName()+" : "+date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("***************end***************");
        });

        thread1.setName("thread1");
        thread2.setName("thread2");
        thread3.setName("thread3");
        thread1.start();
        thread2.start();
        thread3.start();


    }

}
