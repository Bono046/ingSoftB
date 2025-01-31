package it.unibs.ing.model.user;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
public class AdapterIFruitore implements JsonDeserializer<IFruitore>, JsonSerializer<IFruitore> {

    @Override
    public JsonElement serialize(IFruitore src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof Fruitore) {
            jsonObject.addProperty("type", "concreteFruitore");
        }
        return jsonObject;
    }

    @Override
    public IFruitore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("concreteFruitore".equals(type)) {
            return context.deserialize(jsonObject, Fruitore.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    } 
}
