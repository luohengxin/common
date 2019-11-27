package com.cn.common.util.bean;

import com.cn.common.util.date.DateFormatUtil;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;


/**
 * cglib 类型转换工具
 */
public class BeanConvert {

    public static void transfer(Object source,Object target,boolean defaultConverter){

        if(defaultConverter){
            transfer(source,target,new DefaultConverter());
        }else {
            BeanCopier copier = BeanCopier.create(source.getClass(),target.getClass(),false);
            copier.copy(source,target,null);
            mapping(source,target,null);

        }

    }


    public static void transfer(Object source,Object target,Converter converter){
        BeanCopier copier = BeanCopier.create(source.getClass(),target.getClass(),true);
        copier.copy(source,target,converter);
        mapping(source,target,converter);
    }


    private static void mapping(Object source,Object target,Converter converter){
        if(target.getClass().isAnnotationPresent(NeedMapper.class)){
            Field[] declaredFields = target.getClass().getDeclaredFields();
            for (Field field: declaredFields){
                FieldMapping annotation = field.getAnnotation(FieldMapping.class);
                if(null != annotation){
                    String value = annotation.value();
                    if("".equals(value)){
                        continue;
                    }
                    try {
                        Field sField = source.getClass().getDeclaredField(value);
                        sField.setAccessible(true);
                        Object o = sField.get(source);
                        field.setAccessible(true);
                        if(null != converter){
                            Object convert = converter.convert(o, field.getType(), null);
                            field.set(target,convert);
                        }else {
                            field.set(target,o);
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    public static class DefaultConverter implements Converter{

        @Override
        public Object convert(Object o, Class aClass, Object o1) {

            if(o instanceof Date && aClass == String.class){
                return DateFormatUtil.format((Date) o,pattern(),locale());
            }
            if(o instanceof String && aClass == Date.class){
                try {
                    return DateFormatUtil.parse((String) o,pattern(),locale());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
            else if(o instanceof Number && aClass == String.class){
                return String.valueOf(o);
            }

            return o;
        }

        public String pattern() {
            return DateFormatUtil.Y_M_D1;
        }

        public Locale locale() {
            return Locale.getDefault(Locale.Category.FORMAT);
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NeedMapper{
    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldMapping{

        String value() default "";
    }

}
