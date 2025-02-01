package it.unibs.ing.model.fattore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AdapterIFattoreManager implements JsonDeserializer<IFattoreManager>, JsonSerializer<IFattoreManager> {

    @Override
    public JsonElement serialize(IFattoreManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof FattoreManager) {
            jsonObject.addProperty("type", "fattoreManager");
        }
        return jsonObject;
    }

    @Override
    public IFattoreManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("fattoreManager".equals(type)) {
            return context.deserialize(jsonObject, FattoreManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
