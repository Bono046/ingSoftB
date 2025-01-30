package it.unibs.ing.model.gerarchia;

import java.util.HashMap;


public class Categoria implements ICategoria{
    protected String nome;
    private String campo;
    private HashMap<String, String> dominio;
    protected HashMap<String, ICategoria> sottocategorie;


    public Categoria(String nome, String campo, HashMap<String, String> dominio) {
        this.nome = nome;
        this.campo = campo;
        this.dominio = dominio;
        this.sottocategorie = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCampo() {
        return campo;
    }

    
    public HashMap<String, String> getDominio() {
        return dominio;
    }
  

    public void aggiungiSottocategoria(String valore_dominio, ICategoria sottocategoria) {
        if (dominio.containsKey(valore_dominio)) {
                sottocategorie.put(valore_dominio, sottocategoria);
            } else {
                throw new IllegalArgumentException("Il nome della sottocategoria deve essere unico all'interno della gerarchia.");
            }
    }

    public HashMap<String, ICategoria> getSottocategorie() {
        return sottocategorie;
    }

    public boolean isFoglia() {
        return false;
    }    

	public boolean isNomeUnivoco(String nomeSottocategoria) {
	    if (this.nome.equals(nomeSottocategoria)) {
	        return false;  
	    }
	    if(sottocategorie != null) {
           
		    for (ICategoria sottocategoria : sottocategorie.values()) {
                
                    

		            if (!sottocategoria.isNomeUnivoco(nomeSottocategoria)) {
		                return false; 
		        }
		    }
        }
	    return true;
	}

}



