package it.unibs.ing.controller;

import it.unibs.ing.model.Fruitore;
import it.unibs.ing.model.FruitoreManager;
import java.util.ArrayList;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.ComprensorioManager;
import it.unibs.ing.model.Dati;

public class ControllerFruitore {
    FruitoreManager fruitoreManager;
    ComprensorioManager comprensorioManager;
    Dati dati;

    public ControllerFruitore(Dati dati) {
        this.dati = dati;
        fruitoreManager = new FruitoreManager();
    }

    public ArrayList<ComprensorioGeografico> getListaComprensori() {
        ArrayList <ComprensorioGeografico> comprensori = dati.getComprensorioManager().getListaComprensori();
        if(comprensori.isEmpty())
            throw new IllegalArgumentException();
        return comprensori;
    } 

    public Boolean userOk(String username) {
        return fruitoreManager.userValido(username);
    }

    public void registraFruitore(String username, String password, ComprensorioGeografico comprensorio, String mail) {
        Fruitore fruitore = new Fruitore(username, password, comprensorio, mail);
        fruitoreManager.addToListaConfiguratori(fruitore);
        // SALVA DATI
    }

    public Boolean loginFruitore(String username, String password) {
        return fruitoreManager.loginFruitore(username, password);
    }


}
