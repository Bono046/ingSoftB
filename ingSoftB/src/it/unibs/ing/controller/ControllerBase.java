package it.unibs.ing.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibs.ing.model.*;
import it.unibs.ing.view.ViewBase;


public class ControllerBase {

    Dati dati;
    ViewBase view = new ViewBase(this);

    public ControllerBase(Dati dati) {
        this.dati = dati;
    }

    public void salvaDati() throws IOException {
        FileManager.salvaDati(dati);
    }

    public boolean listaGerarchiaNonVuota() {
        if (dati.getGerarchiaCategorieManager().getListaRadici().isEmpty()) {
            view.logErroreGerarchia();
            return false;
        }
        return true;
    }

    public GerarchiaCategorie sceltaRadice() {
        ArrayList<GerarchiaCategorie> listaOggettiGerarchia = dati.getGerarchiaCategorieManager()
                .getListaOggettiGerarchia();

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

}