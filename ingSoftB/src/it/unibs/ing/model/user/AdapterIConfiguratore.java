package it.unibs.ing.model.user;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AdapterIConfiguratore implements JsonDeserializer<IConfiguratore>, JsonSerializer<IConfiguratore> {

    @Override
    public JsonElement serialize(IConfiguratore src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof Configuratore) {
            jsonObject.addProperty("type", "concreteConfiguratore");
        }
        return jsonObject;
    }

    @Override
    public IConfiguratore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("concreteConfiguratore".equals(type)) {
            return context.deserialize(jsonObject, Configuratore.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
