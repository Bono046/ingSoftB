package it.unibs.ing.model.user;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AdapterIFruitoreManager implements JsonDeserializer<IFruitoreManager>, JsonSerializer<IFruitoreManager> {

    @Override
    public JsonElement serialize(IFruitoreManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof FruitoreManager) {
            jsonObject.addProperty("type", "fruitoreManager");
        }
        return jsonObject;
    }

    @Override
    public IFruitoreManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("fruitoreManager".equals(type)) {
            return context.deserialize(jsonObject, FruitoreManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
