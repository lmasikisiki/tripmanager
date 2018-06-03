package com.khwela.khwelacore.commons;

import com.google.gson.*;
import org.apache.tomcat.jni.Local;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class DateSerializerAdaper implements JsonSerializer {

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        return o==null ? null: new JsonPrimitive(o.toString());
    }
}

