package com.hb.auth.util;

import java.lang.reflect.Field;

public class CloneObjectFields {
    public static void cloneNonNullProperties(Object source, Object target){
        try{
            Field[] fields = source.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
