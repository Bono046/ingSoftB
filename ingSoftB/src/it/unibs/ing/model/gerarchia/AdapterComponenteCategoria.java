package it.unibs.ing.model.gerarchia;
import com.google.gson.*;
import java.lang.reflect.Type;

public class AdapterComponenteCategoria implements JsonDeserializer<ICategoria>, JsonSerializer<ICategoria> {

    @Override
    public JsonElement serialize(ICategoria src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        if (src instanceof Categoria) {
            jsonObject.addProperty("type", "categoria");
        } else if (src instanceof CategoriaFoglia) {
            jsonObject.addProperty("type", "foglia");
        }
        return jsonObject;
    }

    @Override
    public ICategoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        if ("categoria".equals(type)) {
            return context.deserialize(jsonObject, Categoria.class);
        } else if ("foglia".equals(type)) {
            return context.deserialize(jsonObject, CategoriaFoglia.class);
        }

        throw new JsonParseException("Tipo sconosciuto: " + type);
    }
}
