package it.unibs.ing.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import it.unibs.ing.model.*;
import it.unibs.ing.model.comprensorio.Comprensorio;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.fattore.FattoreConversione;
import it.unibs.ing.model.gerarchia.Categoria;
import it.unibs.ing.model.gerarchia.CategoriaFoglia;
import it.unibs.ing.model.gerarchia.GerarchiaCategorie;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.gerarchia.IGerarchia;
import it.unibs.ing.model.user.Configuratore;
import it.unibs.ing.view.ViewConfiguratore;
import it.unibs.ing.model.proposta.IProposta;
import it.unibs.ing.model.fattore.IFattore;

public class ControllerConfiguratore extends ControllerBase {

    ViewConfiguratore view;

    public ControllerConfiguratore(Dati dati) {
        super(dati); 
    }



    public void registraView(ViewConfiguratore view) {
        this.view = view;
    }

    public Boolean verificaCredenzialiPrimoAccesso(String usernamePredefinito, String passwordPredefinita) {
        return Configuratore.verificaPrimoAccesso(usernamePredefinito, passwordPredefinita);
    }

    public void registraConfiguratore(String username, String password) {
        Configuratore conf = new Configuratore(username, password);
        configuratoreManager.addToListaConfiguratori(conf);
    }

    public Boolean loginConfiguratore(String username, String password) {
        return configuratoreManager.loginConfiguratore(username, password);
    }


    public void aggiungiComprensorio(String nome, Set<String> comuni) {
        IComprensorio comprensorio = new Comprensorio(nome, comuni);
        comprensorioManager.addComprensorio(comprensorio);
    }

    public ArrayList<IComprensorio> getComprensorioManager() {
        return comprensorioManager.getLista();
    }


    public boolean checkNomeGerarchia(String nome) {
        return gerarchiaManager.checkNomeGerarchia(nome);
    }

    public void addGerarchia(GerarchiaCategorie g) {
        gerarchiaManager.addGerarchia(g);
    }

    public ICategoria creaCategoria(boolean isRadice, ICategoria c) {
        String nome = isRadice ? view.getNomeRadice() : getNomeCategoriaValido(c);
        String campo = view.leggiCampo();
        HashMap<String, String> dominio = view.leggiDominio();
        return new Categoria(nome, campo, dominio);
    }

    public ICategoria creaCategoriaFoglia(ICategoria radice) {
        String nome = getNomeCategoriaValido(radice); 		
        ICategoria categoria = new CategoriaFoglia(nome);
        return categoria;
    }

    public String getNomeCategoriaValido(ICategoria radice) {
        boolean nomeValido = false;
        String nome=""; 
        while(!nomeValido) {
            nome = view.getNomeCategoria();
                nomeValido = radice.isNomeUnivoco(nome);
                if(!nomeValido) 
                    view.nomeNonValido();
            }
        return nome;
    }

    public void inizializzaGerarchia(){
        ICategoria radice = creaCategoria(true, null);
        GerarchiaCategorie g = new GerarchiaCategorie(radice);
        componiGerarchia(g, radice);
        gerarchiaManager.addGerarchia(g);
    }

    public void componiGerarchia(IGerarchia g, ICategoria padre) {		
    	
    	ArrayList<String> listaDominio = new ArrayList<>(padre.getDominio().keySet());
        ICategoria radice = g.getCategoriaRadice();
    	
    	for (String dom : listaDominio) {

            int choice1 = view.stampaSceltaSottocategoria(dom);
            
            switch(choice1) {
                case 1:
                    ICategoria sottocat = creaCategoria(false, radice);
                    padre.aggiungiSottocategoria(dom, sottocat);
                    HashMap<String, ICategoria> sottocategorie = padre.getSottocategorie();
                    componiGerarchia(g, sottocategorie.get(dom));
                    break;
                case 2:
                    ICategoria sottocatF = creaCategoriaFoglia(radice);
                    padre.aggiungiSottocategoria(dom, sottocatF);
                    g.addToListaFoglie(sottocatF);
                    break;
                default:
                    view.logErroreGenerico();
            }
        }
    }


 

    //probabilmente da spostare in ControllerBase
    public void visualizzaGerarchie() {
        if (listaGerarchiaNonVuota()) {
            for (ICategoria gerarchia : gerarchiaManager.getListaRadici()) {
                view.stampaAlbero("", gerarchia);
            }
        }
    }

    public boolean listaGerarchiaNonVuota() {
        if (gerarchiaManager.getListaRadici().isEmpty()) {
            view.logErroreGerarchia();
            return false;
        } 
        return true;
    }


    public IGerarchia sceltaRadice() {
        ArrayList<IGerarchia> listaOggettiGerarchia = gerarchiaManager.getListaOggettiGerarchia();

        if (listaGerarchiaNonVuota()) {
            List<String> nomiGerarchie = new ArrayList<>();
            for (IGerarchia gerarchia : listaOggettiGerarchia) {
                nomiGerarchie.add(gerarchia.getCategoriaRadice().getNome());
            }
            int scelta = view.selezionaGerarchia(nomiGerarchie);

            return listaOggettiGerarchia.get(scelta - 1);
        }
        return null;
    }


    

    public void setFattoriConversione() {
        
        if (!listaGerarchiaNonVuota())
                view.logErroreGerarchia();
        else {           
            view.mostraMessaggio("Seleziona la gerarchia per l'offerta ");
            IGerarchia gerarchiaOfferta = sceltaRadice();
            ArrayList<ICategoria> foglieOfferta = gerarchiaOfferta.getListaFoglie();

            view.mostraMessaggio("Seleziona la gerarchia per la richiesta ");
            IGerarchia gerarchiaRichiesta = sceltaRadice();
            ArrayList<ICategoria> foglieRichiesta = gerarchiaRichiesta.getListaFoglie();

            double min = FattoreConversione.getMin();
            double max = FattoreConversione.getMax();
            boolean aggiuntoFattore = false;

            for (ICategoria foglia1 : foglieOfferta) {
                String c1 = foglia1.getNome();
                for (ICategoria foglia2 : foglieRichiesta) {
                    String c2 = foglia2.getNome();

                    if (!c1.equals(c2) && !fattoreManager.esisteFattore(c1, c2)) {
                        String messaggio = "Inserisci il fattore di conversione da " 
                                            + c1.toUpperCase() 
                                            + " a " 
                                            + c2.toUpperCase() 
                                            + ": ";
                        double fattore = view.leggiFattore(messaggio, min, max);

                        fattoreManager.addFattore(c1, c2, fattore);
                        aggiuntoFattore = true;
                    }
                }
            }
            if (!aggiuntoFattore) {
                view.mostraMessaggio("Tutte le categorie foglia hanno già assegnato un fattore di conversione.");
            }
        }
    }

    public void visualizzaFattoriConversione() {
        if (listaGerarchiaNonVuota()) {

            IGerarchia g = sceltaRadice();
            String nome = view.chiediNomeCategorieFoglia(g.getListaFoglie());
            ArrayList<IFattore> fattoriDaVisualizzare = fattoreManager.trovaFattore(nome);

            if (fattoriDaVisualizzare.isEmpty()) {
                view.mostraMessaggio("Non esistono fattori di conversione per la categoria selezionata\n");
            } else {
                view.mostraFattoriConversione(fattoriDaVisualizzare);
            }
        }
    }

    public void visualizzaProposteByFoglia() {
		if(listaGerarchiaNonVuota()){
            String foglia = view.chiediNomeCategorieFoglia(sceltaRadice().getListaFoglie());
            ArrayList<IProposta> lista = propostaManager.getListaProposte();
            ArrayList<IProposta> listaDaVisualizzare = new ArrayList<>();
            for(IProposta proposta:lista) {
                if(proposta.getOfferta().equals(foglia) || proposta.getRichiesta().equals(foglia))
                    listaDaVisualizzare.add(proposta);                
                }
            
            if(listaDaVisualizzare.isEmpty()) {
                System.out.println("Non esistono proposte legate alla categoria foglia selezionata");
            } else {
                view.visualizzaProposte(listaDaVisualizzare);
            }
        }   
	}

   

       

   




}
