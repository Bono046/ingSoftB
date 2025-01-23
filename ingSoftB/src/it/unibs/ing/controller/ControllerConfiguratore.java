package it.unibs.ing.controller;

import java.util.HashMap;
import java.util.Set;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.Configuratore;
import it.unibs.ing.model.Dati;
import it.unibs.ing.model.GerarchiaCategorie;
import it.unibs.ing.view.ViewConfiguratore;
import it.unibs.ing.model.Categoria;


public class ControllerConfiguratore extends ControllerBase {

    private Dati dati;
    ViewConfiguratore view;
    
    public ControllerConfiguratore(Dati dati) {
        super(dati); 
        this.dati = dati;
    }

    public void registraView(ViewConfiguratore view) {
        this.view = view;
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
   
   public boolean checkNomeGerarchia(String nome) {
       return dati.getGerarchiaCategorieManager().checkNomeGerarchia(nome);
   }

   public void addGerarchia(GerarchiaCategorie g) {
       dati.getGerarchiaCategorieManager().addGerarchia(g);
   }

   /*public Categoria creaCategoria(boolean isRadice) {
    String nome = isRadice ? view.leggiNomeRadice() : view.leggiNomeCategoria();
    String campo = view.leggiCampo();
    HashMap<String, String> dominio = view.leggiDominio();
    */

    public Categoria creaCategoria(String nome, String campo, HashMap<String, String> dominio) {
        return new Categoria(nome, campo, dominio);
}




}
