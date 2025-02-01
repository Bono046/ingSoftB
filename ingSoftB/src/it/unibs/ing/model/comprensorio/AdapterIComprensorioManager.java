package it.unibs.ing.model.comprensorio;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AdapterIComprensorioManager implements JsonDeserializer<IComprensorioManager>, JsonSerializer<IComprensorioManager> {

    @Override
    public JsonElement serialize(IComprensorioManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof ComprensorioManager) {
            jsonObject.addProperty("type", "comprensorioManager");
        }
        return jsonObject;
    }

    @Override
    public IComprensorioManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("comprensorioManager".equals(type)) {
            return context.deserialize(jsonObject, ComprensorioManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
