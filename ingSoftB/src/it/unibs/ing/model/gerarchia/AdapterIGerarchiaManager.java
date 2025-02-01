package it.unibs.ing.model.gerarchia;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AdapterIGerarchiaManager implements JsonDeserializer<IGerarchiaManager>, JsonSerializer<IGerarchiaManager> {

    @Override
    public JsonElement serialize(IGerarchiaManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof GerarchiaManager) {
            jsonObject.addProperty("type", "GerarchiaManager");
        }
        return jsonObject;
    }

    @Override
    public IGerarchiaManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("GerarchiaManager".equals(type)) {
            return context.deserialize(jsonObject, GerarchiaManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
    