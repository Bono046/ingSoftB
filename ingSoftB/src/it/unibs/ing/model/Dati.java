package it.unibs.ing.model;

import it.unibs.ing.model.comprensorio.ComprensorioManager;
import it.unibs.ing.model.fattore.FattoreManager;
import it.unibs.ing.model.fattore.IFattoreManager;
import it.unibs.ing.model.gerarchia.GerarchiaManager;
import it.unibs.ing.model.gerarchia.IGerarchiaManager;
import it.unibs.ing.model.proposta.IPropostaManager;
import it.unibs.ing.model.proposta.PropostaManager;
import it.unibs.ing.model.user.ConfiguratoreManager;
import it.unibs.ing.model.user.FruitoreManager;
import it.unibs.ing.model.user.IConfiguratoreManager;
import it.unibs.ing.model.user.IFruitoreManager;
import it.unibs.ing.model.comprensorio.IComprensorioManager;

public class Dati {

    private static Dati instance;

    private IConfiguratoreManager configuratoreManager;
    private IFruitoreManager fruitoreManager;
    private IComprensorioManager comprensorioManager;
    private IGerarchiaManager gerarchiaManager;
    private IFattoreManager fattoreManager;
    private IPropostaManager propostaManager;

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

    public IConfiguratoreManager getConfiguratoreManager() {
        return configuratoreManager;
    }

    public IFruitoreManager getFruitoreManager() {
        return fruitoreManager;
    }

    public IComprensorioManager getComprensorioManager() {
        return comprensorioManager;
    }

    public IGerarchiaManager getGerarchiaCategorieManager() {
        return gerarchiaManager;
    }

    public IFattoreManager getFattoreManager() {
        return fattoreManager;
    }

    public IPropostaManager getPropostaManager() {
        return propostaManager;
    }
}