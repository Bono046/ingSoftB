package it.unibs.ing.controller;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.Configuratore;
import it.unibs.ing.model.ConfiguratoreManager;
import it.unibs.ing.model.Dati;
import it.unibs.ing.model.FileManager;


public class ControllerConfiguratore extends ControllerBase {

    Dati dati;
    
    public ControllerConfiguratore(Dati dati) {
        super(dati); 
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

    public Dati getDati() {
        return dati;
    }

   public void aggiungiComprensorio(String nome, Set<String> comuni) {
        ComprensorioGeografico comprensorio = new ComprensorioGeografico(nome, comuni);
        dati.getComprensorioManager().addComprensorio(comprensorio);
   }


}
