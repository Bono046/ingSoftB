package it.unibs.ing.database;

import com.google.gson.*;

import it.unibs.ing.model.Dati;
import it.unibs.ing.model.comprensorio.AdapterIComprensorio;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.fattore.IFattore;
import it.unibs.ing.model.gerarchia.AdapterComponenteCategoria;
import it.unibs.ing.model.gerarchia.AdapterIGerarchia;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.gerarchia.IGerarchia;
import it.unibs.ing.model.proposta.AdapterChiusuraStrategy;
import it.unibs.ing.model.proposta.AdapterIProposta;
import it.unibs.ing.model.proposta.IChiusuraProposteStrategy;
import it.unibs.ing.model.proposta.IProposta;
import it.unibs.ing.model.fattore.AdapterIFattore;

import java.io.*;


public class FileManager {

    private static final String FILE_DATI = "dati.json";

    public static void salvaDati(Dati dati) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(ICategoria.class, new AdapterComponenteCategoria())
                .registerTypeAdapter(IComprensorio.class, new AdapterIComprensorio())
                .registerTypeAdapter(IFattore.class, new AdapterIFattore())
                .registerTypeAdapter(IGerarchia.class, new AdapterIGerarchia())
                .registerTypeAdapter(IProposta.class, new AdapterIProposta())
                .registerTypeAdapter(IChiusuraProposteStrategy.class, new AdapterChiusuraStrategy())
                .create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_DATI))) {
            writer.write(gson.toJson(dati));
        }
    }

    public static Dati caricaDati() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ICategoria.class, new AdapterComponenteCategoria())
                .registerTypeAdapter(IComprensorio.class, new AdapterIComprensorio())
                .registerTypeAdapter(IFattore.class, new AdapterIFattore())
                .registerTypeAdapter(IGerarchia.class, new AdapterIGerarchia())
                .registerTypeAdapter(IProposta.class, new AdapterIProposta())
                .registerTypeAdapter(IChiusuraProposteStrategy.class, new AdapterChiusuraStrategy())
                .create();
        File file = new File(FILE_DATI);
        if (!file.exists() || file.length() == 0) {
            return Dati.getInstance();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return gson.fromJson(reader, Dati.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalStateException("Errore di sintassi JSON", e);
        }
    }
}






    


