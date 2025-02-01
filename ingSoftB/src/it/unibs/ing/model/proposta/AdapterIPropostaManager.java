package it.unibs.ing.model.proposta;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


import java.lang.reflect.Type;

public class AdapterIPropostaManager implements JsonDeserializer<IPropostaManager>, JsonSerializer<IPropostaManager> {

    @Override
    public JsonElement serialize(IPropostaManager src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof PropostaManager) {
            jsonObject.addProperty("type", "propostaManager");
        }
        return jsonObject;
    }

    @Override
    public IPropostaManager deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("propostaManager".equals(type)) {
            return context.deserialize(jsonObject, PropostaManager.class);
        }
        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
