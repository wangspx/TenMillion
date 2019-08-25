package com.spwang.luck.tenmillion.kafka;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @author spwang on 2019-08-25 at 21:34
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObjectSerializer implements Serializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = new byte[0];
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(byteArray);
            outputStream.writeObject(o);
            outputStream.flush();
            bytes = byteArray.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArray.close();
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public void close() {
    }
}
