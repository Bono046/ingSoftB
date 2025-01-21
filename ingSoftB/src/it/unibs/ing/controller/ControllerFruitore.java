package it.unibs.ing.controller;

import it.unibs.ing.model.Fruitore;
import it.unibs.ing.model.FruitoreManager;
import java.util.ArrayList;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.ComprensorioManager;

public class ControllerFruitore {
    FruitoreManager fruitoreManager;
    ComprensorioManager comprensorioManager;

    public ControllerFruitore() {
        fruitoreManager = new FruitoreManager();
    }

    public ArrayList<ComprensorioGeografico> getListaComprensori() {
        ArrayList <ComprensorioGeografico> comprensori = comprensorioManager.getListaComprensori();
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
