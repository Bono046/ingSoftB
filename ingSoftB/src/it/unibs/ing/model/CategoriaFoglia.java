package it.unibs.ing.model;

import java.util.HashMap;


public class CategoriaFoglia extends Categoria {
    public CategoriaFoglia(String nome) {
        super(nome, null, null);
        this.sottocategorie = null;
    }

    
	@Override
	public boolean isFoglia() {
	    return true;
	}
	
	
	@Override
	public void aggiungiSottocategoria(String nome, Categoria categoria) {
	    throw new UnsupportedOperationException("Le foglie non possono avere sottocategorie.");
	}
	
	@Override
	public String getCampo() {
	    return null; 
	}
	
	@Override
	public HashMap<String, String> getDominio() {
	    return null; 
	}
	

	
	
}