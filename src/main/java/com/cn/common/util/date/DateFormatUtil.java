package com.cn.common.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateFormatUtil {


    public static final String Y_M_D1 = "yyyy-MM-dd";

    public static final String Y_M_D2 = "yyyy/MM/dd";

    private final static Object LOCK = new Object();


    private final static Map<String,ThreadLocal<SimpleDateFormat>> sdfCache = new HashMap<>();



    private static SimpleDateFormat getSimpleDateFormat(String pattern,Locale locale){
        String key = pattern.concat(locale.getDisplayName());
        ThreadLocal<SimpleDateFormat> threadLocal = sdfCache.get(key);
        if(null == threadLocal){
            synchronized (LOCK){
                threadLocal = sdfCache.get(key);
                if(null == threadLocal ){
                    threadLocal = ThreadLocal.withInitial(()->
                       new SimpleDateFormat(pattern,locale)
                    );
                    sdfCache.put(key,threadLocal);
                }
            }
        }

        return threadLocal.get();
    }

    public static String format(Date date,String pattern){
        return format(date,pattern,Locale.getDefault(Locale.Category.FORMAT));
    }

    public static String format(Date date,String pattern,Locale locale){
        return getSimpleDateFormat(pattern,locale).format(date);
    }



    public static Date parse(String source,String pattern) throws ParseException {
        return parse(source,pattern,Locale.getDefault(Locale.Category.FORMAT));
    }

    public static Date parse(String source,String pattern,Locale locale) throws ParseException {
        return getSimpleDateFormat(pattern,locale).parse(source);
    }


}
