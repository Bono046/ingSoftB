package it.unibs.ing.model;

public class Dati {

    private ConfiguratoreManager configuratoreManager;
    private FruitoreManager fruitoreManager;
    private ComprensorioManager comprensorioManager;
    private GerarchiaManager gerarchiaManager;

    public Dati() {
        configuratoreManager = new ConfiguratoreManager();
        fruitoreManager = new FruitoreManager();
        comprensorioManager = new ComprensorioManager();
        gerarchiaManager = new GerarchiaManager();
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
        
}
