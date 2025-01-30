package it.unibs.ing.model.fattore;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;


public class AdapterIFattore implements JsonDeserializer<IFattore>, JsonSerializer<IFattore> {

    @Override
    public JsonElement serialize(IFattore src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof FattoreConversione) {
            jsonObject.addProperty("type", "fattore");
        }
        return jsonObject;
    }

    @Override
    public IFattore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("fattore".equals(type)) {
            return context.deserialize(jsonObject, FattoreConversione.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
