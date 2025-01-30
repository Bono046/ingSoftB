package it.unibs.ing.model.proposta;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;


public class AdapterChiusuraStrategy implements JsonDeserializer<IChiusuraProposteStrategy>, JsonSerializer<IChiusuraProposteStrategy> {

    @Override
    public JsonElement serialize(IChiusuraProposteStrategy src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof ConcreteStrategyProposte) {
            jsonObject.addProperty("type", "concreteStrategy");
        }
        return jsonObject;
    }

    @Override
    public IChiusuraProposteStrategy deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("concreteStrategy".equals(type)) {
            return context.deserialize(jsonObject, ConcreteStrategyProposte.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}

