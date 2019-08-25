package com.spwang.luck.tenmillion.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * @author spwang on 2019-08-25 at 21:35
 * @version 1.0.0
 * @since 1.0.0
 */
public class ObjectDeserializer implements Deserializer<Object> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        Object readObject = null;
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream inputStream = new ObjectInputStream(in);
            readObject = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readObject;
    }

    @Override
    public void close() {
    }
}
