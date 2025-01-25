package it.unibs.ing.model;

public class Dati {

    private ConfiguratoreManager configuratoreManager;
    private FruitoreManager fruitoreManager;
    private ComprensorioManager comprensorioManager;
    private GerarchiaManager gerarchiaManager;
    private FattoreManager fattoreManager;
    private PropostaManager propostaManager;

    public Dati() {
        configuratoreManager = new ConfiguratoreManager();
        fruitoreManager = new FruitoreManager();
        comprensorioManager = new ComprensorioManager();
        gerarchiaManager = new GerarchiaManager();
        fattoreManager = new FattoreManager();
        propostaManager = new PropostaManager();
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
