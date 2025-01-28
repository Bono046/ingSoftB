package it.unibs.ing.model;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;

public class FileManager {

    private static final String FILE_DATI = "dati.json";

    public static void salvaDati(Dati dati) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ComponenteCategoria.class, new ComponenteCategoriaAdapter()) // Registra l'adapter
                .create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DATI))) {
            writer.write(gson.toJson(dati));
        }
    }

    public static Dati caricaDati() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ComponenteCategoria.class, new ComponenteCategoriaAdapter()) // Registra l'adapter
                .create();
        File file = new File(FILE_DATI);
        if (!file.exists() || file.length() == 0) {
            return new Dati();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader, Dati.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException("Errore di sintassi JSON", e);
        }
    }

    // Adapter personalizzato per ComponenteCategoria
    private static class ComponenteCategoriaAdapter implements JsonDeserializer<ComponenteCategoria>, JsonSerializer<ComponenteCategoria> {

        @Override
        public JsonElement serialize(ComponenteCategoria src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = context.serialize(src).getAsJsonObject();
            if (src instanceof Categoria) {
                jsonObject.addProperty("type", "categoria");
            } else if (src instanceof CategoriaFoglia) {
                jsonObject.addProperty("type", "foglia");
            }
            return jsonObject;
        }

        @Override
        public ComponenteCategoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
}
