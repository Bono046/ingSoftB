package it.unibs.ing.controller;

import java.io.IOException;

import it.unibs.ing.model.Dati;
import it.unibs.ing.model.FileManager;

public class ControllerBase {

    Dati dati;

    public ControllerBase(Dati dati) {
        this.dati = dati;
    }

    
     public void salvaDati() throws IOException {
        FileManager.salvaDati(dati);
    }



}