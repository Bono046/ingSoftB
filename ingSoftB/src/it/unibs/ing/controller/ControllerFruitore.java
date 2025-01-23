package it.unibs.ing.controller;

import it.unibs.ing.model.Fruitore;
import java.util.ArrayList;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.Dati;

public class ControllerFruitore extends ControllerBase{    
    Dati dati;

    public ControllerFruitore(Dati dati) {
        super(dati);
        this.dati = dati;
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


}
