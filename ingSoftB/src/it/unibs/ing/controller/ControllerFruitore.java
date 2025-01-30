package it.unibs.ing.controller;

import it.unibs.ing.model.*;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.fattore.IFattore;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.gerarchia.IGerarchia;
import it.unibs.ing.model.proposta.ConcreteStrategyProposte;
import it.unibs.ing.model.proposta.IProposta;
import it.unibs.ing.model.proposta.Proposta;
import it.unibs.ing.model.user.Fruitore;
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

    public ArrayList<IComprensorio> getListaComprensori() {
        ArrayList <IComprensorio> comprensori = comprensorioManager.getLista();
        if(comprensori.isEmpty())
            throw new IllegalArgumentException();
        return comprensori;
    } 

    public void registraFruitore(String username, String password, IComprensorio comprensorio, String mail) {
        Fruitore fruitore = new Fruitore(username, password, comprensorio, mail);
       fruitoreManager.addToListaFruitori(fruitore);
    }

    public Boolean loginFruitore(String username, String password) {
        return fruitoreManager.loginFruitore(username, password);
    }


    public boolean esploraGerarchia() {

        if(listaGerarchiaNonVuota()) {
            IGerarchia g = sceltaRadice();
            boolean ricerca = true;
            g.setCategoriaCorrente();
            while (ricerca) {
                
                ICategoria categoriaCorrente = g.getCategoriaCorrente();
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
        }
            return false; 
    }


    public void creaProposta(String user) {
        String richiesta;
        String offerta;
        boolean categorieValide = false;
        do {
            view.mostraMessaggio("Seleziona la gerarchia per l'offerta ");
            IGerarchia gerarchiaRichiesta = sceltaRadice(); 
            richiesta = view.chiediNomeCategorieFoglia(gerarchiaRichiesta.getListaFoglie());

            view.mostraMessaggio("Seleziona la gerarchia per la richiesta ");
            IGerarchia gerarchiaOfferta = sceltaRadice(); 
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
                propostaManager.addProposta(proposta);
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
            IFattore f = fattoreManager.trovaFattore(richiesta, offerta);
            durataOfferta = (int) Math.round(durataRichiesta * f.getFattore());
            return durataOfferta;
        } catch (NullPointerException e) {
            System.out.println("non esiste questo fattore di conversione");
            return 0;
        }
    }


    private boolean verificaProposta(IProposta proposta) {
        String user = proposta.getUsername();
	    IComprensorio comprensorio =fruitoreManager.getComprensorioFromUser(user);
	    ArrayList<String> userFruitoriFromComprensorio =fruitoreManager.getUserFruitoriFromComprensorio(comprensorio);
	    userFruitoriFromComprensorio.remove(user);
       
        ArrayList<IProposta> proposteAperteFromComprensorio = propostaManager.getProposteAperteFromUsers(userFruitoriFromComprensorio);  
        propostaManager.setChiusuraProposteStrategy(new ConcreteStrategyProposte());
        boolean chiusura = propostaManager.verificaProposta(proposta, proposteAperteFromComprensorio);
        return chiusura;
    }


    public void visualizzaProposteByUser(String user) {
	    ArrayList<IProposta> list = propostaManager.getListaProposteUser(user);
	    if (list.isEmpty()) {
            view.mostraMessaggio("Non sono presenti proposte da visualizzare.");
	    } else {
            view.visualizzaProposte(list);;
    	}
    }   

    public void ritiraProposta(String user) {
	    
	    ArrayList<IProposta> list = propostaManager.getListaProposteAperteUser(user);

	    if (list.isEmpty()) {
	        view.mostraMessaggio("Non sono presenti proposte da ritirare.");
	        return;
	    }

	    IProposta propostaDaRitirare = view.selezionaDaLista(list, "Seleziona il numero della proposta da ritirare:");

	    propostaDaRitirare.ritiraProposta();
	    view.mostraMessaggio("La proposta è stata ritirata con successo.");
	    }


}
