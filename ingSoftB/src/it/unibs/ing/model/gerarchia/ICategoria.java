package it.unibs.ing.model.gerarchia;

import java.util.HashMap;


public interface ICategoria {

    String getNome();
    String getCampo();
    HashMap<String, String> getDominio();
    boolean isFoglia();
    void aggiungiSottocategoria(String nome, ICategoria categoria);
    HashMap<String, ICategoria> getSottocategorie();
    boolean isNomeUnivoco(String nome);
    
}
