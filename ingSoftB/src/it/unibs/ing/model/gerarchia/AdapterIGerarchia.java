package it.unibs.ing.model.gerarchia;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class AdapterIGerarchia  implements JsonDeserializer<IGerarchia>, JsonSerializer<IGerarchia> {

    @Override
    public JsonElement serialize(IGerarchia src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof GerarchiaCategorie) {
            jsonObject.addProperty("type", "gerarchia");
        }
        return jsonObject;
    }

    @Override
    public IGerarchia deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("gerarchia".equals(type)) {
            return context.deserialize(jsonObject, GerarchiaCategorie.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    }
}

