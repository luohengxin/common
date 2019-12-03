package com.cn.common.util.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoFactory;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializer {

    private static final ThreadLocal<Kryo> kryos = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.setReferences(true);

        kryo.setRegistrationRequired(false);

        ((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
        return kryo;
    });


    private static KryoFactory factory = new KryoFactory() {
        @Override
        public Kryo create() {
            Kryo kryo = new Kryo();

            kryo.setReferences(true);


            kryo.setRegistrationRequired(false);

            ((Kryo.DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                    .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };

    public static byte[] kryoSeriAsByte(Kryo kryo, Class<?> c) {

        try (ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            kryo.writeClassAndObject(output, c);
            output.flush();
            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static <T> T kryoSeriAsByte(byte[] bytes, Kryo kryo) {

        try (ByteArrayInputStream byteArrayInputStream = new
                ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Object o = kryo.readClassAndObject(input);
            return (T) o;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
