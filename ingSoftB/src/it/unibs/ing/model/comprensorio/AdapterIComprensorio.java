package it.unibs.ing.model.comprensorio;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AdapterIComprensorio implements JsonDeserializer<IComprensorio>, JsonSerializer<IComprensorio> {

    @Override
    public JsonElement serialize(IComprensorio src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof Comprensorio) {
            jsonObject.addProperty("type", "concreteComprensorio");
        }
        return jsonObject;
    }

    @Override
    public IComprensorio deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("concreteComprensorio".equals(type)) {
            return context.deserialize(jsonObject, Comprensorio.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
