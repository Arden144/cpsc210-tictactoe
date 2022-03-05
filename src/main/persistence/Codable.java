package persistence;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
                List<Object> list = new ArrayList<>();
                for (Object object : (JSONArray) value) {
                    list.add(decodeValue(cls.getComponentType(), object));
                }
                return list.toArray((T[]) Array.newInstance(cls.getComponentType(), 0));
            } else if (Codable.class.isAssignableFrom(cls)) {
                return Codable.decode((JSONObject) value, (Class) cls);
            } else {
                System.out.println(String.format("Cannot decode class %s", cls.getName()));
                return null;
            }
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Codable> T decode(JSONObject json, Class<T> cls)
            throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
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
        Constructor<T> ctor = cls.getConstructor(parameterTypes);
        return ctor.newInstance(parameters);
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
