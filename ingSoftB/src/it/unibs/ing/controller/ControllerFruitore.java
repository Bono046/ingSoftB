package it.unibs.ing.controller;

import it.unibs.ing.model.*;
import it.unibs.ing.view.ViewFruitore;

import java.util.ArrayList;


public class ControllerFruitore extends ControllerBase{    
    
    ViewFruitore view;

    public ControllerFruitore(Dati dati) {
        super(dati);
    }

    public void registraView(ViewFruitore view) {
        this.view = view;
    }

    public ArrayList<ComprensorioGeografico> getListaComprensori() {
        ArrayList <ComprensorioGeografico> comprensori = dati.getComprensorioManager().getLista();
        if(comprensori.isEmpty())
            throw new IllegalArgumentException();
        return comprensori;
    } 

    public Boolean userOk(String username) {
        return dati.getFruitoreManager().userValido(username);
    }

    public void registraFruitore(String username, String password, ComprensorioGeografico comprensorio, String mail) {
        Fruitore fruitore = new Fruitore(username, password, comprensorio, mail);
        dati.getFruitoreManager().addToListaFruitori(fruitore);
    }

    public Boolean loginFruitore(String username, String password) {
        return dati.getFruitoreManager().loginFruitore(username, password);
    }


    public boolean esploraGerarchia() {

        GerarchiaCategorie g = sceltaRadice();
        boolean ricerca = true;
        g.setCategoriaCorrente();
        while (ricerca) {
            
            ComponenteCategoria categoriaCorrente = g.getCategoriaCorrente();
            view.mostraCategoriaCorrente(categoriaCorrente.getNome());

            // Controlla se la categoria corrente è una foglia
            boolean isFoglia = g.getListaFoglie().stream()
                .anyMatch(foglia -> foglia.getNome().equals(categoriaCorrente.getNome()));

            if (isFoglia) {
                view.mostraMessaggio("Categoria foglia - Premi 0 per tornare indietro nella gerarchia o digita 'esci' per uscire");
            } else {
                view.mostraValoriSottocategorie(categoriaCorrente.getSottocategorie());
                view.mostraMessaggio("Inserisci il valore del campo per navigare nella sottocategoria (0 per tornare indietro nella gerarchia - 'esci' per uscire)");
            }

            String valore = view.leggiValore("");

            // Logica di navigazione
            if (valore.equalsIgnoreCase("esci")) {
                ricerca = false;
            } else if (valore.equals("0")) {
                if(g.getCategoriaCorrente().equals(g.getCategoriaRadice()))
                    ricerca = false;
                g.tornaIndietro();
            } else if (categoriaCorrente.getSottocategorie().containsKey(valore)) {
                g.vaiASottocategoria(categoriaCorrente.getSottocategorie().get(valore));
            } else {
                view.mostraMessaggio("Valore non valido. Riprovare.");
            }
        }

        return false; 
    }


    public void creaProposta(String user) {
        String richiesta;
        String offerta;
        boolean categorieValide = false;
        do {
            view.mostraMessaggio("Seleziona la gerarchia per l'offerta ");
            GerarchiaCategorie gerarchiaRichiesta = sceltaRadice(); 
            richiesta = view.chiediNomeCategorieFoglia(gerarchiaRichiesta.getListaFoglie());

            view.mostraMessaggio("Seleziona la gerarchia per la richiesta ");
            GerarchiaCategorie gerarchiaOfferta = sceltaRadice(); 
            offerta = view.chiediNomeCategorieFoglia(gerarchiaOfferta.getListaFoglie());
            if (richiesta.equals(offerta)) {
                view.mostraMessaggio("Non può essere selezionata la stessa categoria. Riprovare.");
            } else {
                categorieValide = true;
            }
        } while (!categorieValide);

        int durataRichiesta = view.leggiValoreIntero("Quante ore per la richiesta? ");
        int durataOfferta = calcolaDurataOfferta(richiesta, offerta, durataRichiesta);

        if (durataOfferta == 0) {
            view.mostraMessaggio("Un configuratore deve ancora definire il fattore di conversione corrispondente.");
            return;
        }
        Proposta proposta = new Proposta(richiesta, offerta, durataRichiesta, durataOfferta, user);
    
        view.mostraMessaggio(view.toStringProposta(proposta));

        boolean confermato = false;
        while (!confermato) {
            Boolean conferma = view.chiediConferma("Vuoi confermare la proposta? ");
            if (conferma) {
                proposta.accettaProposta();
                dati.getPropostaManager().addProposta(proposta);
                view.mostraMessaggio("Proposta confermata.");
                confermato = true;

                if (verificaProposta(proposta)) {
                    view.mostraMessaggio("La proposta è stata chiusa! Riceverai una mail per i dettagli.");
                }
            } else {
                view.mostraMessaggio("Proposta non confermata.");
                return;
            } 
        }}


    private int calcolaDurataOfferta(String richiesta, String offerta, int durataRichiesta) {
        int durataOfferta=0;
        try {
            FattoreConversione f = dati.getFattoreManager().trovaFattore(richiesta, offerta);
            durataOfferta = (int) Math.round(durataRichiesta * f.getFattore());
            return durataOfferta;
        } catch (NullPointerException e) {
            System.out.println("non esiste questo fattore di conversione");
            return 0;
        }
    }


    private boolean verificaProposta(Proposta proposta) {
        String user = proposta.getUsername();
	    ComprensorioGeografico comprensorio = dati.getFruitoreManager().getComprensorioFromUser(user);
	    ArrayList<String> userFruitoriFromComprensorio = dati.getFruitoreManager().getUserFruitoriFromComprensorio(comprensorio);
	    userFruitoriFromComprensorio.remove(user);
       
        ArrayList<Proposta> proposteAperteFromComprensorio = dati.getPropostaManager().getProposteAperteFromUsers(userFruitoriFromComprensorio);  
        dati.getPropostaManager().setChiusuraProposteStrategy(new ConcreteStrategyProposte());
        boolean chiusura = dati.getPropostaManager().verificaProposta(proposta, proposteAperteFromComprensorio);
        return chiusura;
    }


    public void visualizzaProposteByUser(String user) {
	    ArrayList<Proposta> list = dati.getPropostaManager().getListaProposteUser(user);
	    if (list.isEmpty()) {
            view.mostraMessaggio("Non sono presenti proposte da visualizzare.");
	    } else {
            view.mostraMessaggio(view.visualizzaProposte(list));;
    	}
    }   

    public void ritiraProposta(String user) {
	    
	    ArrayList<Proposta> list = dati.getPropostaManager().getListaProposteAperteUser(user);

	    if (list.isEmpty()) {
	        view.mostraMessaggio("Non sono presenti proposte da ritirare.");
	        return;
	    }

	    Proposta propostaDaRitirare = view.selezionaDaLista(list, "Seleziona il numero della proposta da ritirare:");

	    propostaDaRitirare.ritiraProposta();
	    view.mostraMessaggio("La proposta è stata ritirata con successo.");
	    }


}
