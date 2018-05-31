package com.khwela.khwelacore.commons;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class DateDeSerializerAdaper implements JsonDeserializer {

    @Override
    public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        System.out.println("\n \n Deserialize "+json.getAsJsonPrimitive());

        System.out.println("\n \n as json "+LocalDate.parse(json.getAsString()));

        return LocalDate.parse(json.getAsString());
    }
}

