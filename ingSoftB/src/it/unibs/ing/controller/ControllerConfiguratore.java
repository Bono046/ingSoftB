package it.unibs.ing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import it.unibs.ing.model.*;
import it.unibs.ing.view.ViewConfiguratore;



public class ControllerConfiguratore extends ControllerBase {

    private Dati dati;
    ViewConfiguratore view;

    public ControllerConfiguratore(Dati dati) {
        super(dati); 
        this.dati = dati;
    }

    public void registraView(ViewConfiguratore view) {
        this.view = view;
    }

    public Boolean verificaCredenzialiPrimoAccesso(String usernamePredefinito, String passwordPredefinita) {
        return Configuratore.verificaPrimoAccesso(usernamePredefinito, passwordPredefinita);
    }

    public Boolean userOk(String username) {
        return dati.getConfiguratoreManager().userValido(username);
    }

    public void registraConfiguratore(String username, String password) {
        Configuratore conf = new Configuratore(username, password);
        dati.getConfiguratoreManager().addToListaConfiguratori(conf);
    }

    public Boolean loginConfiguratore(String username, String password) {
        return dati.getConfiguratoreManager().loginConfiguratore(username, password);
    }

    public Dati getDati() {
        return dati;
    }

    public void aggiungiComprensorio(String nome, Set<String> comuni) {
        ComprensorioGeografico comprensorio = new ComprensorioGeografico(nome, comuni);
        dati.getComprensorioManager().addComprensorio(comprensorio);
    }

    public boolean checkNomeGerarchia(String nome) {
        return dati.getGerarchiaCategorieManager().checkNomeGerarchia(nome);
    }

    public void addGerarchia(GerarchiaCategorie g) {
        dati.getGerarchiaCategorieManager().addGerarchia(g);
    }

    public Categoria creaCategoria(boolean isRadice, Categoria c) {
    String nome = isRadice ? view.getNomeRadice() : getNomeCategoriaValido(c);
    String campo = view.leggiCampo();
    HashMap<String, String> dominio = view.leggiDominio();
    return new Categoria(nome, campo, dominio);
    }

    public CategoriaFoglia creaCategoriaFoglia(Categoria radice) {
        String nome = getNomeCategoriaValido(radice); 		
        CategoriaFoglia categoria = new CategoriaFoglia(nome);
        return categoria;
    }

    public String getNomeCategoriaValido(Categoria c) {
        boolean nomeValido = false;
        String nome=""; 
        while(!nomeValido) {
            nome = view.getNomeCategoria();
            nomeValido = c.isNomeUnivoco(nome);
            if(!nomeValido) 
                view.nomeNonValido();
        }
        return nome;
    }

    public void inizializzaGerarchia(){
        Categoria radice = creaCategoria(true, null);
        GerarchiaCategorie g = new GerarchiaCategorie(radice);
        componiGerarchia(g, radice);
    }

    public void componiGerarchia(GerarchiaCategorie g, Categoria padre) {		
    	
    	ArrayList<String> listaDominio = new ArrayList<>(padre.getDominio().keySet());
        Categoria radice = g.getCategoriaRadice();
    	
    	for (String dom : listaDominio) {

            int choice1 = view.stampaSceltaSottocategoria(dom);
            
            switch(choice1) {
                case 1:
                    Categoria sottocat = creaCategoria(false, radice);
                    padre.aggiungiSottocategoria(dom, sottocat);
                    HashMap<String, Categoria> sottocategorie = padre.getSottocategorie();
                    componiGerarchia(g, sottocategorie.get(dom));
                    break;
                case 2:
                    CategoriaFoglia sottocatF = creaCategoriaFoglia(radice);
                    padre.aggiungiSottocategoria(dom, sottocatF);
                    g.addToListaFoglie(sottocatF);
                    break;
                default:
                    view.logErroreGenerico();
            }
        }
        dati.getGerarchiaCategorieManager().addGerarchia(g);
    }


    //probabilmente da spostare in ControllerBase
    public boolean listaGerarchiaNonVuota() {
        if (dati.getGerarchiaCategorieManager().getListaRadici().isEmpty()) {
            view.logErroreGerarchia();
            return false;
        } 
        return true;
    }

    //probabilmente da spostare in ControllerBase
    public void visualizzaGerarchie() {
        if (listaGerarchiaNonVuota()) {
            for (Categoria gerarchia : dati.getGerarchiaCategorieManager().getListaRadici()) {
                view.stampaAlbero("", gerarchia);
            }
        }
    }

    //probabilmente da spostare in ControllerBase
    public GerarchiaCategorie sceltaRadice() {
        ArrayList<GerarchiaCategorie> listaOggettiGerarchia = dati.getGerarchiaCategorieManager().getListaOggettiGerarchia();

        if (listaGerarchiaNonVuota()) {
            List<String> nomiGerarchie = new ArrayList<>();
            for (GerarchiaCategorie gerarchia : listaOggettiGerarchia) {
                nomiGerarchie.add(gerarchia.getCategoriaRadice().getNome());
            }
            int scelta = view.selezionaGerarchia(nomiGerarchie);

            return listaOggettiGerarchia.get(scelta - 1);
        }
        return null;
    }

    

    public void setFattoriConversioneGerarchia() {
        
        if (!listaGerarchiaNonVuota())
                view.logErroreGerarchia();
        else {           
            view.mostraMessaggio("Seleziona la gerarchia per l'offerta.");
            GerarchiaCategorie gerarchiaOfferta = sceltaRadice();
            ArrayList<CategoriaFoglia> foglieOfferta = gerarchiaOfferta.getListaFoglie();

            view.mostraMessaggio("Seleziona la gerarchia per la richiesta.");
            GerarchiaCategorie gerarchiaRichiesta = sceltaRadice();
            ArrayList<CategoriaFoglia> foglieRichiesta = gerarchiaRichiesta.getListaFoglie();

            double min = FattoreConversione.getMin();
            double max = FattoreConversione.getMax();
            boolean aggiuntoFattore = false;

            for (CategoriaFoglia c1 : foglieOfferta) {
                for (CategoriaFoglia c2 : foglieRichiesta) {
                    if (!c1.getNome().equals(c2.getNome()) && dati.getFattoreManager().esisteFattore(c1, c2)) {
                        String messaggio = "Inserisci il fattore di conversione da " 
                                            + c1.getNome().toUpperCase() 
                                            + " a " 
                                            + c2.getNome().toUpperCase() 
                                            + ":";
                        double fattore = view.leggiFattore(messaggio, min, max);

                        dati.getFattoreManager().addFattore(c1, c2, fattore);
                        aggiuntoFattore = true;
                    }
                }
            }
            if (!aggiuntoFattore) {
                view.mostraMessaggio("Tutte le categorie foglia hanno già assegnato un fattore di conversione.");
            }
        }
    }






       

   




}
