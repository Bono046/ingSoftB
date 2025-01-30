package it.unibs.ing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ing.database.FileManager;
import it.unibs.ing.model.*;
import it.unibs.ing.model.comprensorio.ComprensorioManager;
import it.unibs.ing.model.fattore.FattoreManager;
import it.unibs.ing.model.gerarchia.GerarchiaCategorie;
import it.unibs.ing.model.gerarchia.GerarchiaManager;
import it.unibs.ing.model.proposta.PropostaManager;
import it.unibs.ing.model.user.ConfiguratoreManager;
import it.unibs.ing.model.user.FruitoreManager;
import it.unibs.ing.view.ViewBase;


public class ControllerBase {

    Dati dati;
    ViewBase view = new ViewBase(this);
    ConfiguratoreManager configuratoreManager;
    FruitoreManager fruitoreManager;
    GerarchiaManager gerarchiaManager;
    ComprensorioManager comprensorioManager;
    FattoreManager fattoreManager;
    PropostaManager propostaManager;



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

    public boolean listaGerarchiaNonVuota() {
        if (gerarchiaManager.getListaRadici().isEmpty()) {
            view.logErroreGerarchia();
            return false;
        }
        return true;
    }

    public GerarchiaCategorie sceltaRadice() {
        ArrayList<GerarchiaCategorie> listaOggettiGerarchia = gerarchiaManager.getListaOggettiGerarchia();

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

    public Boolean userOk(String username) {
        return (configuratoreManager.userValido(username) && fruitoreManager.userValido(username));
    }

}