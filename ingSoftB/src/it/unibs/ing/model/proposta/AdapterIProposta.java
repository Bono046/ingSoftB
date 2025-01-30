package it.unibs.ing.model.proposta;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AdapterIProposta implements JsonDeserializer<IProposta>, JsonSerializer<IProposta> {

    @Override
    public JsonElement serialize(IProposta src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof Proposta) {
            jsonObject.addProperty("type", "proposta");
        }
        return jsonObject;
    }

    @Override
    public IProposta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("proposta".equals(type)) {
            return context.deserialize(jsonObject, Proposta.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}

    

