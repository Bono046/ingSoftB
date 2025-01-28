package it.unibs.ing.model;

import java.util.HashMap;


public interface ComponenteCategoria {

    
    String getNome();
    String getCampo();
    HashMap<String, String> getDominio();
    boolean isFoglia();
    void aggiungiSottocategoria(String nome, ComponenteCategoria categoria);
    HashMap<String, ComponenteCategoria> getSottocategorie();
    boolean isNomeUnivoco(String nome);
    
}
