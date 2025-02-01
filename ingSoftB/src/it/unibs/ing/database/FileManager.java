package it.unibs.ing.database;

import com.google.gson.*;
import it.unibs.ing.model.Dati;
import it.unibs.ing.model.comprensorio.AdapterIComprensorio;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.fattore.IFattore;
import it.unibs.ing.model.fattore.IFattoreManager;
import it.unibs.ing.model.gerarchia.AdapterComponenteCategoria;
import it.unibs.ing.model.gerarchia.AdapterIGerarchia;
import it.unibs.ing.model.gerarchia.AdapterIGerarchiaManager;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.gerarchia.IGerarchia;
import it.unibs.ing.model.gerarchia.IGerarchiaManager;
import it.unibs.ing.model.proposta.AdapterChiusuraStrategy;
import it.unibs.ing.model.proposta.AdapterIProposta;
import it.unibs.ing.model.proposta.AdapterIPropostaManager;
import it.unibs.ing.model.proposta.IChiusuraProposteStrategy;
import it.unibs.ing.model.proposta.IProposta;
import it.unibs.ing.model.proposta.IPropostaManager;
import it.unibs.ing.model.user.AdapterIConfiguratore;
import it.unibs.ing.model.user.AdapterIConfiguratoreManager;
import it.unibs.ing.model.user.AdapterIFruitore;
import it.unibs.ing.model.user.AdapterIFruitoreManager;
import it.unibs.ing.model.user.IConfiguratore;
import it.unibs.ing.model.user.IConfiguratoreManager;
import it.unibs.ing.model.user.IFruitore;
import it.unibs.ing.model.user.IFruitoreManager;
import it.unibs.ing.model.comprensorio.AdapterIComprensorioManager;
import it.unibs.ing.model.comprensorio.IComprensorioManager;
import it.unibs.ing.model.fattore.AdapterIFattore;
import it.unibs.ing.model.fattore.AdapterIFattoreManager;

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
                .registerTypeAdapter(IConfiguratore.class, new AdapterIConfiguratore())
                .registerTypeAdapter(IFruitore.class, new AdapterIFruitore())
                .registerTypeAdapter(IConfiguratoreManager.class, new AdapterIConfiguratoreManager())
                .registerTypeAdapter(IFattoreManager.class, new AdapterIFattoreManager())
                .registerTypeAdapter(IGerarchiaManager.class, new AdapterIGerarchiaManager())
                .registerTypeAdapter(IPropostaManager.class, new AdapterIPropostaManager())
                .registerTypeAdapter(IFruitoreManager.class, new AdapterIFruitoreManager())
                .registerTypeAdapter(IComprensorioManager.class, new AdapterIComprensorioManager()) 
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
                .registerTypeAdapter(IConfiguratore.class, new AdapterIConfiguratore())
                .registerTypeAdapter(IFruitore.class, new AdapterIFruitore())
                .registerTypeAdapter(IConfiguratoreManager.class, new AdapterIConfiguratoreManager())
                .registerTypeAdapter(IFattoreManager.class, new AdapterIFattoreManager())
                .registerTypeAdapter(IGerarchiaManager.class, new AdapterIGerarchiaManager())
                .registerTypeAdapter(IPropostaManager.class, new AdapterIPropostaManager())
                .registerTypeAdapter(IFruitoreManager.class, new AdapterIFruitoreManager())
                .registerTypeAdapter(IComprensorioManager.class, new AdapterIComprensorioManager()) 
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






    


