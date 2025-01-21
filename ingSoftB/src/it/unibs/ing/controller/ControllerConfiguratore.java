package it.unibs.ing.controller;
import java.io.IOException;

import it.unibs.ing.model.Configuratore;
import it.unibs.ing.model.ConfiguratoreManager;
import it.unibs.ing.model.Dati;
import it.unibs.ing.model.FileManager;

public class ControllerConfiguratore {

    Dati dati;
    
    public ControllerConfiguratore(Dati dati) {
        this.dati = dati;
    }

    public Boolean verificaCredenzialiPrimoAccesso(String usernamePredefinito, String passwordPredefinita) {
        return Configuratore.verificaPrimoAccesso(usernamePredefinito, passwordPredefinita);
    }

    public Boolean userOk(String username) {
        return dati.getConfiguratoreManager().userValido(username);
    }

    public void registraConfiguratore(String username, String password) {
        Configuratore conf = new Configuratore(username, password);
        dati.getConfiguratoreManager().addToListaConfiguratori(conf);
    }

    public Boolean loginConfiguratore(String username, String password) {
        return dati.getConfiguratoreManager().loginConfiguratore(username, password);
    }

    public void salvaDati() throws IOException {
        FileManager.salvaDati(dati);
    }

}
