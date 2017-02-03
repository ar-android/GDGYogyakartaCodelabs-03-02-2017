package com.ahmadrosid.lib.baseapp.helper.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by ocittwo on 2/3/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class BaseJson {

    public Gson getParser(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(
                new TypeToken<Map<String, String>>() {}.getType(),
                new ArrayOfObjectsToMapDeserializer());
        return builder.create();
    }
}
