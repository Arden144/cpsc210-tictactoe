package persistence;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Codable {
    private static <T> Object decodeValue(Class<T> cls, Object value) {
        try {
            if (cls.isPrimitive()) {
                return value;
            } else if (cls.isEnum()) {
                return Enum.valueOf((Class) cls, (String) value);
            } else if (cls.isArray()) {
                return StreamSupport.stream(((JSONArray) value).spliterator(), false)
                        .map(e -> decodeValue(cls.getComponentType(), e))
                        .collect(Collectors.toList())
                        .toArray((T[]) Array.newInstance(cls.getComponentType(), 0));
            } else if (Codable.class.isAssignableFrom(cls)) {
                return Codable.decode((JSONObject) value, (Class) cls);
            } else {
                return null;
            }
        } catch (SecurityException | IllegalArgumentException e) {
            return null;
        }
    }

    public static <T extends Codable> T decode(JSONObject json, Class<T> cls) {
        try {
            Field[] fields = cls.getDeclaredFields();
            Object[] parameters = Arrays.stream(fields)
                    .filter(e -> !e.isSynthetic())
                    .map(e -> decodeValue(e.getType(), json.get(e.getName())))
                    .toArray();
            Class<?>[] parameterTypes = Arrays.stream(fields)
                    .filter(e -> !e.isSynthetic())
                    .map(Field::getType)
                    .collect(Collectors.toList())
                    .toArray(new Class<?>[0]);
            return cls.getConstructor(parameterTypes).newInstance(parameters);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | SecurityException e) {
            return null;
        }
    }

    private static Object encodeValue(Class<?> cls, Object object) {
        if (cls.isPrimitive()) {
            return object;
        } else if (cls.isEnum()) {
            return ((Enum<?>) object).name();
        } else if (cls.isArray()) {
            return new JSONArray(Arrays.stream((Object[]) object)
                    .map(e -> encodeValue(cls.getComponentType(), e))
                    .toArray());
        } else if (Codable.class.isAssignableFrom(cls)) {
            return ((Codable) object).encode();
        } else {
            return null;
        }
    }

    private Object encodeField(Field field) {
        try {
            return encodeValue(field.getType(), field.get(this));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            return null;
        }
    }

    public JSONObject encode() {
        return new JSONObject(Arrays.stream(getClass().getDeclaredFields())
                .filter(e -> !e.isSynthetic())
                .peek(e -> e.setAccessible(true))
                .collect(Collectors.toMap(Field::getName, this::encodeField)));
    }
}
