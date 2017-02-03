package com.ahmadrosid.lib.baseapp.helper.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class ArrayOfObjectsToMapDeserializer
        implements JsonDeserializer<Map<String, String>> {

    public Map<String, String> deserialize(JsonElement json, Type typeOfT,
                                           JsonDeserializationContext context) throws JsonParseException {
        Map<String, String> result = new HashMap<String, String>();

        JsonArray array = json.getAsJsonArray();
        for (JsonElement element : array) {
            JsonObject object = element.getAsJsonObject();
            // This does not check if the objects only have one property, so JSON
            // like [{a:b,c:d}{e:f}] will become a Map like {a:b,c:d,e:f} as well.
            for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue().getAsString();
                result.put(key, value);
            }
        }
        return result;
    }
}