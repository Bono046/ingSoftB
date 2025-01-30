package it.unibs.ing.model;

import it.unibs.ing.model.comprensorio.ComprensorioManager;
import it.unibs.ing.model.fattore.FattoreManager;
import it.unibs.ing.model.gerarchia.GerarchiaManager;
import it.unibs.ing.model.proposta.PropostaManager;
import it.unibs.ing.model.user.ConfiguratoreManager;
import it.unibs.ing.model.user.FruitoreManager;

public class Dati {

    private static Dati instance;

    private ConfiguratoreManager configuratoreManager;
    private FruitoreManager fruitoreManager;
    private ComprensorioManager comprensorioManager;
    private GerarchiaManager gerarchiaManager;
    private FattoreManager fattoreManager;
    private PropostaManager propostaManager;

    private Dati() {
        configuratoreManager = new ConfiguratoreManager();
        fruitoreManager = new FruitoreManager();
        comprensorioManager = new ComprensorioManager();
        gerarchiaManager = new GerarchiaManager();
        fattoreManager = new FattoreManager();
        propostaManager = new PropostaManager();
    }

    public static Dati getInstance() {
        if (instance == null) {
            instance = new Dati();
        }
        return instance;
    }

    public ConfiguratoreManager getConfiguratoreManager() {
        return configuratoreManager;
    }

    public FruitoreManager getFruitoreManager() {
        return fruitoreManager;
    }

    public ComprensorioManager getComprensorioManager() {
        return comprensorioManager;
    }

    public GerarchiaManager getGerarchiaCategorieManager() {
        return gerarchiaManager;
    }

    public FattoreManager getFattoreManager() {
        return fattoreManager;
    }

    public PropostaManager getPropostaManager() {
        return propostaManager;
    }
}