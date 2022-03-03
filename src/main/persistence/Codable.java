package persistence;

import java.lang.reflect.Field;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Codable {
    public static <T extends Codable> T decode(String data) {
        return null;
    }

    private static Object reflectToJson(Class<?> cls, Object object)
            throws IllegalArgumentException, IllegalAccessException {
        if (cls.isPrimitive()) {
            return object;
        } else if (cls.isEnum()) {
            return ((Enum<?>) object).name();
        } else if (cls.isArray()) {
            JSONArray array = new JSONArray();
            for (Object component : (Object[]) object) {
                array.put(reflectToJson(cls.getComponentType(), component));
            }
            return array;
        } else if (Codable.class.isAssignableFrom(cls)) {
            return ((Codable) object).encode();
        } else {
            return null;
        }
    }

    public JSONObject encode() throws IllegalArgumentException, IllegalAccessException {
        JSONObject json = new JSONObject();
        Class<?> cls = getClass();
        for (Field field : cls.getDeclaredFields()) {
            if (!field.isSynthetic()) {
                field.setAccessible(true);
                json.put(field.getName(), reflectToJson(field.getType(), field.get(this)));
            }
        }
        return json;
    }
}
