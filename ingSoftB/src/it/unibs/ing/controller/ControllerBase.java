package it.unibs.ing.controller;

import java.io.IOException;
import java.util.ArrayList;
import it.unibs.ing.database.FileManager;
import it.unibs.ing.model.*;
import it.unibs.ing.model.fattore.IFattoreManager;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.gerarchia.IGerarchia;
import it.unibs.ing.model.gerarchia.IGerarchiaManager;
import it.unibs.ing.model.proposta.IPropostaManager;
import it.unibs.ing.model.user.IConfiguratoreManager;
import it.unibs.ing.model.user.IFruitoreManager;
import it.unibs.ing.view.ViewBase;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.comprensorio.IComprensorioManager;


public class ControllerBase {

    Dati dati;
    ViewBase view = new ViewBase(this);
    IConfiguratoreManager configuratoreManager;
    IFruitoreManager fruitoreManager;
    IGerarchiaManager gerarchiaManager;
    IComprensorioManager comprensorioManager;
    IFattoreManager fattoreManager;
    IPropostaManager propostaManager;



    public ControllerBase(Dati dati) {
        this.dati = dati;
        this.configuratoreManager = dati.getConfiguratoreManager();
        this.fruitoreManager = dati.getFruitoreManager();
        this.gerarchiaManager = dati.getGerarchiaCategorieManager();
        this.comprensorioManager = dati.getComprensorioManager();
        this.fattoreManager = dati.getFattoreManager();
        this.propostaManager = dati.getPropostaManager();
    }

    public void salvaDati() throws IllegalStateException {
        try {
            FileManager.salvaDati(dati);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } 
    }


    public Boolean userOk(String username) {
        return (configuratoreManager.userValido(username) && fruitoreManager.userValido(username));
    }

    public ArrayList<IComprensorio> getListaComprensori() {
        ArrayList <IComprensorio> comprensori = comprensorioManager.getLista();
        if(comprensori.isEmpty())
            throw new IllegalArgumentException();
        return comprensori;
    } 


    public boolean listaGerarchiaNonVuota() {
        if (gerarchiaManager.getListaRadici().isEmpty()) {
            view.logErroreGerarchia();
            return false;
        }
        return true;
    }

    public IGerarchia sceltaRadice() {
        ArrayList<IGerarchia> listaOggettiGerarchia = gerarchiaManager.getLista();

        if (!listaGerarchiaNonVuota()) {
            return null;
        }
        return view.selezionaDaLista(listaOggettiGerarchia, "Seleziona gerarchia");
    }

    
    public void visualizzaGerarchie() {
        if (listaGerarchiaNonVuota()) {
            for (ICategoria gerarchia : gerarchiaManager.getListaRadici()) {
                view.stampaAlbero("", gerarchia);
            }
        }
    }   

    public String selezionaCategoria(String messaggio) {
        IGerarchia gerarchia = sceltaRadice();
        ArrayList<ICategoria> listaFoglie = gerarchia.getListaFoglie();
        return view.chiediNomeCategorieFoglia(listaFoglie, messaggio);
    }

}