package it.unibs.ing.model.user;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AdapterIConfiguratoreManager implements JsonDeserializer<IConfiguratoreManager>, JsonSerializer<IConfiguratoreManager> {

    @Override
    public JsonElement serialize(IConfiguratoreManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof ConfiguratoreManager) {
            jsonObject.addProperty("type", "configuratoreManager");
        }
        return jsonObject;
    }

    @Override
    public IConfiguratoreManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("configuratoreManager".equals(type)) {
            return context.deserialize(jsonObject, ConfiguratoreManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
