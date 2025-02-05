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
import it.unibs.ing.model.user.IFruitore;
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

    public void registraFruitore(String username, String password, IComprensorio comprensorio, String mail) {
        IFruitore fruitore = new Fruitore(username, password, comprensorio, mail);
        fruitoreManager.addElemento(fruitore);
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
                boolean isFoglia = g.getListaFoglie().stream()
                    .anyMatch(foglia -> foglia.getNome().equals(categoriaCorrente.getNome()));

                if (isFoglia) {
                    view.mostraMessaggio("Categoria foglia - Premi 0 per tornare indietro nella gerarchia o digita 'esci' per uscire");
                } else {
                    view.mostraValoriSottocategorie(categoriaCorrente.getSottocategorie());
                    view.mostraMessaggio("Inserisci il valore del campo per navigare nella sottocategoria (0 per tornare indietro nella gerarchia - 'esci' per uscire)");
                }

                String valore = view.leggiValore("");

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
        if (!listaGerarchiaNonVuota()) {
            return;
        }
        
        String[] categorie = selezionaCategorieProposta();
        String richiesta = categorie[0];
        String offerta = categorie[1];
        
        int durataRichiesta = view.leggiValoreIntero("Quante ore per la richiesta? ");

        IProposta proposta = new Proposta(richiesta, offerta, durataRichiesta, user);
        IFattore f;

        try{
            f = fattoreManager.trovaFattore(richiesta, offerta);
        } catch(NullPointerException e) {
            view.mostraMessaggio("Un configuratore deve ancora definire il fattore di conversione corrispondente.");
            return;
        }
        proposta.calcolaDurataOfferta(f.getFattore());
        confermaProposta(proposta);
    }

    private void confermaProposta(IProposta proposta) {
        view.mostraMessaggio(view.toStringProposta(proposta));
        boolean confermaProposta = view.chiediConferma("Vuoi confermare la proposta? ");

        if (!confermaProposta) {
            view.mostraMessaggio("Proposta non confermata.");
            return;
        }
        
        proposta.accettaProposta();
        propostaManager.addElemento(proposta);
        view.mostraMessaggio("Proposta confermata.");
        
        if (verificaProposta(proposta)) {
            view.mostraMessaggio("La proposta è stata chiusa! Riceverai una mail per i dettagli.");
        }
    }
    
    private String[] selezionaCategorieProposta() {
        String richiesta;
        String offerta;
        do {
            richiesta = selezionaCategoria(" per l'offerta");
            offerta = selezionaCategoria(" per la richiesta");
            if (richiesta.equals(offerta)) {
                view.mostraMessaggio("Non può essere selezionata la stessa categoria. Riprovare.");
            }
        } while (richiesta.equals(offerta));
        return new String[]{richiesta, offerta};
    }
    



    private boolean verificaProposta(IProposta proposta) {
        String user = proposta.getUsername();
	    IComprensorio comprensorio =fruitoreManager.getComprensorioFromUser(user);
	    ArrayList<String> userFruitoriFromComprensorio =fruitoreManager.getUserFruitoriFromComprensorio(comprensorio);
	    userFruitoriFromComprensorio.remove(user);
        ArrayList<IProposta> proposteAperteFromComprensorio = propostaManager.getProposteAperteFromUsers(userFruitoriFromComprensorio);  
        propostaManager.setChiusuraProposteStrategy(new ConcreteStrategyProposte());
        return propostaManager.verificaProposta(proposta, proposteAperteFromComprensorio);
    }


    public void visualizzaProposteByUser(String user) {
	    ArrayList<IProposta> list = propostaManager.getListaProposteUser(user);
	    if (list.isEmpty()) {
            view.mostraMessaggio("Non sono presenti proposte da visualizzare.");
	    } else {
            view.visualizzaProposte(list);
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
